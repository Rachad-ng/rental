package com.negra.location.mapper;

import com.negra.location.dto.BookingCarDto;
import com.negra.location.model.*;
import com.negra.location.service.interfaces.ICostService;
import com.negra.location.service.interfaces.IMaintenanceService;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class BookingCarDtoMapper {

    @Autowired
    private ICostService costService;
    @Autowired
    private IMaintenanceService maintenanceService;
    @Autowired
    private AssuranceDueDateDtoMapper assuranceDueDateDtoMapper;
    @Autowired
    private TechnicalVisitDueDateDtoMapper technicalVisitDueDateDtoMapper;
    @Autowired
    private CarStickerDueDateDtoMapper carStickerDueDateDtoMapper;
    @Autowired
    private OilChangeMaxMileageDtoMapper oilChangeMaxMileageDtoMapper;

    public abstract BookingCarDto carToLBookingCarDto(Car car);

    @BeforeMapping
    protected void setCostDueDatesAndMaintenance(Car car, @MappingTarget BookingCarDto bookingCarDto){

        Long idCar = car.getId();

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

                bookingCarDto.setAssurance(assuranceDueDateDtoMapper.assuranceToAssuranceDueDateDto(lastAssurance));
            }

            if(technicalVisits.size() > 0){
                TechnicalVisit lastTechnicalVisit = (TechnicalVisit) technicalVisits.stream().sorted(Comparator.comparing(Cost::getDueDate).reversed())
                        .collect(Collectors.toList()).get(0);

                bookingCarDto.setTechnicalVisit(technicalVisitDueDateDtoMapper.technicalVisitToTechnicalVisitDueDateDto(lastTechnicalVisit));
            }

            if(carStickers.size() > 0){
                CarSticker lastCarSticker = (CarSticker) carStickers.stream().sorted(Comparator.comparing(Cost::getDueDate).reversed())
                        .collect(Collectors.toList()).get(0);

                bookingCarDto.setCarSticker(carStickerDueDateDtoMapper.carStickerToCarStickerDueDateDto(lastCarSticker));
            }
        }

        if(maintenances.size() > 0){
            List<Maintenance> oilChanges = maintenances.stream().filter(m -> m instanceof OilChange).collect(Collectors.toList());

            if(oilChanges.size() > 0){
                OilChange oilChange = (OilChange) oilChanges.stream()
                        .sorted(Comparator.comparing(Maintenance::getId).reversed())
                        .collect(Collectors.toList()).get(0);

                bookingCarDto.setOilChange(oilChangeMaxMileageDtoMapper.oilChangeToOilChangeMaxMileageDto(oilChange));
            }
        }
    }

}
