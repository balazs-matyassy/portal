package hu.progmatic.portal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Page {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String slug;

    private String title;

    private String content;

    private boolean internal;

    // Java 7
    // java.util.Date
    // GregorianCalendar
    // Java 8+
    // LocalDate: csak dátum
    // LocalDateTime: dátum + idő
    // OffsetDateTime: dátum + idő (UTC+=x)
    // ZonedDateTime: dátum + idő (pontos időzóna)
    private LocalDate created;

    public Page() {

    }

    public Page(String slug, String title, String content) {
        this.slug = slug;
        this.title = title;
        this.content = content;
        this.created = LocalDate.now();
    }

    public Page(String slug, String title, String content, boolean internal) {
        this.slug = slug;
        this.title = title;
        this.content = content;
        this.internal = internal;
        this.created = LocalDate.now();
    }

    public Page(Long id, String slug, String title, String content, boolean internal, LocalDate created) {
        this.id = id;
        this.slug = slug;
        this.title = title;
        this.content = content;
        this.internal = internal;
        this.created = created;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isInternal() {
        return internal;
    }

    public void setInternal(boolean internal) {
        this.internal = internal;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Page{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", internal=" + internal +
                ", created=" + created +
                '}';
    }
}
