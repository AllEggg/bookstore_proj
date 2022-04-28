package book_store.views;

import book_store.dao.entity.Book;
import book_store.dao.service.AuthorService;
import book_store.dao.service.WarehouseService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class BookView {

//    private Integer id;
    private String name;
    private Integer pageQuantity;
    private Double price;
    private Date publishYear;
    private String authorName;
    private Integer inStock;

    public BookView mapToView(Book book, WarehouseService warehouseService) {
        BookView bookView = new BookView();
//        bookView.setId(book.getId());
        bookView.setName(book.getName());
        bookView.setPrice(book.getPrice());
        bookView.setPageQuantity(book.getPageQuantity());
        bookView.setPublishYear(book.getPublishYear());
        bookView.setAuthorName(book.getAuthor().getName());
        bookView.setInStock(warehouseService.getBooksCount(book.getId()));
        return bookView;
    }

    public Book mapFromView(BookView bookView, AuthorService authorService) {
        Book book = new Book();
        book.setName(bookView.getName());
        book.setPageQuantity(bookView.getPageQuantity());
        book.setPublishYear(bookView.getPublishYear());
        book.setPrice(bookView.getPrice());
        book.setAuthor(authorService.getAuthorByName(bookView.getAuthorName()));

        return book;

    }

    public List<BookView> mapToViewList(List<Book> bookList, WarehouseService warehouseService) {
        List<BookView> bookViewList = new ArrayList<>();
        for (Book book:bookList) {
            BookView bookView = new BookView();
//            bookView.setId(book.getId());
            bookView.setName(book.getName());
            bookView.setPrice(book.getPrice());
            bookView.setPageQuantity(book.getPageQuantity());
            bookView.setPublishYear(book.getPublishYear());
            bookView.setAuthorName(book.getAuthor().getName());
            bookViewList.add(bookView);
            bookView.setInStock(warehouseService.getBooksCount(book.getId()));
        }
        return bookViewList;
    }

}
