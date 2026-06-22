package com.ledger.messaging;
import com.ledger.event.TransactionEvent;
import com.ledger.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TransactionEventConsumer {
    private final NotificationService notificationService;
    public TransactionEventConsumer(NotificationService notificationService) { this.notificationService = notificationService; }

    @KafkaListener(topics = "${app.kafka.topics.transaction-events}", groupId = "${spring.kafka.consumer.group-id}", concurrency = "3")
    public void handleTransactionEvent(TransactionEvent event, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                       @Header(KafkaHeaders.RECEIVED_PARTITION) int partition, @Header(KafkaHeaders.OFFSET) long offset) {
        log.info("Received event: eventId={}, type={}, partition={}, offset={}", event.getEventId(), event.getEventType(), partition, offset);
        try {
            switch (event.getEventType()) {
                case "TRANSFER_COMPLETED" -> notificationService.sendTransferReceipt(event);
                case "TRANSFER_FAILED" -> notificationService.sendTransferFailureAlert(event);
                default -> log.warn("Unknown event type: {}", event.getEventType());
            }
        } catch (Exception ex) {
            log.error("Failed to process event: eventId={}, error={}", event.getEventId(), ex.getMessage(), ex);
        }
    }
}
