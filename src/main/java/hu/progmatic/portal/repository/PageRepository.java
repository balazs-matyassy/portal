package hu.progmatic.portal.repository;

import hu.progmatic.portal.model.Page;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PageRepository extends CrudRepository<Page, Long> {

    // SELECT * FROM page WHERE slug = :slug;
    Optional<Page> findBySlug(String slug);

    // SELECT * FROM page WHERE internal = :internal ORDER BY title;
    List<Page> findByInternalOrderByTitle(boolean internal);

}
