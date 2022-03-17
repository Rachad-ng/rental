package com.negra.location.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/agent")
public class AgentController {

    private static final String AGENT_HOME = "agent";

    @RequestMapping("/home")
    public String agentHome(){
        return AGENT_HOME;
    }

}
