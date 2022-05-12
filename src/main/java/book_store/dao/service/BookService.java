package book_store.dao.service;

import book_store.dao.entity.Author;
import book_store.dao.entity.Book;
import book_store.dao.repository.AuthorRepository;
import book_store.dao.repository.BookRepository;
import book_store.dao.filters.BookFilter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static book_store.dao.specifications.BookSpecification.byFilter;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class BookService {

    private final BookRepository repository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository repository, AuthorRepository authorRepository) {

        this.repository = repository;

        this.authorRepository = authorRepository;
    }

    public String getNameById(Long id) {
        return repository.getBookNameById(id);
    }

    public List<Book> getAllBooks() {

        return  repository.findAll();
    }
    public Book getBookByName(String name) {
        return repository.getBookByName(name);
    }

    public Long getBookIdByName(String name) {
        return repository.getIdByName(name);
    }

    public Book getBookById(Long id) {
        return repository.getBookById(id);
    }

    @Transactional
    public Book addBook(Book book) {
        return repository.save(book);
    }

    public Boolean deleteBook(String name) {
        repository.delete(repository.getBookByName(name));
        return true;
    }

    public List<Book> getBookSpec(BookFilter spec) {
        Specification<Book> specification = where(byFilter(spec));
        return repository.findAll(specification);
    }

    @Transactional
    public void changeAuthor(Author oldAuthor, String newAuthor) {
        repository.changeAuthor(oldAuthor.getId(), authorRepository.getAuthorByName(newAuthor).getId());
    }

    public Boolean bookIfExist(String name) {
        return repository.existsBookByName(name);

    }

    public Double getBookPrice(Long id) {
        return repository.getPriceById(id);
    }

}






