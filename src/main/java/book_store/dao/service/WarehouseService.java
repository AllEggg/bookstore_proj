package book_store.dao.service;

import book_store.dao.entity.Warehouse;
import book_store.dao.repository.BookWarehouseRepository;
import book_store.exeptions.OutOfStockExeption;
import book_store.report.MessageSender;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.util.List;

@Service
public class WarehouseService {

    private final BookWarehouseRepository repository;
    private final BookService bookService;
    private final MessageSender messageSender;


    public WarehouseService(BookWarehouseRepository repository, BookService bookService, MessageSender messageSender) {
        this.repository = repository;
        this.bookService = bookService;
        this.messageSender = messageSender;
    }

    public int getBooksCount(Long id) {
        return repository.getBooksQuantityById(id);
    }

    @Transactional
    @Lock(value = LockModeType.READ)
    public void refillBook(String name, Integer refillQuantity) {
        Long bookId = bookService.getBookIdByName(name);
        Integer quantity = repository.getBooksQuantityById(bookId)
                + refillQuantity;
        repository.changeBookQuantity(bookId, quantity);
    }

    @Transactional
    @Lock(LockModeType.READ)
    public void sellBook(String name, Integer sellQuantity) {
        Long bookId = bookService.getBookIdByName(name);
        if ((repository.getBooksQuantityById(bookId) - sellQuantity) < 0) {
            throw new OutOfStockExeption();
        } else {
            int quantity = repository.getBooksQuantityById(bookId) - sellQuantity;
            repository.changeBookQuantity(bookId, quantity);
            messageSender.sellReportMessage(sellQuantity, bookId);
        }
    }
    public List<Warehouse> getWarehouse() {
        return repository.findAll();
    }
    public Long getBookIdByName(String name) {
        return bookService.getBookIdByName(name);
    }

    public void save(Warehouse warehouse) {
        repository.save(warehouse);
    }

    public String getBookNameByIdWarehouse(Long id) {
        return bookService.getNameById(id);
    }



    //    public void purchaseBook(Integer id, Warehouse warehouse) {
//        int count = getBooksCount(id) - 1;
//        if (count >= 0) {
//            repository.sellBook(count, id);
//        } else {
//            throw new RuntimeException("?? ??????????????????, ?????????? ??????????????????????");
//        }
//    }
}



