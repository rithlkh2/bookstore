package np.com.mshrestha.bookstore.model;

import javax.persistence.*;

/**
 * Created by Ron Rith on 5/24/2017.
 */
@Entity
@Table(name = "hi_book_test")
public class Book2Test {
    private Long id;
    private String code;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Column(name = "code",nullable = false)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
