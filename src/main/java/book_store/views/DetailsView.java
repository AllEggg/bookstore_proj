package book_store.views;


import book_store.dao.entity.OrderDetails;
import book_store.dao.service.BookService;
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
public class DetailsView {

    private Long containerId;
    private Long orderId;
    private int bookQuantity;
    private Long bookId;
    private String bookName;
    private double orderPrice;

    public DetailsView mapToView(OrderDetails orderDetails, BookService bookService) {
        DetailsView detailsView = new DetailsView();
        detailsView.setContainerId(orderDetails.getId());
        detailsView.setOrderId(orderDetails.getBookOrder().getId());
        detailsView.setBookQuantity(orderDetails.getBookQuantity());
        detailsView.setBookId(orderDetails.getBookId());
        detailsView.setBookName(bookService.getNameById(orderDetails.getBookId()));
        detailsView.setOrderPrice(orderDetails.getOrderPrice());

        return detailsView;
    }

    public OrderDetails mapFromView(DetailsView detailsView,
                                    OrderService orderService,
                                    BookService bookService) {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setBookOrder(orderService.getOrderById(detailsView.getOrderId()));
        orderDetails.setBookQuantity(detailsView.getBookQuantity());
        orderDetails.setBooks(Set.of(bookService.getBookById(detailsView.getBookId())));
        orderDetails.setOrderPrice(detailsView.getOrderPrice());

        return orderDetails;
    }

    public List<DetailsView> mapToViewList(Long orderId, DetailsService detailsService, BookService bookService) {
        List<OrderDetails> orderDetailsList = detailsService.getListDetailsByOrderId(orderId);
        List<DetailsView> detailsViewList = new ArrayList<>();
        for (OrderDetails details:orderDetailsList) {
            DetailsView detailsView = new DetailsView();
            detailsView.setContainerId(details.getId());
            detailsView.setOrderId(details.getBookOrder().getId());
            detailsView.setBookQuantity(details.getBookQuantity());
            detailsView.setBookId(details.getBookId());
            detailsView.setOrderPrice(details.getOrderPrice());
            detailsViewList.add(detailsView);
        }
        return detailsViewList;
    }
}
