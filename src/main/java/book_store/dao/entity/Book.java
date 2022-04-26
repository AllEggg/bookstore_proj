package book_store.dao.entity;

import book_store.dao.service.WarehouseService;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Builder
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private Date publishYear;

    @Column
    private int pageQuantity;

    @Column
    private double price;


    @ManyToOne(fetch = FetchType.LAZY)
    private Author author;

    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + name + '\'' +
                ", publishYear=" + publishYear +
                ", pageQuantity=" + pageQuantity +
                ", price=" + price +
                '}';
    }

    public String getImage() {
        return "https://lh4.googleusercontent.com/JF5ll5h92znpF3dMZZM7oImhi9j_OH1LCT9on-zozUX0jXsU3wICu0LpvAbHCn0sro1szi2RXim5DPuqDUie7d1sJ0tJRVHefO97NwA4C_Ha06HBScmZtE3GuW2Im8MJvSF_zJdh";


    }


}

