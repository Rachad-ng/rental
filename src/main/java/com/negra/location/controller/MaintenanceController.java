package com.negra.location.controller;

import com.google.gson.Gson;
import com.negra.location.exception.DataStoreException;
import com.negra.location.exception.OilChangeMaxMileageException;
import com.negra.location.model.Car;
import com.negra.location.model.CarRepair;
import com.negra.location.model.Maintenance;
import com.negra.location.model.OilChange;
import com.negra.location.service.interfaces.ICarService;
import com.negra.location.service.interfaces.IMaintenanceService;
import com.negra.location.service.interfaces.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static com.negra.location.utility.ErrorMessage.*;
import static com.negra.location.utility.ErrorMessage.ACCESS_DENIED;

@AllArgsConstructor
@Controller
public class MaintenanceController {

    private static final String RESPONSE_STATUS_SUCCESS = "success";
    private static final String RESPONSE_STATUS_FAILED = "failed";
    private static final String RESPONSE_MESSAGE_SUCCESS = "L'opération a été bien effecutée";

    // Injection des services
    private ICarService carService;
    private IUserService userService;
    private IMaintenanceService maintenanceService;

    @PostMapping("/agent/add-car-repair")
    public @ResponseBody
    String addCarRepair(@RequestParam("date") LocalDate date, @RequestParam("amount") int amount, @RequestParam("comment") String comment, @RequestParam("idCar") Long idCar){
        Map<String, String> data = new HashMap<>();
        String status = RESPONSE_STATUS_FAILED;
        String message = "";

        Car car = carService.findById(idCar);

        if(car.getAgent().getId() == userService.getCurrentUser().getId()){
            if(date.atStartOfDay().isBefore(LocalDateTime.now())){
                if(amount >= 0){

                    if(!comment.equals("")){
                        Maintenance carRepair = new CarRepair(amount, comment, date);
                        try {
                            maintenanceService.createMaintenance(carRepair, car);

                            status = RESPONSE_STATUS_SUCCESS;
                            message = RESPONSE_MESSAGE_SUCCESS;

                        }catch (DataStoreException e){
                            message = e.getMessage();
                        }
                    }else
                        message = ERROR_MAINTENANCE_COMMENT_INVALID;

                }else
                    message = ERROR_MAINTENANCE_AMOUNT_INVALID;
            }else
                message = ERROR_MAINTENANCE_DATE_INVALID;

        }else
            message = ACCESS_DENIED;

        data.put("status", status);
        data.put("serverMessage", message);

        return new Gson().toJson(data);
    }

    @PostMapping("/agent/add-oil-change")
    public @ResponseBody String addOilChange(@RequestParam("date") LocalDate date, @RequestParam("amount") int amount, @RequestParam("maxMileage") int maxMileage, @RequestParam("idCar") Long idCar){
        Map<String, String> data = new HashMap<>();
        String status = RESPONSE_STATUS_FAILED;
        String message = "";

        Car car = carService.findById(idCar);

        if(car.getAgent().getId() == userService.getCurrentUser().getId()){
            if(date.atStartOfDay().isBefore(LocalDateTime.now())){
                if(amount >= 0){

                    if(maxMileage >= 0){
                        Maintenance oilChange = new OilChange(amount, maxMileage, date);
                        try{
                            maintenanceService.createMaintenance(oilChange, car);

                            status = RESPONSE_STATUS_SUCCESS;
                            message = RESPONSE_MESSAGE_SUCCESS;

                        }catch (OilChangeMaxMileageException | DataStoreException e) {
                            message = e.getMessage();
                        }
                    }else
                        message = ERROR_OIL_CHANGE_MILEAGE_MAX_INVALID;

                }else
                    message = ERROR_MAINTENANCE_AMOUNT_INVALID;
            }else
                message = ERROR_MAINTENANCE_DATE_INVALID;

        }else
            message = ACCESS_DENIED;

        data.put("status", status);
        data.put("serverMessage", message);

        return new Gson().toJson(data);
    }

}
