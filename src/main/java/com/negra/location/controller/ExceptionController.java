package com.negra.location.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExceptionController {

    private static final String ExceptionPage = "500";

    @RequestMapping("/exp")
    public String exceptionHandling(){
        return ExceptionPage;
    }

}
