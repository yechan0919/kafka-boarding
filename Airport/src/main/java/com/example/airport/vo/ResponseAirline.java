package com.example.airport.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseAirline {
    int ttlPassenger;   // 총 승객 수
    int ttlLuggage;     // 총 수하물 무게
}
