package com.example.airport.service;

import com.example.airport.dto.PassengerDTO;
import com.example.airport.entity.PassengerEntity;
import com.example.airport.repository.PassengerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PassengerService {
    private final ModelMapper mapper = new ModelMapper();
    private final PassengerRepository passengerRepository;

    public PassengerDTO getPassenger(String passport){
        PassengerEntity passengerEntity = passengerRepository.findById(passport).orElse(null);
        if(passengerEntity != null){
            return mapper.map(passengerEntity, PassengerDTO.class);
        }else{
            return null;
        }
    }
}
