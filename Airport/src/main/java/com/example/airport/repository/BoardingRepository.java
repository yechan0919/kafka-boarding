package com.example.airport.repository;

import com.example.airport.entity.BoardingEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BoardingRepository extends CrudRepository<BoardingEntity, Long>  {

    List<BoardingEntity> findPassengersByAirline(String airline);

    void deleteAirportEntitiesByAirline(String airline);
}
