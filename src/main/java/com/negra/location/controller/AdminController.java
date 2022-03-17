package com.negra.location.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final String ADMIN_HOME = "admin";

    @RequestMapping("/home")
    public String adminHome(){
        return ADMIN_HOME;
    }

}
