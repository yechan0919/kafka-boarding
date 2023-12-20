package com.example.airport.dto;

import lombok.Data;

@Data
public class BoardingDTO {
    Long id;
    String passport; // 여권번호
    int luggage;    // 수하물 무게
    String airline; // 탑승 항공사
}
