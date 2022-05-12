package book_store.views;

import book_store.dao.entity.Book;
import book_store.dao.entity.BookOrder;
import book_store.dao.service.CustomerService;
import book_store.dao.service.DetailsService;
import book_store.dao.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
        orderView.setCustomerName(order.getCustomer().getName());
        return orderView;
    }


    public OrderView mapToViewDetails(BookOrder order,
                               OrderService service,
                               DetailsService detailsService, DetailsView detailsView) {
        OrderView orderView = new OrderView();
        List<DetailsView> detailsViewList = new ArrayList<>();
        orderView.setId(order.getId());
        orderView.setPrice(service.getPrice(order.getId()));
        orderView.setCustomerName(order.getCustomer().getName());
//        orderView.setDetails(detailsView.mapToViewList(order.getId(), detailsService));
        return orderView;
    }

    public BookOrder mapFromView(OrderView orderView, CustomerService customerService) {
        BookOrder bookOrder = new BookOrder();
        bookOrder.setCustomer(customerService.getCustomerByName(orderView.getCustomerName()));
        bookOrder.setOrderPrice(00.00);
        return bookOrder;
    }

}
