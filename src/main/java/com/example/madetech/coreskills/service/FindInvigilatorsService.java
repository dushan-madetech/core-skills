package com.example.madetech.coreskills.service;

import com.example.madetech.coreskills.domain.InvigilatorDomain;
import com.example.madetech.coreskills.entity.InvigilatorEntity;
import com.example.madetech.coreskills.repositories.InvigilatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class FindInvigilatorsService {
    @Autowired
    private InvigilatorRepository invigilatorRepository;

    public InvigilatorDomain[] getByCoreskill(String coreSkill) {
        List<InvigilatorDomain> result = new ArrayList<InvigilatorDomain>();

        for (InvigilatorEntity invigilatorEntity : invigilatorRepository.findAll())
        {
            if (invigilatorEntity.getCoreSkills() != null && invigilatorEntity.getCoreSkills().contains(coreSkill)) {
                result.add(InvigilatorDomain.builder().name(invigilatorEntity.getName()).build());
            }
        }

        return result.toArray(new InvigilatorDomain[result.size()]);
    }
}
