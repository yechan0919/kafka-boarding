package com.example.aairline.service;

import com.example.aairline.entity.PassengerEntity;
import com.example.aairline.repository.PassengerRepository;
import com.example.aairline.vo.RequestPassenger;
import com.example.aairline.dto.PassengerDTO;
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
