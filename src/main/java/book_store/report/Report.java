package book_store.report;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long bookId;

    @Column
    private Integer bookQuantity;

    @Column
    private Date date;
}
