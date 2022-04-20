package book_store.dao.repository;


import book_store.dao.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> getBooksByAuthor_AuthorId(Integer id);

}
