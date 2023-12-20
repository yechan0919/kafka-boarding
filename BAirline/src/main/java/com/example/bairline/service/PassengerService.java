package com.example.bairline.service;

import com.example.bairline.entity.PassengerEntity;
import com.example.bairline.repository.PassengerRepository;
import com.example.bairline.vo.RequestPassenger;
import com.example.bairline.dto.PassengerDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PassengerService {
    private final PassengerRepository passengerRepository;

    public PassengerDTO addPassenger(RequestPassenger requestPassenger) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy((MatchingStrategies.STRICT));
        PassengerEntity entity = mapper.map(requestPassenger, PassengerEntity.class);
        passengerRepository.save(entity);
        return mapper.map(entity, PassengerDTO.class);
    }
}
