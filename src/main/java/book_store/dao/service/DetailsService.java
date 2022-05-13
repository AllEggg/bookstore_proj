package book_store.dao.service;

import book_store.dao.entity.Book;
import book_store.dao.entity.OrderDetails;
import book_store.dao.repository.OrderDetailsRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.Modifying;
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

    public Double getPrice(Long bookId, int quantity) {
        return quantity * bookService.getBookPrice(bookId);
    }

    public List<OrderDetails> getListDetailsByOrderId(Long id) {
        return repository.getAllByOrderId(id);
    }

    public OrderDetails getDetailsByBookId(Long bookId, Long orderId) {
        List<OrderDetails> orderDetailsList = repository.getAllByOrderId(orderId);
        OrderDetails resultDetails = new OrderDetails();
        for (OrderDetails details:orderDetailsList) {
            if (details.getBookId().equals(bookId)) {
                resultDetails = details;
            }
        }

        return resultDetails;
    }

    public OrderDetails getDetailsByDetailsId(Long id) {
        return repository.getById(id);
    }

    @Transactional
    public OrderDetails add(OrderDetails orderDetails) {
        return repository.save(orderDetails);
    }

    public Boolean existBookInOrder(Long bookId, Long orderId) {
        List<OrderDetails> orderDetailsList = repository.getAllByOrderId(orderId);
        Boolean value = false;
        for (OrderDetails details:orderDetailsList) {
            if (details.getBookId().equals(bookId)) {
                value = true;
            }
        }
        return value;
    }

    public Integer getBookQuantityByOrderId(Long orderId, Long bookId) {
        return repository.getBookQuantityByBookOrderAndBookId(orderId, bookId);
    }

    @Transactional
    public Boolean delete(Long id) {
        repository.delete(repository.getById(id));
        return true;
    }

    @Modifying
    @Transactional
    public OrderDetails updateQuantity(@NotNull OrderDetails orderDetails, Integer quantity) {

        repository.updateDetails(orderDetails.getId(), quantity);
        return repository.getOptional(orderDetails.getId());
    }
}
