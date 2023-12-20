package com.example.aairline.messagequeue;

import com.example.aairline.vo.Boarding;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AirlineProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public Boarding send(String topic, Boarding dto) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";
        try {
            jsonInString = mapper.writeValueAsString(dto);
        } catch(JsonProcessingException ex) {
            log.info("JSON 변환 실패");
            ex.printStackTrace();
        }

        kafkaTemplate.send(topic, jsonInString);
        log.info("Kafka Producer sent data from Consumer: " + dto);

        return dto;
    }

}
