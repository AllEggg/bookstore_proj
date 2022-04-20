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
    @GeneratedValue
    @Column
    private int orderId;

    @Column
    private double orderPrice;

    @OneToOne
    private OrderDetails orderDetails;

    @ManyToOne
    @JoinColumn
    private Customer customer;

}
