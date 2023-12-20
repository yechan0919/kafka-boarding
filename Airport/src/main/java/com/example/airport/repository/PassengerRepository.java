package com.example.airport.repository;

import com.example.airport.entity.PassengerEntity;
import org.springframework.data.repository.CrudRepository;

public interface PassengerRepository extends CrudRepository<PassengerEntity, String> {
}
