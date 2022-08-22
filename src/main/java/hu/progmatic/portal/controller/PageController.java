package hu.progmatic.portal.controller;

import hu.progmatic.portal.model.Page;
import hu.progmatic.portal.repository.PageRepository;
import hu.progmatic.portal.service.PageUserDetailsService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PageController {
    private final PageRepository pageRepository;

    private final PageUserDetailsService userDetailsService;

    public PageController(PageRepository pageRepository, PageUserDetailsService userDetailsService) {
        this.pageRepository = pageRepository;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/")
    public String homePage() {
        System.out.println(userDetailsService.getLoggedInUser());

        // https://www.baeldung.com/spring-security-thymeleaf
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

    @GetMapping("/pages/public")
    public String listPublicPages(Model model) {
        List<Page> pages = pageRepository.findByInternalOrderByTitle(false);
        model.addAttribute("pages", pages);

        return "pages";
    }

    @GetMapping("/pages/internal")
    public String listInteralPages(Model model) {
        List<Page> pages = pageRepository.findByInternalOrderByTitle(true);
        model.addAttribute("pages", pages);

        return "pages";
    }

}
