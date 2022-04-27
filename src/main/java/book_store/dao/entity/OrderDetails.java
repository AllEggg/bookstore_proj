package book_store.dao.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class OrderDetails {
    @Id
    private int id;

    @Column
    private int bookId;

    @Column
    private int bookQuantity;

    @Column
    private double orderPrice;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn
    private BookOrder bookOrder;


}
