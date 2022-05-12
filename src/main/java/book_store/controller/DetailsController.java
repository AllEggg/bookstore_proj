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
        BookOrder order = orderService.getOrderById(id);
        if (!orderService.ifExist(id)) {
            throw new EntityExistsException(
                    String.format("Заказ номер = %& не существует", id)
            );
        } else if (detailsService.existBookInOrder(bookService.getBookIdByName(body.getBookName()),
                order.getId())) {
            int alreadyHas = detailsService.getBookQuantityByOrderId(order.getId());
            orderDetails.setOrderPrice(detailsService.getPrice(bookService.getBookIdByName(body.getBookName())
                    , body.getBookQuantity() + alreadyHas));

        } else {
            orderDetails.setOrderPrice(detailsService.getPrice(bookService.getBookIdByName(body.getBookName())
                    , body.getBookQuantity()));
        }

        orderDetails.setBookOrder(order);
        OrderDetails newDetails = detailsService.add(orderDetails);
        return detailsView.mapToView(newDetails, bookService);
        }

    @PutMapping("/order_id")
    public DetailsView editDetails(@PathVariable("order_id") Long id
            , @RequestBody DetailsViewForRequest body) {
        if (!orderService.ifExist(id)) {
            throw new EntityExistsException(
                    String.format("Заказ номер = %& не существует", id)
            );}
        else if (!detailsService.existBookInOrder(bookService.getBookIdByName(body.getBookName()),
                id)) {
            throw new EntityExistsException(
                    String.format("Книги =%& нет в заказе = %&"
                            , "fff", id));}

        OrderDetails orderDetails = detailsService.getDetailsByOrderId(id);

        if (orderDetails.getBookQuantity() != body.getBookQuantity()) {
            orderDetails.setBookQuantity(body.getBookQuantity());
            orderDetails.setOrderPrice(detailsService.getPrice(bookService.getBookIdByName(body.getBookName())
                    , body.getBookQuantity()));
        }

        OrderDetails edited = detailsService.add(orderDetails);
        return detailsView.mapToView(edited, bookService);

    }

    @DeleteMapping("/container_id")
    public Boolean deleteDetails(@PathVariable("container_id") Long id) {
        return detailsService.delete(id);
    }

}






