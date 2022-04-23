package book_store.dao.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String bookName;

    @Column
    private Date publishYear;

    @Column
    private int pageQuantity;

    @Column
    private double price;


    @ManyToOne(fetch = FetchType.LAZY)
    private Author author;


    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                ", publishYear=" + publishYear +
                ", pageQuantity=" + pageQuantity +
                ", price=" + price +
                '}';
    }
}

