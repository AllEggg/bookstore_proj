package book_store.controller;

import book_store.dao.service.BookService;
import book_store.dao.service.WarehouseService;
import book_store.views.WarehouseView;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("restcontrol/warehouse")
public class WarehouseRestController {

    private final WarehouseService warehouseService;
    private final WarehouseView warehouseView;
    private final BookService bookService;

    public WarehouseRestController(WarehouseService warehouseService, WarehouseView warehouseView, BookService bookService) {
        this.warehouseService = warehouseService;
        this.warehouseView = warehouseView;
        this.bookService = bookService;
    }

    @GetMapping
    public List<WarehouseView> getWarehouse() {
        return warehouseView.mapToViewList(warehouseService.getWarehouse(), warehouseService);
    }

    @GetMapping("/{bookName}")
    public WarehouseView getBookWarehouse(@PathVariable("bookName") String name) {
        return warehouseView.mapToView(name, warehouseService, bookService);
    }

}
