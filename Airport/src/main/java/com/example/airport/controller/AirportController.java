package com.example.airport.controller;

import com.example.airport.entity.BoardingEntity;
import com.example.airport.service.BoardingService;
import com.example.airport.service.PassengerService;
import com.example.airport.vo.ResponseAirline;
import com.example.airport.vo.ResponsePassenger;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class AirportController {
    private final BoardingService boardingService;
    private final PassengerService passengerService;
    private final ModelMapper mapper = new ModelMapper();

    // 모든 입국자 정보 조회
    @GetMapping("/airport")
    public ResponseEntity<List<ResponsePassenger>> getAirport() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(boardingService.getAllBoardings().stream().map(boarding -> {
                    ResponsePassenger responsePassenger = mapper.map(passengerService.getPassenger(boarding.getPassport()), ResponsePassenger.class);
                    mapper.map(boarding, responsePassenger);
                    return responsePassenger;
                }).toList());
    }

    // 항공사 별 입국자 정보 조회
    @GetMapping("/{airline}")
    public ResponseEntity<Map<ResponseAirline, List<ResponsePassenger>>> getAirlinePassenger(@PathVariable("airline") String airline) {
        Map<ResponseAirline, List<ResponsePassenger>> result = new HashMap<>();
        List<BoardingEntity> boardings = boardingService.getBoardingsByAirline(airline);
        int ttlLug = boardings.stream().mapToInt(BoardingEntity::getLuggage).sum();
        int ttlPass = boardings.size();
        List<ResponsePassenger> respPassengers = boardings.stream()
                .map(boarding -> {
                    ResponsePassenger responsePassenger = mapper.map(passengerService.getPassenger(boarding.getPassport()), ResponsePassenger.class);
                    mapper.map(boarding, responsePassenger);
                    return mapper.map(boarding, ResponsePassenger.class);
                })
                .toList();
        ResponseAirline respAirline = new ResponseAirline(ttlPass, ttlLug);
        result.put(respAirline, respPassengers);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


    // 특정 항공사의 모든 승객 하차 완료
    @DeleteMapping("{airline}")
    ResponseEntity<String> landingAirline(@PathVariable("airline") String airline) {
        boardingService.removeAllBoardingsByAirline(airline);
        return ResponseEntity.status(HttpStatus.OK).body(airline + " 모든 승객 하차 완료");
    }
}