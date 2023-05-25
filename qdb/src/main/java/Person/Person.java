package Person;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;

import java.util.List;

@Entity
@ApplicationScoped
public class Person extends PanacheEntityBase {

    public Person() {}

    @Id
//    @Schema(hidden = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int pk_id;

    private String f_name;

    private String l_name;

    private String address;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Book> likedBooks;

    public List<Book> getLikedBooks() {
        return likedBooks;
    }

    public Person setLikedBooks(List<Book> likedBooks) {
        this.likedBooks = likedBooks;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Person setAddress(String address) {
        this.address = address;
        return this;
    }

    public int getPk_id() {
        return pk_id;
    }

    public String getF_name() {
        return f_name;
    }

    public Person setF_name(String f_name) {
        this.f_name = f_name;
        return this;
    }

    public String getL_name() {
        return l_name;
    }

    public Person setL_name(String l_name) {
        this.l_name = l_name;
        return this;
    }
}