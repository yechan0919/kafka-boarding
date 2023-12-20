package com.example.airport.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="passengers")
public class PassengerEntity {

    @Id
    @Column(nullable = false)
    String passport; // 여권번호

    @Column(nullable = false)
    String name;     // 영문이름

    @Column(nullable = false)
    Date birth;     // 생년월일

    @Column(nullable = false)
    int gender;      // 성별

    @Column(nullable = false)
    String country;  // 국적
}
