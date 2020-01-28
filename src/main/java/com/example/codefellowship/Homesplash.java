package com.example.codefellowship;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class Homesplash {


    @GetMapping
    public String getHome(Model m){
        return "home";

    }


}
