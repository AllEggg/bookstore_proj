package book_store.controller;

import book_store.dao.entity.Author;
import book_store.dao.service.AuthorService;
import book_store.views.AuthorView;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("restcontrol/authors")
public class AuthorRestController {

    private final AuthorService authorService;
    private final AuthorView authorView;

    public AuthorRestController(AuthorService authorService, AuthorView authorView) {
        this.authorService = authorService;
        this.authorView = authorView;
    }

    // get all authors
    @GetMapping
    public List<AuthorView> getAllAuthors() {

        return authorView.mapToViewList(authorService.getAuthors());

    }

    // get author by name
    @GetMapping("/{authorName}")
    public AuthorView getAuthorByName(@PathVariable("authorName") String authorName) {
        return authorView.mapToView(authorService.getAuthorByName(authorName));
    }

    // add new author
    @PostMapping
    public AuthorView addAuthor(@RequestBody AuthorView body) {
        if (authorService.authorIfExist(body.getName())) {
            throw new EntityExistsException(String
                    .format("Автор с именем %$ уже существует", body.getName()));
        } else {
            Author author = authorView.mapFromView(body);
            Author addedAuthor = authorService.createAuthor(author);
            return authorView.mapToView(addedAuthor);
        }

    }

    // edit author
    @PutMapping("/{authorName}")
    public AuthorView editAuthor(@PathVariable("authorName") String name,
                                 @RequestBody AuthorView body) {
        if (authorService.authorIfExist(body.getName())) {
            throw new EntityNotFoundException("Такого автора нет");
        } else if (!Objects.equals(name, body.getName())) {
            throw new RuntimeException("Ошибка названия");
        }

        Author author = authorService.getAuthorByName(name);

        if(!author.getName().equals(body.getName())) {
            author.setName(body.getName());
        }

        Author editedAuthor = authorService.createAuthor(author);
        return authorView.mapToView(editedAuthor);
    }

    // delete author
    @DeleteMapping("/{authorName}")
    public Boolean deleteAuthor(@PathVariable("authorName") String name) {
        return authorService.deleteAuthor(name);
    }


}
