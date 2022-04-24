package com.negra.location.mapper;

import com.negra.location.dto.*;
import com.negra.location.model.*;
import com.negra.location.repository.VisitRepository;
import com.negra.location.service.interfaces.IBookingService;
import com.negra.location.service.interfaces.ICostService;
import com.negra.location.service.interfaces.IMaintenanceService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class CarDetailsDtoMapper {

    @Autowired
    private ICostService costService;
    @Autowired
    private AssuranceDueDateDtoMapper assuranceDueDateDtoMapper;
    @Autowired
    private TechnicalVisitDueDateDtoMapper technicalVisitDueDateDtoMapper;
    @Autowired
    private CarStickerDueDateDtoMapper carStickerDueDateDtoMapper;
    @Autowired
    private IMaintenanceService maintenanceService;
    @Autowired
    private OilChangeMaxMileageDtoMapper oilChangeMaxMileageDtoMapper;
    @Autowired
    private VisitRepository visitRepository;
    @Autowired
    private IBookingService bookingService;

    public abstract CarDetailsDto carToCarDetailsDto(Car car);

    @BeforeMapping
    protected void setCostDueDatesAndMaintenance(Car car, @MappingTarget CarDetailsDto carDetailsDto){

        Long idCar = car.getId();

        carDetailsDto.setNumberOfVisits(visitRepository.getCarNumberOfVisists(idCar));
        carDetailsDto.setLastVisitDateTime(visitRepository.getCarLastVisistDate(idCar));
        carDetailsDto.setNumberOfBookingsInProgress(bookingService.getCarNumberOfBookingsInProgress(idCar));

        List<Cost> costs = costService.findByCarId(idCar);
        List<Maintenance> maintenances = maintenanceService.findByCarId(idCar);

        if(costs.size() > 0){
             List<Cost> assurances, technicalVisits, carStickers;

             assurances = costs.stream()
                     .filter(c -> c instanceof Assurance).collect(Collectors.toList());

             technicalVisits = costs.stream()
                     .filter(c -> c instanceof TechnicalVisit).collect(Collectors.toList());

             carStickers = costs.stream()
                     .filter(c -> c instanceof CarSticker).collect(Collectors.toList());

             if(assurances.size() > 0){
                 Assurance lastAssurance = (Assurance) assurances.stream().sorted(Comparator.comparing(Cost::getDueDate).reversed())
                         .collect(Collectors.toList()).get(0);

                 carDetailsDto.setAssurance(assuranceDueDateDtoMapper.assuranceToAssuranceDueDateDto(lastAssurance));
             }

            if(technicalVisits.size() > 0){
                TechnicalVisit lastTechnicalVisit = (TechnicalVisit) technicalVisits.stream().sorted(Comparator.comparing(Cost::getDueDate).reversed())
                        .collect(Collectors.toList()).get(0);

                carDetailsDto.setTechnicalVisit(technicalVisitDueDateDtoMapper.technicalVisitToTechnicalVisitDueDateDto(lastTechnicalVisit));
            }

            if(carStickers.size() > 0){
                CarSticker lastCarSticker = (CarSticker) carStickers.stream().sorted(Comparator.comparing(Cost::getDueDate).reversed())
                        .collect(Collectors.toList()).get(0);

                carDetailsDto.setCarSticker(carStickerDueDateDtoMapper.carStickerToCarStickerDueDateDto(lastCarSticker));
            }
        }

        if(maintenances.size() > 0){
            List<Maintenance> oilChanges = maintenances.stream().filter(m -> m instanceof OilChange).collect(Collectors.toList());

            if(oilChanges.size() > 1){
                OilChange oilChange = (OilChange) oilChanges.stream()
                        .sorted(Comparator.comparing(Maintenance::getId).reversed())
                        .collect(Collectors.toList()).get(0);

                carDetailsDto.setOilChange(oilChangeMaxMileageDtoMapper.oilChangeToOilChangeMaxMileageDto(oilChange));
            }
        }
    }

    @AfterMapping
    protected void setDefaultValues(@MappingTarget CarDetailsDto carDetailsDto){

        if(carDetailsDto.getCarSticker() == null)
            carDetailsDto.setCarSticker(new CarStickerDueDateDto());

        if(carDetailsDto.getAssurance() == null)
            carDetailsDto.setAssurance(new AssuranceDueDateDto());

        if(carDetailsDto.getTechnicalVisit() == null)
            carDetailsDto.setTechnicalVisit(new TechnicalVisitDueDateDto());

        if (carDetailsDto.getOilChange() == null)
            carDetailsDto.setOilChange(new OilChangeMaxMileageDto());
    }

}
