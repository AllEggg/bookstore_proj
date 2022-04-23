package book_store.dao.repository;


import book_store.dao.entity.Book;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    @Cacheable(value = "book_name")
    @Query(value = "select book_name from book where id = :id", nativeQuery = true)
    String getBookNameById(Integer id);

}
