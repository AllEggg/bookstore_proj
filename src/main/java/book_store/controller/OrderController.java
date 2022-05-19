package book_store.controller;


import book_store.dao.entity.BookOrder;
import book_store.dao.entity.BookStoreUser;
import book_store.dao.entity.Role;
import book_store.dao.service.OrderService;
import book_store.dao.service.UserService;
import book_store.views.OrderRequestBodyView;
import book_store.views.OrderView;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("restcontrol/orders")
public class OrderController {
    private final UserService userService;
    private final OrderService orderService;
    private final OrderView view;
    private final OrderRequestBodyView requestBodyView;


    public OrderController(UserService userService, OrderView view,
                           OrderService orderService,
                           OrderRequestBodyView requestBodyView) {
        this.userService = userService;
        this.view = view;
        this.orderService = orderService;
        this.requestBodyView = requestBodyView;
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/{order_id}")
    public OrderView getOrderById(@PathVariable("order_id") Long id) {
        BookStoreUser user =
                userService.loadUserByUsername(SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName());
        BookOrder order = orderService.getOrderById(id);
        boolean isAdmin = user.getRoles()
                .stream().map(Role::getRole)
                .anyMatch(p -> p.equals("ROLE_ADMIN"));
        if (isAdmin) {
            return view.mapToView(orderService.getOrderById(id),
                    orderService);
        } else if (!user.getId().equals(order.getCustomer().getId())) {
            throw new RuntimeException("Доступ к заказу запрещён");
        } else {
            return view.mapToView(orderService.getOrderById(id),
                    orderService);
        }
    }

    @GetMapping
    public List<OrderView> getAllOrders() {
        BookStoreUser user =
                userService.loadUserByUsername(SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName());
        boolean isAdmin = user.getRoles()
                .stream().map(Role::getRole)
                .anyMatch(p -> p.equals("ROLE_ADMIN"));
        if (isAdmin) {
            return view.mapToViewList(orderService.getAllOrders(), orderService);
        } else {
            return view.mapToViewList(orderService.getCustomerOrders(user.getId()), orderService);
        }
    }

    @Secured({"ROLE_USER"})
    @PostMapping
    public OrderView createOrder(@RequestBody OrderRequestBodyView body) {
        BookOrder bookOrder = requestBodyView.mapFromView(body, userService);
        BookOrder newOrder = orderService.createOrder(bookOrder);
        return view.mapToView(newOrder, orderService);
    }

    @Secured({"ROLE_USER"})
    @PutMapping("/{order_id}")
    public String purchaseOrder(@PathVariable("order_id") Long id) {
        BookOrder order = orderService.getOrderById(id);
        BookStoreUser user =
                userService.loadUserByUsername(SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName());
        if (!user.getId().equals(order.getCustomer().getId())) {
            throw new RuntimeException("Доступ к заказу запрещён");
        } else {
            orderService.purchaseOrder(id);
            return "Заказ успешно оплачен";
        }
    }

    @Secured({"ROLE_USER"})
    @DeleteMapping("/{order_id}")
    public Boolean deleteOrder(@PathVariable("order_id") Long id) {
        BookOrder order = orderService.getOrderById(id);
        BookStoreUser user =
                userService.loadUserByUsername(SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName());
        if (!user.getId().equals(order.getCustomer().getId())) {
            throw new RuntimeException("Доступ к заказу запрещён");
        }
        return orderService.deleteOrder(id);
    }


}
