package Person;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Entity
@ApplicationScoped
public class Book extends PanacheEntityBase {

    @Id
    @Schema(hidden=true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;

    private String author;

    private int pageCount;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Book setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public Book setAuthor(String author) {
        this.author = author;
        return this;
    }

    public int getPageCount() {
        return pageCount;
    }

    public Book setPageCount(int pageCount) {
        this.pageCount = pageCount;
        return this;
    }
}
