package com.azure.demo.controller;

import com.azure.demo.model.Greeting;
import com.azure.demo.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class WebController {

    @Autowired
    private GreetingRepository greetingRepository;

    @GetMapping("/")
    public String home(Model model) {
        List<Greeting> greetings = greetingRepository.findAll();
        model.addAttribute("greetings", greetings);
        return "index"; // templates/index.html
    }
}