package book_store.dao.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Book {

    @Id
    @GeneratedValue
    private Integer bookId;

    @Column
    private String bookName;

    @Column
    private Date publishYear;

    @Column
    private int pageQuantity;

    @Column
    private double price;



    @ManyToOne
    @JoinColumn
    @Fetch(FetchMode.JOIN)
    private Warehouse warehouse;

    @OneToOne(mappedBy = "book", fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    private OrderDetails orderDetails;

    @ManyToOne
    @JoinColumn
    @Fetch(FetchMode.JOIN)
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

