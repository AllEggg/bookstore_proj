package book_store.dao.repository;

import book_store.dao.entity.Product;
import book_store.dao.entity.Warehouse;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;

import javax.persistence.LockModeType;
import javax.persistence.NamedNativeQuery;
import javax.transaction.Transactional;


public interface BookWarehouseRepository extends JpaRepository<Warehouse, Integer> {

    @Query(value = "select book_quantity from warehouse where book_id_id = :id", nativeQuery = true)
    Integer getBooksQuantityById(Integer id);

    @Transactional
    @Modifying
    @Query(value = "update warehouse set book_quantity = :count where book_id_id = :id", nativeQuery = true)
    void sellBook(Integer count, int id);

    @Query(value = "select * from warehouse where book_id_id = :id",nativeQuery = true)
    Warehouse getWarehouseById(Integer id);


}
