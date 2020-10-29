package com.example.madetech.coreskills;

import com.example.madetech.coreskills.response.InvigilatorsResponse;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PotentialInvigilatorsControllerTests {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void testCoreSkillCanBeSpecified() throws IOException {
        String coreSkill = "giraffe";

        ResponseEntity<InvigilatorsResponse> invigilatorsResponse = testRestTemplate.exchange("/invigilators/" + coreSkill, HttpMethod.GET, null, InvigilatorsResponse.class);

        assertEquals(invigilatorsResponse , HttpStatus.SC_OK);
    }
}
