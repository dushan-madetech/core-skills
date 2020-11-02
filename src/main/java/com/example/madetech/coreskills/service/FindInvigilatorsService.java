package com.example.madetech.coreskills.service;

import com.example.madetech.coreskills.domain.InvigilatorDomain;
import org.springframework.stereotype.Service;

@Service
public class FindInvigilatorsService {
    public InvigilatorDomain[] getByCoreskill(String coreSkill) {
        return new InvigilatorDomain[]{ InvigilatorDomain.builder().name("Joe").build() };
    }
}
