package book_store.controller;


import book_store.dao.entity.Book;
import book_store.dao.filters.BookFilter;
import book_store.dao.service.AuthorService;
import book_store.dao.service.BookService;
import book_store.dao.service.WarehouseService;
import book_store.views.BookView;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("restcontrol/books")
public class BookRestController {

    private final BookService bookService;
    private final WarehouseService warehouseService;
    private final BookView view;
    private final AuthorService authorService;

    public BookRestController(BookService bookService, WarehouseService warehouseService, BookView view, AuthorService authorService) {
        this.bookService = bookService;
        this.warehouseService = warehouseService;
        this.view = view;
        this.authorService = authorService;
    }

    @GetMapping
    public List<BookView> getBooks(
            @RequestParam(value = "name", required = false) String name
    ) {
        if(name != null) {
            return bookService.getBookSpec(new BookFilter(name))
                    .stream()
                    .map((Book book) -> view.mapToView(bookService.getBookByName(name), warehouseService))
                    .collect(Collectors.toList());
        } else {
            return view.mapToViewList(bookService.getAllBooks(), warehouseService);
        }
    }

    @GetMapping("/{name}")
    public BookView getBook(@PathVariable("name") String name) {
        return view.mapToView(bookService.getBookByName(name), warehouseService);
    }


    @PostMapping
    public BookView addBook(@RequestBody BookView body) {
        Book book = view.mapFromView(body, authorService);
        if (bookService.bookIfExist(body.getName())) {
            throw new EntityExistsException(
                    String.format("Книга с названием = %$ уже существует", body.getName())
            );
        }

        Book newBook = bookService.addBook(book);
        return view.mapToView(newBook, warehouseService);
    }

    @PutMapping("/{bookName}")
    public BookView editBook(@PathVariable("bookName") String name, @RequestBody BookView body) {

        if (!bookService.bookIfExist(name)) {
            throw new EntityNotFoundException("Такой книги нет");
        } else if (!Objects.equals(name, body.getName())) {
            throw new RuntimeException("Ошибка названия");
        }

        Book book = bookService.getBookByName(name);

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
        return view.mapToView(edited, warehouseService);

    }

    @DeleteMapping("/{bookName}")
    public Boolean deleteBook(@PathVariable("bookName") String name) {
        return bookService.deleteBook(name);
    }

}
