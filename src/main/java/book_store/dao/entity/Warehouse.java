package book_store.dao.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Warehouse {
    @Id
    private Long id;

    @Column
    private int bookQuantity;

    @Version
    @Valid
    @NotBlank
    private Long version;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "book_id")
    private Book book;

}

