package book_store.views;

import book_store.report.Report;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class ReportView {

    private Long bookId;
    private Integer bookQuantity;
    private Date date;

    public List<ReportView> mapToViewList(List<Report> reports) {
        List<ReportView> reportViews = new ArrayList<>();
        for (Report report:reports) {
            ReportView view = new ReportView();
            view.setBookId(report.getBookId());
            view.setBookQuantity(report.getBookQuantity());
            view.setDate(report.getDate());
            reportViews.add(view);
        }
        return reportViews;
    }

}
