package hu.progmatic.portal.command;

import hu.progmatic.portal.model.Page;
import hu.progmatic.portal.repository.PageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestDataCommand implements CommandLineRunner {
    private final PageRepository pageRepository;

    public TestDataCommand(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
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
    }
}
