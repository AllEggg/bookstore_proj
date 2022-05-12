package book_store.controller;

import book_store.dao.entity.Author;
import book_store.dao.service.AuthorService;
import book_store.views.AuthorView;
import org.springframework.security.access.annotation.Secured;
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

    @GetMapping
    public List<AuthorView> getAllAuthors() {

        return authorView.mapToViewList(authorService.getAuthors());

    }


    @GetMapping("/{authorName}")
    public AuthorView getAuthorByName(@PathVariable("authorName") String authorName) {
        return authorView.mapToView(authorService.getAuthorByName(authorName));
    }


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


    @PutMapping("/{authorName}")
    public AuthorView editAuthor(@PathVariable("authorName") String name,
                                 @RequestBody AuthorView body) {
        if (!authorService.authorIfExist(name)) {
            throw new EntityNotFoundException("Такого автора нет");
        }

        Author author = authorService.getAuthorByName(name);

        if(!author.getName().equals(body.getName())) {
            author.setName(body.getName());
        }

        Author editedAuthor = authorService.createAuthor(author);
        return authorView.mapToView(editedAuthor);
    }


    @DeleteMapping("/{authorName}")
    public Boolean deleteAuthor(@PathVariable("authorName") String name) {
        return authorService.deleteAuthor(name);
    }


}
