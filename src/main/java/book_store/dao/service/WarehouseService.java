package book_store.dao.service;

import book_store.dao.repository.BookWarehouseRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class WarehouseService {

    private final BookWarehouseRepository repository;


    public WarehouseService(BookWarehouseRepository repository) {
        this.repository = repository;
    }

    public int getBooksCount(Integer id) {
        return repository.getBooksQuantityById(id);
    }

    public void purchaseBook(Integer id) {
        int count = getBooksCount(id) - 1;
        if (count >= 0) {
        repository.sellBook(count, id);
        } else {
            throw new RuntimeException("книг нет");
        }




}}



