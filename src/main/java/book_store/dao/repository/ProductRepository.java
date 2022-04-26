package book_store.dao.repository;

import book_store.dao.entity.Customer;
import book_store.dao.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {


        @Transactional
        @Modifying
        @Query(value = "update product set name = :name where id = :id", nativeQuery = true)
        void setName(String name, int id);

        @Query(value = "select * from product where id = ?1", nativeQuery = true)
        Product getProductById(Integer id);

}
