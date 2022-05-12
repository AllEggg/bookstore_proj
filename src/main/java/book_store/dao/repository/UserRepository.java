package book_store.dao.repository;

import book_store.dao.entity.BookStoreUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<BookStoreUser, Long> {

    BookStoreUser findByUsername(String userName);

}
