package book_store.dao.repository;

import book_store.dao.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {

    @Query(value = "select * from order_details where book_order_id = :id", nativeQuery = true)
    List<OrderDetails> getAllByOrderId(Long id);

    Boolean existsOrderDetailsByBookIdAndAndBookOrderId(Long bookId, Long OrderId);

    @Query(value = "select book_quantity from order_details where book_order_id = :orderId and book_id = :bookId", nativeQuery = true)
    Integer getBookQuantityByBookOrderAndBookId(Long orderId, Long bookId);

//    OrderDetails getOrderDetailsByBookIdAndBookOrderId(Long bookId, Long orderId);

    @Modifying
    @Query(value = "update order_details set book_quantity = :quantity where id = :detailId", nativeQuery = true)
    void updateDetails(Long detailId, Integer quantity);

    @Query(value = "select * from order_details where id = :id", nativeQuery = true)
    OrderDetails getOptional(Long id);
}
