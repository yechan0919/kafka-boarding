package com.example.cairline.repository;

import com.example.cairline.entity.PassengerEntity;
import org.springframework.data.repository.CrudRepository;

public interface PassengerRepository extends CrudRepository<PassengerEntity, String> {
}
