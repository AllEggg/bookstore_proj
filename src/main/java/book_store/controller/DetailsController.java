package book_store.controller;


import book_store.dao.entity.Book;
import book_store.dao.entity.BookOrder;
import book_store.dao.entity.BookStoreUser;
import book_store.dao.entity.OrderDetails;
import book_store.dao.service.BookService;
import book_store.dao.service.DetailsService;
import book_store.dao.service.OrderService;
import book_store.dao.service.UserService;
import book_store.views.DetailsView;
import book_store.views.DetailsViewForRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("restcontrol/orderDetails")
public class DetailsController {

    private final UserService userService;
    private final DetailsView detailsView;
    private final DetailsViewForRequest detailsViewForRequest;
    private final DetailsService detailsService;
    private final OrderService orderService;
    private final BookService bookService;


    public DetailsController(UserService userService,
                             DetailsView detailsView,
                             DetailsViewForRequest detailsViewForRequest,
                             DetailsService detailsService,
                             OrderService orderService,
                             BookService bookService) {
        this.userService = userService;
        this.detailsView = detailsView;
        this.detailsViewForRequest = detailsViewForRequest;
        this.detailsService = detailsService;
        this.orderService = orderService;
        this.bookService = bookService;

    }



    @GetMapping("/{order_id}")
    public List<DetailsView> getOrderDetails(@PathVariable("order_id") Long id) {
        BookOrder order = orderService.getOrderById(id);
        BookStoreUser user =
                userService.loadUserByUsername(SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName());
        if (!user.getId().equals(order.getCustomer().getId())) {
            throw new RuntimeException("Доступ к заказу запрещён");
        }
        return detailsView.mapToViewList(id, detailsService, bookService);

    }
    @Secured({"ROLE_USER"})
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
    @Secured({"ROLE_USER"})
    @PutMapping("{order_id}")
    public DetailsView editDetails(@PathVariable("order_id") Long orderId
            , @RequestBody DetailsViewForRequest body) {
        BookStoreUser user =
                userService.loadUserByUsername(SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName());
        BookOrder order = orderService.getOrderById(orderId);
        if (!Objects.equals(user.getId(), order.getCustomer().getId())) {
            throw new RuntimeException("Доступ к заказу запрещён");
        }

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
    @Secured({"ROLE_USER"})
    @DeleteMapping("/{container_id}")

    public Boolean deleteDetails(@PathVariable("container_id") Long id) {
        BookOrder order = orderService.getOrderById(id);
        BookStoreUser user =
                userService.loadUserByUsername(SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName());
        if (!user.getId().equals(order.getCustomer().getId())) {
            throw new RuntimeException("Доступ к заказу запрещён");
        }
        return detailsService.delete(id);
    }

}






