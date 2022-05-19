package book_store.dao.repository;

import book_store.dao.entity.BookOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookOrderRepository extends JpaRepository<BookOrder, Long> {

    List<BookOrder> findAll();

    @Query(value = "select * from book_order where customer_id = :id",nativeQuery = true)
    List<BookOrder> findAllByCustomer(Long id);
}
