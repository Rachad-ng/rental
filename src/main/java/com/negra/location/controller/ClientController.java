package com.negra.location.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client")
public class ClientController {

    private static final String CLIENT_HOME = "client";

    @GetMapping("/home")
    public String clientHome(){
        return CLIENT_HOME;
    }

}
