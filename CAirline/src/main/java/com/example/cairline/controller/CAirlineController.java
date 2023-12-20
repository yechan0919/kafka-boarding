package com.example.cairline.controller;

import com.example.cairline.dto.PassengerDTO;
import com.example.cairline.messagequeue.AirlineProducer;
import com.example.cairline.service.PassengerService;
import com.example.cairline.vo.RequestPassenger;
import com.example.cairline.vo.Boarding;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/C-airline")
public class CAirlineController {

    private final String AIRLINE_ID = "C-airline";

    private final PassengerService service;
    private final AirlineProducer producer;

    @PostMapping("/passenger")
    public ResponseEntity<PassengerDTO> createOrder(@RequestBody RequestPassenger requestPassenger) {
        log.info("Before add orders data");
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        PassengerDTO addPassenger = service.addPassenger(requestPassenger);

        /* kafka */
        Boarding boarding = mapper.map(requestPassenger, Boarding.class);
        boarding.setAirline(AIRLINE_ID);
        producer.send("boarding", boarding);

        log.info("After send Passenger Data");
        return ResponseEntity.status(HttpStatus.CREATED).body(addPassenger);
    }

}
