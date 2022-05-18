package book_store.controller;


import book_store.dao.entity.BookOrder;
import book_store.dao.service.CustomerService;
import book_store.dao.service.OrderService;
import book_store.views.OrderRequestBodyView;
import book_store.views.OrderView;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("restcontrol/orders")
public class OrderController {

    private final OrderView view;
    private final OrderRequestBodyView requestBodyView;
    private final OrderService orderService;
    private final CustomerService customerService;

    public OrderController(OrderView view,
                           OrderService orderService,
                           CustomerService customerService,
                           OrderRequestBodyView requestBodyView) {
        this.view = view;
        this.orderService = orderService;
        this.customerService = customerService;
        this.requestBodyView = requestBodyView;
    }

    @GetMapping("/{order_id}")
    public OrderView getOrderById(@PathVariable("order_id") Long id){
        return view.mapToView(orderService.getOrderById(id),
                orderService);
    }

    @Secured({"ROLE_USER"})
    @PostMapping
    public OrderView createOrder(@RequestBody OrderRequestBodyView body) {
        BookOrder bookOrder = requestBodyView.mapFromView(body, customerService);
        BookOrder newOrder = orderService.createOrder(bookOrder);
        return view.mapToView(newOrder, orderService);
    }

    @Secured({"ROLE_USER"})
    @DeleteMapping("/{order_id}")
    public Boolean deleteOrder(@PathVariable("order_id") Long id) {
        return orderService.deleteOrder(id);
    }


}
