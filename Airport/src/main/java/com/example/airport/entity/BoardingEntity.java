package com.example.airport.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "boarding")
public class BoardingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String passport; // 여권번호

    @Column(nullable = false)
    int luggage;    // 수하물 무게

    @Column(nullable = false)
    String airline; // 탑승 항공사
}
