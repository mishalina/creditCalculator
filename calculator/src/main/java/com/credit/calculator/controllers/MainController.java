package com.credit.calculator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;

@Controller
public class MainController implements WebMvcConfigurer {
    @GetMapping("/index")
    public String showForm(Count form) {
        return "index";
    }
    @PostMapping("/index")
    public String startSubmit(@Valid Count form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "index";
        }
        Count.countRes();
        return "result";
    }
}
