package book_store.views;

import book_store.dao.entity.BookOrder;
import book_store.dao.service.UserService;
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

    public BookOrder mapFromView(OrderRequestBodyView orderView, UserService userService) {
        BookOrder bookOrder = new BookOrder();
        bookOrder.setCustomer(userService.loadUserByUsername(orderView.getCustomerName()));
        bookOrder.setOrderPrice(00.00);
        return bookOrder;
    }
}
