package com.example.airport.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponsePassenger {
    String name;     // 영문이름
    String passport; // 여권번호
    int luggage;    // 수하물 무게
    Date birth;     // 생년월일
    int gender;      // 성별
    String country;  // 국적
    String airline; // 탑승 항공사
}
