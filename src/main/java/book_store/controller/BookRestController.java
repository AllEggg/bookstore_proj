package book_store.controller;


import book_store.dao.entity.Book;
import book_store.dao.service.BookService;
import book_store.views.BookView;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("restcontrol/bookstore")
public class BookRestController {

    private final BookService bookService;
    private final BookView view;

    public BookRestController(BookService bookService, BookView view) {
        this.bookService = bookService;
        this.view = view;
    }

    @GetMapping
    public List<BookView> getBooks(@RequestParam(value = "name", required = false) String name) {
        return view.mapToViewList(bookService.getAllBooks());
    }

    @GetMapping("/{bookId}")
    public BookView getBook(@PathVariable("bookId") Integer id) {
        return view.mapToView(bookService.getBookById(id));
    }
    // add book
    @PostMapping
    public BookView addBook(@RequestBody BookView body) {
        if (body.getId() != null) {
            throw new EntityExistsException(
                    String.format("Книга с id = %$ уже существует", body.getId())
            );
        }
        Book book = view.mapFromView(body);
        Book newBook = bookService.addBook(book);
        return view.mapToView(newBook);
    }

    @PutMapping("/{bookId}")
    public BookView editBook(@PathVariable("bookId") Integer id, @RequestBody BookView body) {

        if (body.getId() == null) {
            throw new EntityNotFoundException("Такой книги нет");
        } else if (!Objects.equals(id, body.getId())) {
            throw new RuntimeException("Идентификаторы не совпадают");
        }

        Book book = bookService.getBookById(id);

        if (!book.getName().equals(body.getName())) {
            book.setName(body.getName());
        }
        if (book.getPageQuantity() != body.getPageQuantity()) {
            book.setPageQuantity(body.getPageQuantity());
        }
        if (book.getPrice() != body.getPrice()) {
            book.setPrice(body.getPrice());
        }
        if (!book.getPublishYear().equals(body.getPublishYear())) {
            book.setPublishYear(body.getPublishYear());
        }
        if (!book.getAuthor().getName().equals(body.getAuthorName())) {
            bookService.changeAuthor(book.getAuthor(), body.getAuthorName());
        }
        Book edited = bookService.addBook(book);
        return view.mapToView(edited);

    }

    @DeleteMapping("{bookId}")
    public Boolean deleteBook(@PathVariable("bookId") Integer id) {
        return bookService.deleteBook(id);
    }

}
