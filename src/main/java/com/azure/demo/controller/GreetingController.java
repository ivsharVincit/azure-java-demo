package com.azure.demo.controller;

import com.azure.demo.model.Greeting;
import com.azure.demo.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class GreetingController {

    @Autowired
    private GreetingRepository greetingRepository;

   @GetMapping("/greetings")
    public List<Greeting> getAllGreetings() {
        return greetingRepository.findAll();
    }
    @GetMapping("/greet")
    public String greet(@RequestParam(defaultValue = "World") String name) {
        greetingRepository.save(new Greeting(name));
        return "Hello, " + name + "!";
    }
}