package book_store.dao.service;

import book_store.dao.entity.Warehouse;
import book_store.dao.repository.BookWarehouseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WarehouseService {

    private final BookWarehouseRepository repository;


    public WarehouseService(BookWarehouseRepository repository) {
        this.repository = repository;
    }

    public int getBooksCount(Integer id) {
        return repository.getBooksQuantityById(id);
    }


    @Transactional
    public void purchaseBook(Integer id, Warehouse warehouse) {
        int count = getBooksCount(id) - 1;
        if (count >= 0) {
            repository.sellBook(count, id);
        } else {
            throw new RuntimeException("К сожалению, книги закончились");
        }


    }

    public Warehouse getWarehouseById(Integer id) {
        return repository.getWarehouseById(id);
    }


    public void save(Warehouse warehouse) {
        repository.save(warehouse);
    }
}



