package com.example.aairline.controller;

import com.example.aairline.dto.PassengerDTO;
import com.example.aairline.messagequeue.AirlineProducer;
import com.example.aairline.service.PassengerService;
import com.example.aairline.vo.RequestPassenger;
import com.example.aairline.vo.Boarding;
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
@RequestMapping("/A-airline")
public class AAirlineController {

    private final String AIRLINE_ID = "A-airline";

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
