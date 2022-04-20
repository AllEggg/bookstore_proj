package book_store.dao.service;

import book_store.dao.entity.Author;
import book_store.dao.entity.Book;
import book_store.dao.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {

        this.repository = repository;
    }

    public List<Book> getBooksByAuthorId(Integer id) {
        return repository.getBooksByAuthor_AuthorId(id);

    }



}
