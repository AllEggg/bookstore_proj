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
public interface BookRepository extends JpaRepository<Book, Integer> {


    List<Book> findAll(Specification<Book> specification);


    @Query(value = "select id from book where name = :name", nativeQuery = true)
    Integer getIdByName(String name);

    @Cacheable(value = "book_name")
    @Query(value = "select name from book where id = :id", nativeQuery = true)
    String getBookNameById(Integer id);

    Book getBookById(Integer id);

    @Query(value = "update book set author_id = :newId where author_id = :oldId;", nativeQuery = true)
    void changeAuthor(Integer oldId, Integer newId);

    Boolean existsBookByName(String name);

    Book getBookByName(String name);


}
