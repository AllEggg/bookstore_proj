package book_store.dao.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Warehouse {
    @Id
    private int id;

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

