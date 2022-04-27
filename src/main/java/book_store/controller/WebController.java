package book_store.controller;

import book_store.dao.entity.Book;
import book_store.dao.service.BookService;
import book_store.dao.service.WarehouseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@Controller
@RequestMapping("/bookstore")
public class WebController {

    private final BookService bookService;

    public WebController(BookService bookService) {
        this.bookService = bookService;
    }

    // http://localhost:8080/bookstore/home
    @GetMapping("/home")
    public String home(Model model) {
        return "home";
    }

    // http://localhost:8080/bookstore/books
    @GetMapping("/books")
    public String books(Model model) {
        List<Book> allBooks = bookService.getAllBooks();

        model.addAttribute("books", allBooks);
        return "books";
    }

    // http://localhost:8080/bookstore/underConstruction
    @GetMapping("/underConstruction")
    public String underConstruction(Model  model) {
        return "underConstruction";
    }

    }





