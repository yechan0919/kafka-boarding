package com.example.bairline.repository;

import com.example.bairline.entity.PassengerEntity;
import org.springframework.data.repository.CrudRepository;

public interface PassengerRepository extends CrudRepository<PassengerEntity, String> {
}
