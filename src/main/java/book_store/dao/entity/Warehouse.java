package book_store.dao.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Warehouse {
    @Id
    @GeneratedValue
    private int warehouse_id;

    @Column
    private int bookQuantity;

    @OneToMany(mappedBy = "warehouse", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private List<Book> bookIds;


    }

