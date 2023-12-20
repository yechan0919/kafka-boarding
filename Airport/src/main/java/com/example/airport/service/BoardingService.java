package com.example.airport.service;

import com.example.airport.dto.BoardingDTO;
import com.example.airport.entity.BoardingEntity;
import com.example.airport.repository.BoardingRepository;
import com.example.airport.vo.ResponsePassenger;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardingService {

    private final ModelMapper mapper = new ModelMapper();
    private final BoardingRepository boardingRepository;

    public List<BoardingDTO> getAllBoardings(){
        List<BoardingDTO> boardings = new ArrayList<>();
        boardingRepository.findAll().forEach(boarding -> boardings.add(mapper.map(boarding, BoardingDTO.class)));
        return boardings;
    }

    public List<BoardingEntity> getBoardingsByAirline(String airline) {
        return boardingRepository.findPassengersByAirline(airline);
    }

    @Transactional
    public void removeAllBoardingsByAirline(String airline) {
        boardingRepository.deleteAirportEntitiesByAirline(airline);
    }

}
