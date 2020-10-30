package com.example.madetech.coreskills;

import com.example.madetech.coreskills.response.InvigilatorsResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PotentialInvigilatorsControllerTests {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void testCoreSkillCanBeSpecified() throws IOException {
        String coreSkill = "giraffe";

        ResponseEntity<InvigilatorsResponse> invigilatorsResponse = testRestTemplate.exchange("/invigilators/" + coreSkill, HttpMethod.GET, null, InvigilatorsResponse.class);

        assertEquals(HttpStatus.OK, invigilatorsResponse.getStatusCode());
    }
}
