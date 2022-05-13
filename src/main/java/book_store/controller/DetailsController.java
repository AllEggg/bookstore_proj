package book_store.controller;


import book_store.dao.entity.Book;
import book_store.dao.entity.BookOrder;
import book_store.dao.entity.OrderDetails;
import book_store.dao.service.BookService;
import book_store.dao.service.DetailsService;
import book_store.dao.service.OrderService;
import book_store.views.DetailsView;
import book_store.views.DetailsViewForRequest;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import java.util.List;

@RestController
@RequestMapping("restcontrol/orderDetails")
public class DetailsController {


    private final DetailsView detailsView;
    private final DetailsViewForRequest detailsViewForRequest;
    private final DetailsService detailsService;
    private final OrderService orderService;
    private final BookService bookService;

    public DetailsController(DetailsView detailsView, DetailsViewForRequest detailsViewForRequest, DetailsService detailsService, OrderService orderService, BookService bookService) {
        this.detailsView = detailsView;
        this.detailsViewForRequest = detailsViewForRequest;
        this.detailsService = detailsService;
        this.orderService = orderService;
        this.bookService = bookService;
    }

    @GetMapping("/{order_id}")
    public List<DetailsView> getOrderDetails(@PathVariable("order_id") Long id) {
        return detailsView.mapToViewList(id, detailsService, bookService);

    }

    @PostMapping("/{order_id}")
    public DetailsView addBookToOrder(@PathVariable("order_id") Long id
            , @RequestBody DetailsViewForRequest body) {

        OrderDetails orderDetails = detailsViewForRequest.mapFromView(body
                , detailsService, bookService);
        OrderDetails newDetails;
        BookOrder order = orderService.getOrderById(id);
        Book book = bookService.getBookByName(body.getBookName());

        if (!orderService.ifExist(id)) {
            throw new EntityExistsException("No such order");

        } else if (detailsService.existBookInOrder(book.getId(), order.getId())) {
            int newQuantity = detailsService.getBookQuantityByOrderId(order.getId(), book.getId()) + body.getBookQuantity();
            newDetails = detailsService.updateQuantity(detailsService.getDetailsByBookId(book.getId(),
                    order.getId()),
                    newQuantity);

        } else {
            orderDetails.setOrderPrice(detailsService.getPrice(book.getId(), body.getBookQuantity()));
            orderDetails.setBookOrder(order);
            newDetails = detailsService.add(orderDetails);

        }

        return detailsView.mapToView(newDetails, bookService);
        }

    @PutMapping("/{order_id}")
    public DetailsView editDetails(@PathVariable("order_id") Long orderId
            , @RequestBody DetailsViewForRequest body) {

        Book book = bookService.getBookByName(body.getBookName());

        if (!orderService.ifExist(orderId)) {
            throw new EntityExistsException(String.format("Заказ номер = %d не существует", orderId));}
        else if (!detailsService.existBookInOrder(book.getId(), orderId)) {
            throw new EntityExistsException("Entittytytyt: ");}

        OrderDetails orderDetails = detailsService.getDetailsByBookId(book.getId(), orderId);

        if (orderDetails.getBookQuantity() != body.getBookQuantity()) {
            orderDetails.setBookQuantity(body.getBookQuantity());
            orderDetails.setOrderPrice(detailsService.getPrice(book.getId(), body.getBookQuantity()));
        }

        OrderDetails edited = detailsService.add(orderDetails);
        return detailsView.mapToView(edited, bookService);

    }

    @DeleteMapping("/{container_id}")
    public Boolean deleteDetails(@PathVariable("container_id") Long id) {
        return detailsService.delete(id);
    }

}






