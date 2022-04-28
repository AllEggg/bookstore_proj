package book_store.dao.repository;

import book_store.dao.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;
@Repository
public interface BookWarehouseRepository extends JpaRepository<Warehouse, Integer> {

    @Query(value = "select book_quantity from warehouse where book_id = :id", nativeQuery = true)
    Integer getBooksQuantityById(Integer id);

    @Transactional
    @Modifying
    @Query(value = "update warehouse set book_quantity = :count where book_id = :id", nativeQuery = true)
    void changeBookQuantity(Integer count, int id);

    @Query(value = "select * from warehouse where book_id = :id",nativeQuery = true)
    Warehouse getWarehouseById(Integer id);

    List<Warehouse> findAll();


}
