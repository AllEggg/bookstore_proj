package book_store.dao.repository;

import book_store.dao.entity.BookOrder;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookOrderRepository extends JpaRepository<BookOrder, Integer> {
}
