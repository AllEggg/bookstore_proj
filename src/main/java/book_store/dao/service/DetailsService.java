package book_store.dao.service;

import book_store.dao.entity.Book;
import book_store.dao.entity.OrderDetails;
import book_store.dao.repository.OrderDetailsRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DetailsService {
    private final OrderDetailsRepository repository;
    private final BookService bookService;

    public DetailsService(OrderDetailsRepository repository, BookService bookService) {
        this.repository = repository;
        this.bookService = bookService;
    }

    public Set<Book> createBookSet(Set<String> nameSet) {
        Set<Book> bookSet = new HashSet<>();
        for (String name : nameSet) {
            Book book = bookService.getBookByName(name);
            bookSet.add(book);
        }
        return bookSet;
    }

    public Double getPrice(Long bookId, int quantity) {
        return quantity * bookService.getBookPrice(bookId);
    }


    public List<OrderDetails> getListDetailsByOrderId(Long id) {

        return repository.getAllByOrderId(id);
    }

    public OrderDetails getDetailsByOrderId(Long id) {
        return repository.getById(id);
    }

    @Transactional
    public OrderDetails add(OrderDetails orderDetails) {
        return repository.save(orderDetails);
    }

    public Boolean existBookInOrder(Long bookId, Long orderId) {
        return repository.existsOrderDetailsByBookIdAndAndBookOrderId(bookId, orderId);
    }

    public Integer getBookQuantityByOrderId(Long orderId) {
        return repository.getBookQuantityByOrderId(orderId);
    }

    @Transactional
    public Boolean delete(Long id) {
        repository.delete(repository.getById(id));
        return true;
    }
}
