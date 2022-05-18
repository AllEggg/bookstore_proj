package book_store.dao.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int bookQuantity;

    @Column
    private Long bookId;

    @Column
    private double orderPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    private BookOrder bookOrder;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    @JoinTable(name = "order_books",
            joinColumns = @JoinColumn(name = "order_details_id"),
            inverseJoinColumns = @JoinColumn(name = "books_id"))
    private Set<Book> books;

    public void addBook(Book book){
        this.books.add(book);
        book.getOrderDetails().add(this);
    }
    public void removeBook(Book book){
        this.books.remove(book);
        book.getOrderDetails().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof OrderDetails)) return false;
        return id != null && id.equals(((OrderDetails) o).getId());
    }
    @Override
    public int hashCode() {
        return 31;
    }

}
