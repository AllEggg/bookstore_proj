package book_store.dao.repository;

import book_store.dao.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {
}
