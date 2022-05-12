package book_store.dao.repository;


import book_store.dao.entity.Book;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {


    List<Book> findAll(Specification<Book> specification);


    @Query(value = "select id from book where name = :name", nativeQuery = true)
    Long getIdByName(String name);

    @Query(value = "select name from book where id = :id", nativeQuery = true)
    String getBookNameById(Long id);

    Book getBookById(Long id);

    @Query(value = "update book set author_id = :newId where author_id = :oldId;", nativeQuery = true)
    void changeAuthor(Long oldId, Long newId);

    Boolean existsBookByName(String name);

    Book getBookByName(String name);

    @Query(value = "select price from book where id = :id", nativeQuery = true)
    Double getPriceById(Long id);


}
