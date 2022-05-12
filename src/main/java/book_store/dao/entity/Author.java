package book_store.dao.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Book> bookList;

    public void addBook(Book book){
        this.bookList.add(book);
        book.setAuthor(this);
    }
    public void removeComment(Book book){
        this.bookList.remove(book);
        book.setAuthor(null);
    }


}
