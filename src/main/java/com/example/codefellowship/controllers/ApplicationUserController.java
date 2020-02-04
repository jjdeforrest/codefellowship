package com.example.codefellowship.controllers;



import com.example.codefellowship.models.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.view.RedirectView;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ApplicationUserController {

    @Autowired
    public CommentRepository commentRepository;


    @Autowired
    public ApplicationUserRepository applicationUserRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @PostMapping("/join")
    public RedirectView createUser(String firstName, String lastName, String dateOfBirth, String bio, String username, String password) {
        ApplicationUser newUser = new ApplicationUser(username, passwordEncoder.encode(password), firstName, lastName, dateOfBirth, bio);
        applicationUserRepository.save(newUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new RedirectView("/myprofile");
    }

    @GetMapping("/login")
    public String loginForm() {

        return "login";
    }

    @PostMapping("/login")
    public RedirectView authententicatedUser(Principal p) {
        if (p != null) {
            ApplicationUser loggedUser = applicationUserRepository.findByUsername(p.getName());
            return new RedirectView("/user/" + loggedUser.getId());
        } else {
            return new RedirectView("/login");
        }
    }

    @GetMapping("/myprofile")
    public RedirectView getLoggedInUsersId(Principal p, Model m) {
        ApplicationUser loggedInUser = applicationUserRepository.findByUsername(p.getName());
        return new RedirectView("/user/" + loggedInUser.id);
    }

    @GetMapping("/user/{id}")
    public String userDetails(@PathVariable Long id, Principal p, Model m) {
        ApplicationUser user = applicationUserRepository.findById(id).get();
        m.addAttribute("user", user);
        m.addAttribute("principal", p.getName());
        m.addAttribute("comment",commentRepository);
        return "profile";
    }





}


