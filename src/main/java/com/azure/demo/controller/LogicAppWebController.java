package com.azure.demo.controller;

import com.azure.demo.service.LogicAppClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LogicAppWebController {

    @Autowired
    private LogicAppClient logicAppClient;

    @GetMapping("/trigger")
    public String showForm(Model model) {
        return "logic-form";
    }

    @PostMapping("/trigger")
    public String handleForm(@RequestParam String employeeId, @RequestParam String action, Model model) {
        logicAppClient.sendToLogicApp(employeeId, action);
        model.addAttribute("message", "Triggered for Employee ID: " + employeeId);
        return "logic-form";
    }
}
