package book_store.dao.repository;

import book_store.dao.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {

    @Query(value = "select * from order_details where book_order_id = :id", nativeQuery = true)
    List<OrderDetails> getAllByOrderId(Long id);

    Boolean existsOrderDetailsByBookIdAndAndBookOrderId(Long bookId, Long OrderId);

    @Query(value = "select book_quantity from order_details where book_order_id = :id", nativeQuery = true)
    Integer getBookQuantityByOrderId(Long id);
}
