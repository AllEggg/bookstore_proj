package book_store.dao.service;

import book_store.dao.entity.Author;
import book_store.dao.entity.Book;
import book_store.dao.repository.AuthorRepository;
import book_store.dao.repository.BookRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookService {

    private final BookRepository repository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository repository, AuthorRepository authorRepository) {

        this.repository = repository;

        this.authorRepository = authorRepository;
    }


    public String getNameById(Integer id) {
        return repository.getBookNameById(id);
    }


    public List<Book> getAllBooks() {

        return  repository.findAll();
    }

    public Book getBookById(Integer id) {
        return repository.getBookById(id);
    }
    @Transactional
    public Book addBook(Book book) {
        return repository.save(book);
    }

    public Boolean deleteBook(Integer id) {
        repository.delete(repository.getBookById(id));
        return true;
    }

    @Transactional
    public void changeAuthor(Author oldAuthor, String newAuthor) {
        repository.changeAuthor(oldAuthor.getId(), authorRepository.getAuthorByName(newAuthor).getId());
    }



}






