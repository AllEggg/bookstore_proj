package book_store.views;

import book_store.dao.entity.BookOrder;
import book_store.dao.service.DetailsService;
import book_store.dao.service.OrderService;
import book_store.dao.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class OrderView {

    private Long id;
    private Double price;
    private String customerName;

    public OrderView mapToView(BookOrder order,
                                      OrderService service) {
        OrderView orderView = new OrderView();
        orderView.setId(order.getId());
        orderView.setPrice(service.getPrice(order.getId()));
        orderView.setCustomerName(order.getCustomer().getUsername());
        return orderView;
    }

    public List<OrderView> mapToViewList(List<BookOrder> bookOrderList,
                               OrderService service) {
        List<OrderView> orderViewList = new ArrayList<>();
        for (BookOrder order : bookOrderList) {
            OrderView orderView = new OrderView();
            orderView.setId(order.getId());
            orderView.setPrice(service.getPrice(order.getId()));
            orderView.setCustomerName(order.getCustomer().getUsername());
            orderViewList.add(orderView);
        }
        return orderViewList;
    }


    public OrderView mapToViewDetails(BookOrder order,
                               OrderService service,
                               DetailsService detailsService, DetailsView detailsView) {
        OrderView orderView = new OrderView();
        List<DetailsView> detailsViewList = new ArrayList<>();
        orderView.setId(order.getId());
        orderView.setPrice(service.getPrice(order.getId()));
        orderView.setCustomerName(order.getCustomer().getUsername());
//        orderView.setDetails(detailsView.mapToViewList(order.getId(), detailsService));
        return orderView;
    }

    public BookOrder mapFromView(OrderView orderView, UserService userService) {
        BookOrder bookOrder = new BookOrder();
        bookOrder.setCustomer(userService.loadUserByUsername(orderView.getCustomerName()));
        bookOrder.setOrderPrice(00.00);
        return bookOrder;
    }

}
