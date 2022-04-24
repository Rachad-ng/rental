package com.negra.location.controller;

import com.google.gson.Gson;
import com.negra.location.enums.CostEnum;
import com.negra.location.exception.CostDateException;
import com.negra.location.exception.DataStoreException;
import com.negra.location.model.*;
import com.negra.location.service.interfaces.ICarService;
import com.negra.location.service.interfaces.ICostService;
import com.negra.location.service.interfaces.IUserService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static com.negra.location.utility.ErrorMessage.*;

@AllArgsConstructor
@Controller
public class CostController {

    private static final String RESPONSE_STATUS_SUCCESS = "success";
    private static final String RESPONSE_STATUS_FAILED = "failed";
    private static final String RESPONSE_MESSAGE_SUCCESS = "L'opération a été bien effecutée";

    // Injection des services
    private ICarService carService;
    private IUserService userService;
    private ICostService costService;

    @PostMapping("/agent/add-cost")
    public @ResponseBody
    String addCost(@RequestParam("date") LocalDate date, @RequestParam("amount") int amount, @RequestParam("operation") String operation, @RequestParam("idCar") Long idCar){

        Map<String, String> data = new HashMap<>();
        String status = RESPONSE_STATUS_FAILED;
        String message = "";

        Car car = carService.findById(idCar);

        if(car.getAgent().getId() == userService.getCurrentUser().getId()){
            if(date.isAfter(LocalDate.now())){
                if(amount >= 0){
                    if(EnumUtils.isValidEnum(CostEnum.class, operation)){
                        Cost cost = null;
                        switch (operation){
                            case "ASSURANCE" :
                                cost = new Assurance(date.atStartOfDay(), amount);
                                break;

                            case "TECHNICAL_VISIT" :
                                cost = new TechnicalVisit(date.atStartOfDay(), amount);
                                break;

                            case "CAR_STICKER" :
                                cost = new CarSticker(date.atStartOfDay(), amount);
                                break;
                        }

                        try {
                            costService.createCost(cost, car);

                            status = RESPONSE_STATUS_SUCCESS;
                            message = RESPONSE_MESSAGE_SUCCESS;

                        }catch (CostDateException | DataStoreException e){
                            message = e.getMessage();
                        }
                    }else
                        message = ERROR_COST_TYPE;

                }else
                    message = ERROR_COST_AMOUNT_INVALID;
            }else
                message = ERROR_COST_END_DATE_INVALID;

        }else
            message = ACCESS_DENIED;

        data.put("status", status);
        data.put("serverMessage", message);

        return new Gson().toJson(data);
    }
}
