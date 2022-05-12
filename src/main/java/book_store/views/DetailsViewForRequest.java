package book_store.views;


import book_store.dao.entity.Book;
import book_store.dao.entity.OrderDetails;
import book_store.dao.service.BookService;
import book_store.dao.service.DetailsService;
import book_store.dao.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class DetailsViewForRequest {

    private int bookQuantity;
    private String bookName;

    public DetailsViewForRequest mapToView(OrderDetails orderDetails, BookService bookService) {
        DetailsViewForRequest detailsView = new DetailsViewForRequest();
        detailsView.setBookQuantity(orderDetails.getBookQuantity());
//        detailsView.setBookId(orderDetails.getBookId());
        detailsView.setBookName(bookService.getNameById(orderDetails.getBookId()));

        return detailsView;
    }
    public OrderDetails mapFromView(DetailsViewForRequest detailsView,
                                    DetailsService detailsService,
                                    BookService bookService) {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setBookQuantity(detailsView.getBookQuantity());
        orderDetails.setBooks(Set.of(bookService.getBookByName(detailsView.getBookName())));
        orderDetails.setBookId(bookService.getBookIdByName(detailsView.getBookName()));
        orderDetails.setOrderPrice(detailsService.getPrice(bookService.getBookIdByName(detailsView.getBookName())
                , detailsView.getBookQuantity()));

        return orderDetails;
    }
}
