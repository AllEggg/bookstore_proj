package book_store.dao.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class OrderDetails {
    @Id
    @GeneratedValue
    private int orderId;

    @Column
    private int bookId;

    @Column
    private int bookQuantity;

    @Column
    private double orderPrice;

    @OneToOne(orphanRemoval = true)
    private BookOrder bookOrder;

    @OneToOne(orphanRemoval = true)
    @Fetch(FetchMode.JOIN)
    private Book book;

}
