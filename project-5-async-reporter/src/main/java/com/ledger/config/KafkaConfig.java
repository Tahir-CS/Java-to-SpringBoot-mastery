package com.ledger.config;

import com.ledger.event.TransactionEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class KafkaConfig {

    @Value("${app.kafka.topics.transaction-events}")
    private String transactionEventsTopic;

    @Bean
    public NewTopic transactionEventsTopic() {
        return TopicBuilder.name(transactionEventsTopic).partitions(3).replicas(1).build();
    }

    @Bean
    public NewTopic transactionEventsDlt() {
        return TopicBuilder.name(transactionEventsTopic + ".DLT").partitions(1).replicas(1).build();
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, TransactionEvent> kafkaListenerContainerFactory(
            ConsumerFactory<String, TransactionEvent> consumerFactory,
            KafkaTemplate<String, Object> kafkaTemplate) {
        ConcurrentKafkaListenerContainerFactory<String, TransactionEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        DefaultErrorHandler errorHandler = new DefaultErrorHandler(
                new DeadLetterPublishingRecoverer(kafkaTemplate),
                new FixedBackOff(1000L, 3)
        );
        factory.setCommonErrorHandler(errorHandler);
        return factory;
    }
}
