package book_store.views;

import book_store.dao.entity.BookOrder;
import book_store.dao.service.CustomerService;
import book_store.dao.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestBodyView {

    private String customerName;

    public BookOrder mapFromView(OrderRequestBodyView orderView, CustomerService customerService) {
        BookOrder bookOrder = new BookOrder();
        bookOrder.setCustomer(customerService.getCustomerByName(orderView.getCustomerName()));
        bookOrder.setOrderPrice(00.00);
        return bookOrder;
    }
}
