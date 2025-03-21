package com.example.shield.service.kafka;

import org.springframework.stereotype.Service;

import com.example.shield.model.conversation.RoomConversation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

@Service
public class KafkaProducerService {


    @Value("${spring.kafka.topic}")
    private String enrichmentTopic;

    private final KafkaTemplate<String, RoomConversation> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, RoomConversation> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendDataToEnrichment(RoomConversation data) {
        kafkaTemplate.send(enrichmentTopic, data);
    }

}
