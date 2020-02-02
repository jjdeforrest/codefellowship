package com.example.codefellowship.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;


@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Principal p, Model m) {
        if (p != null) {
            m.addAttribute("username", p.getName());
        }
        return "home";
    }

    @GetMapping("/sign-up")
    public String signUp() {
        return "signup";
    }

}