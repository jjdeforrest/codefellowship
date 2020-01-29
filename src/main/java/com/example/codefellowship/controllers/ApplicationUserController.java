package com.example.codefellowship.controllers;



import com.example.codefellowship.models.ApplicationUser;
import com.example.codefellowship.models.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import java.security.Principal;

@Controller
public class ApplicationUserController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public RedirectView createNewApplicationUser(String username, String password, String dateOfBirth, String bio, String firstName, String lastName) {
        System.out.println("You are adding a user");
        ApplicationUser newUser = new ApplicationUser(username, passwordEncoder.encode(password), dateOfBirth, bio, firstName, lastName);
        applicationUserRepository.save(newUser);
        return new RedirectView("/");
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }


    @GetMapping("/signup")
    public String showSignup() {
        return "signup";
    }

    @GetMapping("/profile")
    public String getHome(Principal p, Model m) {

        if (p != null) {
            m.addAttribute("username", p.getName());
        }

        return "profile";
    }


}