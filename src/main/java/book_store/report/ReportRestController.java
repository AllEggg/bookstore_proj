package book_store.report;


import book_store.views.ReportView;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("restcontrol/report")
public class ReportRestController {

    private final ReportService reportService;
    private final ReportView reportView;

    public ReportRestController(ReportService reportService, ReportView reportView) {
        this.reportService = reportService;
        this.reportView = reportView;
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping
    public List<ReportView> getReport() {
        return reportView.mapToViewList(reportService.getAllReport());
    }
}
