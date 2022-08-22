package hu.progmatic.portal.controller;

import hu.progmatic.portal.model.Page;
import hu.progmatic.portal.repository.PageRepository;
import hu.progmatic.portal.service.PageUserDetailsService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

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
        // https://www.baeldung.com/spring-security-login

        // FRAGMENTS: (html újrahasznosítása több oldalon: pl. menü, fejléc...)
        // https://www.baeldung.com/spring-thymeleaf-fragments
        // hierarchical: közös layout, változó tartalom
        // https://www.thymeleaf.org/doc/articles/layouts.html

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

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/login-error")
    public String loginErrorPage(Model model) {
        model.addAttribute("loginError", true);

        return "login";
    }

    // https://www.baeldung.com/spring-controller-vs-restcontroller
    // REST - representational state transfer
    // 1. Frontend framework segítségével generáljuk a HTML-t.
    // Angular, React, Vue...
    // 2. M2M (pl. egy szenzor küld adatokat az alkalmazásunkank), Smart home...
    // 3. Pl. Android alkalmazások is el tudják érni az adatokat.

    // @RestControllerben nincs szükség @ResponseBody annotációra,
    // mert az a default.

    @GetMapping(value = "/pages/{slug}", produces = "application/json")
    public @ResponseBody Page getPage(@PathVariable String slug) {
        Optional<Page> page = pageRepository.findBySlug(slug);
        return page.orElseThrow();
    }

}
