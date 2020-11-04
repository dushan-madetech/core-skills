package com.example.madetech.coreskills.repositories;
import com.example.madetech.coreskills.entity.InvigilatorEntity;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableScan
@Repository
public interface InvigilatorRepository extends CrudRepository<InvigilatorEntity, String> {
    @Query("From Invigilator i where :coreSkill member i.coreSkills")
    List<InvigilatorEntity> findByCoreSkillsContaining(@Param("coreSkill") String coreSkill);
}
