package book_store.report;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageSender {

    private final JmsTemplate jmsTemplate;
    private final String olegMq;


    public MessageSender(JmsTemplate jmsTemplate, @Value("${oleg.mq}") String olegMq) {
        this.jmsTemplate = jmsTemplate;
        this.olegMq = olegMq;
    }

    public void sellReportMessage(Integer bookQuantity, Long bookId) {

        jmsTemplate.convertAndSend(olegMq, new MessageBody(bookQuantity, bookId));
    }
}
