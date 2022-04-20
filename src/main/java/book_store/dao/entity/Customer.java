package book_store.dao.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Customer {
    @Id
    @GeneratedValue
    private int id;
    @Column
    private String name;
    @Column
    private String surName;
    @Column
    private String fathersName;
    @Column
    private Date birthdate;

    @OneToMany(mappedBy = "customer")
    @Fetch(FetchMode.JOIN)
    private List<BookOrder> orders;

}
