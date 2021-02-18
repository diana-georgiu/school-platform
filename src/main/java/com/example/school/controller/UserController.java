package com.example.school.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("userInfo")
public class UserController {

    @GetMapping("/login")
    public String renderLoginPage(){
        return "loginUser";
    }

    @GetMapping("/logout")
    public String renderLogoutPage(){
        return "index";
    }

}
