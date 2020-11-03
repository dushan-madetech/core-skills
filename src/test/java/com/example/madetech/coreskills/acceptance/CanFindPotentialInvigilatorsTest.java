package com.example.madetech.coreskills.acceptance;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.*;
import com.example.madetech.coreskills.entity.InvigilatorEntity;
import com.example.madetech.coreskills.repositories.InvigilatorRepository;
import com.example.madetech.coreskills.response.InvigilatorsResponse;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CanFindPotentialInvigilatorsTest {
    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    InvigilatorRepository invigilatorRepository;

    @Autowired
    AmazonDynamoDB amazonDynamoDB;

    DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

    private ResponseEntity<InvigilatorsResponse> invigilatorsResponse;
    private final Faker faker = new Faker();
    private final String firstInvigilator = faker.name().fullName();
    private final String secondInvigilator = faker.name().fullName();
    private final String notGiraffeInvigilator = faker.name().fullName();
    private final String noBadgeGuy = faker.name().fullName();
    private final String giraffeBadge = "giraffe";

    @Test
    public void canFindPotentialInvigilatorsTest() throws IOException {
        givenThereArePeopleWithAGiraffeBadge();
        whenICallTheInvigilatorsEndpointWithGiraffeBadge();
        thenICanSeeThePotentialInvigilators();
    }

    private void givenThereArePeopleWithAGiraffeBadge() {
        CreateTableRequest tableRequest = dynamoDBMapper
                .generateCreateTableRequest(InvigilatorEntity.class);
        tableRequest.setProvisionedThroughput(
                new ProvisionedThroughput(1L, 1L));
        amazonDynamoDB.createTable(tableRequest);

        invigilatorRepository.save(InvigilatorEntity.builder().name(firstInvigilator).coreSkills(new HashSet<String>(Arrays.asList(giraffeBadge))).build());
        invigilatorRepository.save(InvigilatorEntity.builder().name(secondInvigilator).coreSkills(new HashSet<String>(Arrays.asList(giraffeBadge))).build());
        invigilatorRepository.save(InvigilatorEntity.builder().name(notGiraffeInvigilator).coreSkills(new HashSet<String>(Arrays.asList("Dummy"))).build());
        invigilatorRepository.save(InvigilatorEntity.builder().name(noBadgeGuy).build());
    }

    private void whenICallTheInvigilatorsEndpointWithGiraffeBadge() {
        invigilatorsResponse = testRestTemplate.exchange("/invigilators/" + giraffeBadge, HttpMethod.GET, null, InvigilatorsResponse.class);
    }

    private void thenICanSeeThePotentialInvigilators() {
        assertEquals(HttpStatus.OK, invigilatorsResponse.getStatusCode());
        assertEquals(2, invigilatorsResponse.getBody().getNames().length);
        assertTrue(Arrays.asList(invigilatorsResponse.getBody().getNames()).contains(firstInvigilator));
        assertTrue(Arrays.asList(invigilatorsResponse.getBody().getNames()).contains(secondInvigilator));
        assertFalse(Arrays.asList(invigilatorsResponse.getBody().getNames()).contains(notGiraffeInvigilator));
        assertFalse(Arrays.asList(invigilatorsResponse.getBody().getNames()).contains(noBadgeGuy));
    }

    @AfterEach
    public void tearDown() {
        DeleteTableRequest tableRequest = dynamoDBMapper
                .generateDeleteTableRequest(InvigilatorEntity.class);
        amazonDynamoDB.deleteTable(tableRequest);
    }
}
