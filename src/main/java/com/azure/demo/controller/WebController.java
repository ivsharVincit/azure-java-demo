package com.azure.demo.controller;

import com.azure.demo.model.Greeting;
import com.azure.demo.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.data.domain.Sort;

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

    @GetMapping("/form")
    public String showForm(Model model) {
        List<Greeting> greetings = greetingRepository.findAll();
        model.addAttribute("greetings", greetings);
        return "form";
    }

    @PostMapping("/submit")
    public String submitGreeting(@RequestParam String name, RedirectAttributes redirectAttributes) {
        if (name == null || name.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Name cannot be empty.");
            return "redirect:/form";
        }

        greetingRepository.save(new Greeting(name.trim()));
        redirectAttributes.addFlashAttribute("message", "Hello, " + name + "! Saved successfully.");
        return "redirect:/form";
    }

    @PostMapping("/delete/{id}")
    public String deleteGreeting(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        greetingRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Entry deleted successfully.");
        return "redirect:/form";
    }
}

