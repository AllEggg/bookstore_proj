package book_store.dao.service;


import book_store.dao.entity.Book;
import book_store.dao.entity.BookOrder;
import book_store.dao.entity.OrderDetails;
import book_store.dao.repository.BookOrderRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrderService {

    private final BookOrderRepository repository;
    private final DetailsService detailsService;
    private final WarehouseService warehouseService;
    private final BookService bookService;

    public OrderService(BookOrderRepository bookOrderRepository, DetailsService detailsService, WarehouseService warehouseService, BookService bookService) {
        this.repository = bookOrderRepository;
        this.detailsService = detailsService;
        this.warehouseService = warehouseService;
        this.bookService = bookService;
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

    public List<BookOrder> getAllOrders() {
        return repository.findAll();
    }

    public List<BookOrder> getCustomerOrders(Long id) {
        return repository.findAllByCustomer(id);
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

    @Transactional
    public void purchaseOrder(Long id) {
        List<OrderDetails> orderDetailsList = detailsService.getListDetailsByOrderId(id);
        for (OrderDetails orderDetails:orderDetailsList) {
            Book book = bookService.getBookById(orderDetails.getBookId());
            warehouseService.sellBook(book.getName(), orderDetails.getBookQuantity());
        }
    }

}
