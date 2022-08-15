package hu.progmatic.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping("/profile")
    public String profilePage() {
        return "profile";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }

}
