package com.ledger.messaging;
import com.ledger.event.TransactionEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class TransactionEventProducer {
    private final KafkaTemplate<String, TransactionEvent> kafkaTemplate;
    @Value("${app.kafka.topics.transaction-events}")
    private String topic;

    public TransactionEventProducer(KafkaTemplate<String, TransactionEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishTransferCompleted(TransactionEvent event) {
        String messageKey = event.getFromUsername();
        CompletableFuture<SendResult<String, TransactionEvent>> future = kafkaTemplate.send(topic, messageKey, event);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Event published: eventId={}, topic={}, partition={}, offset={}",
                    event.getEventId(), result.getRecordMetadata().topic(),
                    result.getRecordMetadata().partition(), result.getRecordMetadata().offset());
            } else {
                log.error("FAILED to publish event: eventId={}, error={}", event.getEventId(), ex.getMessage());
            }
        });
    }
}
