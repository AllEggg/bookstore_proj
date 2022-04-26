package book_store.dao.service;

import book_store.dao.entity.Book;
import book_store.dao.entity.Warehouse;
import book_store.dao.repository.BookRepository;
import book_store.dao.repository.BookWarehouseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository repository;
    private final WarehouseService warehouseService;

    public BookService(BookRepository repository, WarehouseService warehouseService) {

        this.repository = repository;

        this.warehouseService = warehouseService;
    }


    public String getNameById(Integer id) {
        return repository.getBookNameById(id);
    }

    public Integer getBookCount() {
        return repository.countBooks();
    }


    public List<Book> getAllBooks() {

        return  repository.findAll();
    }

    }






