package com.example.airport.messagequeque;

import com.example.airport.entity.BoardingEntity;
import com.example.airport.repository.BoardingRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class KafkaConsumer {
    private final BoardingRepository boardingRepository;

    @KafkaListener(topics = "boarding")
    public void boarding(String kafkaMessage) {
        log.info("Kafka Message: ->" + kafkaMessage);

        BoardingEntity entity = new BoardingEntity();
        ObjectMapper mapper = new ObjectMapper();
        try {
            entity = mapper.readValue(kafkaMessage, BoardingEntity.class);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }

        boardingRepository.save(entity);
    }
}