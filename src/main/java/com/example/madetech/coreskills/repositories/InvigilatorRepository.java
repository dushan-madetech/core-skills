package com.example.madetech.coreskills.repositories;
import com.example.madetech.coreskills.entity.InvigilatorEntity;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@EnableScan
public interface InvigilatorRepository extends CrudRepository<InvigilatorEntity, String> {
    Optional<InvigilatorEntity> findById(String id);
}
