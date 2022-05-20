package book_store.report;


import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageListener {

    private final ReportService reportService;

    public MessageListener(ReportService reportService) {
        this.reportService = reportService;
    }


    @JmsListener(destination = "oleg-mq")
    public void processReportListener(@Payload MessageBody messageBody) {
        reportService.commitReport(messageBody.getBookQuantity(), messageBody.getBookId());
        log.info("Покупка совершена. Книга - {}, количество - {}",
                messageBody.getBookId(),
                messageBody.getBookQuantity());
    }

}
