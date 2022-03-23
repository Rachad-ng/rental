package com.negra.location.controller;

import com.google.gson.Gson;
import com.negra.location.dto.ModelDto;
import com.negra.location.service.implementations.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ModelController {

    @Autowired
    private ModelService modelService;

    @PostMapping("/syncModelWithMark")
    public @ResponseBody
    String syncModelWithMark(@RequestParam long idMark){
        List<ModelDto> modelDtos = modelService.getByMark(idMark);
        return new Gson().toJson(modelDtos);
    }

}
