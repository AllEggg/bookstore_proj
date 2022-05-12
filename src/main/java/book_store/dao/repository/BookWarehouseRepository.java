package book_store.dao.repository;

import book_store.dao.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;
@Repository
public interface BookWarehouseRepository extends JpaRepository<Warehouse, Long> {

    @Query(value = "select book_quantity from warehouse where book_id = :id", nativeQuery = true)
    Integer getBooksQuantityById(Long id);

    @Transactional
    @Modifying
    @Query(value = "update warehouse set book_quantity = :count where book_id = :id", nativeQuery = true)
    void changeBookQuantity(Long id, Integer count);

    @Query(value = "select * from warehouse where book_id = :id",nativeQuery = true)
    Warehouse getWarehouseById(Long id);

    List<Warehouse> findAll();


}
