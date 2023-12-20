package com.example.cairline.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Boarding {
    String passport; // 여권번호
    int luggage;    // 수하물 무게
    String airline; // 탑승 항공사
}
