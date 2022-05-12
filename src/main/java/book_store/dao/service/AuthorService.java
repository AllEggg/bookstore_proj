package book_store.dao.service;

import book_store.dao.entity.Author;
import book_store.dao.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
public class AuthorService {

    private final AuthorRepository repository;

    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }
    @Transactional
    public Author createAuthor(Author author) {
        return repository.save(author);
    }

    public List<Author> getAuthors() {
        return repository.findAll();
    }

    public Author getAuthorById(Long id) {
        return repository.getById(id);
    }

    public Author getAuthorByName(String name) {
        return repository.getAuthorByName(name);
    }

    public Boolean authorIfExist(String name) {
        return repository.existsAuthorByName(name);
    }

    public Boolean deleteAuthor(String name) {
        repository.delete(repository.getAuthorByName(name));
        return true;
    }
}
