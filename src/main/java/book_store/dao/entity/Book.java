package book_store.dao.entity;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

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

    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Warehouse> warehouse;

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

