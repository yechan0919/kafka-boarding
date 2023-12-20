package com.example.bairline.vo;

import lombok.Data;

import java.util.Date;

@Data
public class RequestPassenger {
    String name;     // 영문이름
    String passport; // 여권번호
    int luggage;    // 수하물 무게
    Date birth;     // 생년월일
    int gender;      // 성별
    String country;  // 국적
    String airline; // 탑승 항공사
}
