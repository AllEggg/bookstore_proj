package book_store.dao.service;


import book_store.dao.entity.Book;
import book_store.dao.entity.BookOrder;
import book_store.dao.entity.OrderDetails;
import book_store.dao.repository.BookOrderRepository;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Order;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class OrderService {

    private final BookOrderRepository repository;
    private final DetailsService detailsService;

    public OrderService(BookOrderRepository bookOrderRepository, DetailsService detailsService) {
        this.repository = bookOrderRepository;
        this.detailsService = detailsService;
    }

    public Boolean ifExist(Long id) {
        return repository.existsById(id);
    }

    public Double getPrice(Long id) {
        List<OrderDetails> detailsList = detailsService.getListDetailsByOrderId(id);
        Double totalPrice = 00.00;
        for (OrderDetails details:detailsList) {
            Double pricePart = details.getOrderPrice();
            totalPrice += pricePart;
        }

        return totalPrice;
    }

    @Transactional
    public BookOrder createOrder(BookOrder order) {
        return repository.save(order);
    }

    public BookOrder getOrderById(Long id) {
        return repository.getById(id);
    }

    @Transactional
    public Boolean deleteOrder(Long id) {
        repository.delete(repository.getById(id));
        return true;
    }

}
