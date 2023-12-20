package com.example.aairline.repository;

import com.example.aairline.entity.PassengerEntity;
import org.springframework.data.repository.CrudRepository;

public interface PassengerRepository extends CrudRepository<PassengerEntity, String> {
}
