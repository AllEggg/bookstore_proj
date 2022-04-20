package book_store.dao.repository;

import book_store.dao.entity.Author;
import book_store.dao.entity.Book;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;
import java.util.Optional;


public interface AuthorRepository extends JpaRepository<Author, Integer> {



 //   @Query(value = "select * from author a  join book b on a.author_id = ?1", nativeQuery = true)
    @Cacheable(value = "book")
    @Query(value = "select book_name from book where author_author_id = ?1", nativeQuery = true)
    List<String> findAllBooksByAuthor(Integer id);
    @Cacheable(value = "author")
    @Query(value = "select name, surname, fathers_name from author where author_id = ?1", nativeQuery = true)
    List<String> getAuthorName(Integer id);


}
