package com.example.madetech.coreskills.repository;

import com.example.madetech.coreskills.entity.InvigilatorEntity;
import com.example.madetech.coreskills.repositories.InvigilatorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class InvigilatorsRepositoryTests {

    @Autowired
    InvigilatorRepository invigilatorRepository;

    @Test
    public void testCanGetInvigilatorsWithACoreSkill() {
        List<InvigilatorEntity> result = invigilatorRepository.findByCoreSkill("Giraffe");

        assertEquals(result,"foo");

    }
}
