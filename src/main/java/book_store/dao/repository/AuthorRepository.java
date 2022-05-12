package book_store.dao.repository;

import book_store.dao.entity.Author;
import book_store.dao.entity.Book;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
//    @Query(value = "select * from author where name = :name", nativeQuery = true)
    Author getAuthorByName(String name);

    Boolean existsAuthorByName(String name);

}
