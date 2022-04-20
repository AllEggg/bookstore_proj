package book_store.dao.repository;

import book_store.dao.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookWarehouseRepository extends JpaRepository<Warehouse, Integer> {
}
