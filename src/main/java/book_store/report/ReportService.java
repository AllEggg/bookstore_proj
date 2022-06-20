package book_store.report;


import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Service
public class ReportService {

    private final ReportRepository repository;

    public ReportService(ReportRepository repository) {
        this.repository = repository;
    }

    public void commitReport(Integer bookQuantity, Long bookId) {
        Report report = new Report();
        report.setBookQuantity(bookQuantity);
        report.setBookId(bookId);
        report.setDate(new Date(Calendar.getInstance().getTime().getTime()));
        repository.save(report);
    }

    public List<Report> getAllReport() {
        return repository.findAll();
    }

}
