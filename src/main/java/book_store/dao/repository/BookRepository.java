package book_store.dao.repository;


import book_store.dao.entity.Book;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {


    List<Book> findAll();

    @Cacheable(value = "book_name")
    @Query(value = "select name from book where id = :id", nativeQuery = true)
    String getBookNameById(Integer id);

    Book getBookById(Integer id);




    @Query(value = "select count(*) from book",nativeQuery = true)
    Integer countBooks();

}
