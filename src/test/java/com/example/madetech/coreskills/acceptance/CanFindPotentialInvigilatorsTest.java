package com.example.madetech.coreskills.acceptance;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.*;
import com.example.madetech.coreskills.domain.InvigilatorDomain;
import com.example.madetech.coreskills.entity.InvigilatorEntity;
import com.example.madetech.coreskills.repositories.InvigilatorRepository;
import com.example.madetech.coreskills.response.InvigilatorsResponse;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    private final String firstInvigilator = "Bruce Wayne";
    private final String secondInvigilator = "Clark kent";
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

        invigilatorRepository.save(InvigilatorEntity.builder().name(firstInvigilator).coreSkills(Arrays.asList(new String[]{giraffeBadge})).build());
        invigilatorRepository.save(InvigilatorEntity.builder().name(secondInvigilator).coreSkills(Arrays.asList(new String[]{giraffeBadge})).build());
    }

    private void whenICallTheInvigilatorsEndpointWithGiraffeBadge() {
        invigilatorsResponse = testRestTemplate.exchange("/invigilators/" + giraffeBadge, HttpMethod.GET, null, InvigilatorsResponse.class);
    }

    private void thenICanSeeThePotentialInvigilators() {
        assertEquals(HttpStatus.OK, invigilatorsResponse.getStatusCode());
        assertEquals(2, invigilatorsResponse.getBody().getNames().length);
        assertTrue(Arrays.asList(invigilatorsResponse.getBody().getNames()).contains(firstInvigilator));
        assertTrue(Arrays.asList(invigilatorsResponse.getBody().getNames()).contains(secondInvigilator));
    }

    @AfterEach
    public void tearDown() {
        DeleteTableRequest tableRequest = dynamoDBMapper
                .generateDeleteTableRequest(InvigilatorEntity.class);
        amazonDynamoDB.deleteTable(tableRequest);
    }
}
