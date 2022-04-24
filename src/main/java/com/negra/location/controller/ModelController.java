package com.negra.location.controller;

import com.google.gson.Gson;
import com.negra.location.dto.ModelWithImageAndMarkDto;
import com.negra.location.service.implementations.ModelService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@AllArgsConstructor
@Controller
public class ModelController {

    private ModelService modelService;

    @PostMapping("/syncModelWithMark")
    public @ResponseBody
    String syncModelWithMark(@RequestParam long idMark){
        List<ModelWithImageAndMarkDto> modelWithImageAndMarkDtos = modelService.getByMark(idMark);
        return new Gson().toJson(modelWithImageAndMarkDtos);
    }

}
