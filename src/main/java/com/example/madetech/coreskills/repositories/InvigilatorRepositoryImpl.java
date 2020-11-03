package com.example.madetech.coreskills.repositories;

import com.example.madetech.coreskills.entity.InvigilatorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public abstract class InvigilatorRepositoryImpl implements InvigilatorRepository {

    @Override
    public List<InvigilatorEntity> findByCoreSkill(String coreSkill) {
        return null;
    }
}
