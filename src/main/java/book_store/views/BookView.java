package book_store.views;

import book_store.dao.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class BookView {

    private Integer id;
    private String name;
    private Integer pageQuantity;
    private Double price;
    private Date publishYear;
    private String authorName;

    public BookView mapToView(Book book) {
        BookView bookView = new BookView();
        bookView.setId(book.getId());
        bookView.setName(book.getName());
        bookView.setPrice(book.getPrice());
        bookView.setPageQuantity(book.getPageQuantity());
        bookView.setPublishYear(book.getPublishYear());
        bookView.setAuthorName(book.getAuthor().getName());
        return bookView;
    }

    public Book mapFromView(BookView bookView) {
        Book book = new Book();
        book.setName(bookView.getName());
        book.setPageQuantity(bookView.getPageQuantity());
        book.setPublishYear(bookView.getPublishYear());
        book.setPrice(bookView.getPrice());
        bookView.setAuthorName(book.getAuthor().getName());

        return book;

    }

    public List<BookView> mapToViewList(List<Book> bookList) {
        List<BookView> bookViewList = new ArrayList<>();
        for (Book book:bookList) {
            BookView bookView = new BookView();
            bookView.setId(book.getId());
            bookView.setName(book.getName());
            bookView.setPrice(book.getPrice());
            bookView.setPageQuantity(book.getPageQuantity());
            bookView.setPublishYear(book.getPublishYear());
            bookView.setAuthorName(book.getAuthor().getName());
            bookViewList.add(bookView);
        }
        return bookViewList;
    }

}
