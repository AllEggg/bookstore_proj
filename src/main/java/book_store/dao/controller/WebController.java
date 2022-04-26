package book_store.dao.controller;

import book_store.dao.entity.Book;
import book_store.dao.entity.Warehouse;
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
    private final WarehouseService warehouseService;

    public WebController(BookService bookService, WarehouseService warehouseService) {
        this.bookService = bookService;
        this.warehouseService = warehouseService;
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





