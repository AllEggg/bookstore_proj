package book_store.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class BookOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private double orderPrice;


    @ManyToOne(fetch = FetchType.LAZY)
    private BookStoreUser customer;


    @OneToMany(mappedBy = "bookOrder", cascade = CascadeType.ALL)
    private List<OrderDetails> orderDetails;

    public void addDetails(OrderDetails orderDetail){
        this.orderDetails.add(orderDetail);
        orderDetail.setBookOrder(this);
    }
    public void removeDetails(OrderDetails orderDetail){
        this.orderDetails.remove(orderDetail);
        orderDetail.setBookOrder(null);
    }


    }

