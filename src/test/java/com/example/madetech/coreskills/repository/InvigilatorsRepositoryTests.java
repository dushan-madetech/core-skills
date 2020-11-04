package com.example.madetech.coreskills.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.DeleteTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.example.madetech.coreskills.entity.InvigilatorEntity;
import com.example.madetech.coreskills.repositories.InvigilatorRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class InvigilatorsRepositoryTests {
    @Autowired
    AmazonDynamoDB amazonDynamoDB;
    DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

    @Autowired
    InvigilatorRepository invigilatorRepository;

    @BeforeEach
    private void SetupDB() {
        CreateTableRequest tableRequest = dynamoDBMapper
                .generateCreateTableRequest(InvigilatorEntity.class);
        tableRequest.setProvisionedThroughput(
                new ProvisionedThroughput(1L, 1L));
        amazonDynamoDB.createTable(tableRequest);
    }

    @AfterEach
    public void tearDown() {
        DeleteTableRequest tableRequest = dynamoDBMapper
                .generateDeleteTableRequest(InvigilatorEntity.class);
        amazonDynamoDB.deleteTable(tableRequest);
    }

    @Test
    public void testCanGetInvigilatorsWithACoreSkill() {
        final String expectedBadge = "giraffe";
        final String expectedName = "Clark Kent";
        invigilatorRepository.save(InvigilatorEntity.builder().name("Clark Kent").coreSkills(new HashSet<String>(Arrays.asList(expectedBadge))).build());
        Iterable<InvigilatorEntity> entities = invigilatorRepository.findByCoreSkillsContaining(expectedBadge);

        List<String> names = new ArrayList<String>();
        entities.forEach(e -> names.add(e.getName()));
        assertTrue(names.contains(expectedName));
    }
}
