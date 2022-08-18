package hu.progmatic.portal.command;

import hu.progmatic.portal.model.Page;
import hu.progmatic.portal.model.User;
import hu.progmatic.portal.repository.PageRepository;
import hu.progmatic.portal.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class TestDataCommand implements CommandLineRunner {
    private final PageRepository pageRepository;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public TestDataCommand(
            PageRepository pageRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.pageRepository = pageRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Generating test pages...");

        pageRepository.save(new Page("about", "About page", "Hello on the about page!"));
        System.out.println("About page generated.");

        pageRepository.save(new Page("products", "Products page", "Hello on the products page!"));
        System.out.println("Products page generated.");

        pageRepository.save(new Page("internal", "Internal page",
                "Hello on the internal page!", true));
        System.out.println("Internal page generated.");

        System.out.println("Generating test users...");

        userRepository.save(new User("user", passwordEncoder.encode("password")));
        System.out.println("User user generated.");

        userRepository.save(new User("admin", passwordEncoder.encode("password"), true));
        System.out.println("Admin user generated.");
    }
}
