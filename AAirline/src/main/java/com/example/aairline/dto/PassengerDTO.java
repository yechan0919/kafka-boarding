package com.example.aairline.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PassengerDTO {
    String passport; // 여권번호
    String name;     // 영문이름
    Date birth;     // 생년월일
    int gender;      // 성별
    String country;  // 국적
}
