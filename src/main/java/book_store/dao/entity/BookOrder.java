package book_store.dao.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class BookOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private double orderPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @OneToOne
    @JoinColumn
    private OrderDetails details;

}
