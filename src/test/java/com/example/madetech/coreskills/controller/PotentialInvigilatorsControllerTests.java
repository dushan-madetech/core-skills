package com.example.madetech.coreskills.controller;

import com.example.madetech.coreskills.domain.InvigilatorDomain;
import com.example.madetech.coreskills.response.InvigilatorsResponse;
import com.example.madetech.coreskills.service.FindInvigilatorsService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PotentialInvigilatorsControllerTests {
    @InjectMocks
    PotentialInvigilatorsController invigilatorsController;

    @Mock
    FindInvigilatorsService findInvigilatorsService;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void testCoreSkillCanBeSpecified() throws IOException {
        // TODO this test is probably redundant should be covered by the acceptance test
        String coreSkill = "giraffe";

        ResponseEntity<InvigilatorsResponse> invigilatorsResponse = testRestTemplate.exchange("/invigilators/" + coreSkill, HttpMethod.GET, null, InvigilatorsResponse.class);

        assertEquals(HttpStatus.OK, invigilatorsResponse.getStatusCode());
        assertEquals("Joe", invigilatorsResponse.getBody().getNames()[0]);
    }

    @Test
    public void testControllerCallsFindInvigilatorsServiceWithAGivenCoreSkill() throws IOException {
        String giraffe = "giraffe";
        String wolf = "wolf";
        String dragon = "dragon";

        when(findInvigilatorsService.getByCoreskill(giraffe)).thenReturn(new InvigilatorDomain[]{new InvigilatorDomain("Dushan")});
        when(findInvigilatorsService.getByCoreskill(wolf)).thenReturn(new InvigilatorDomain[]{new InvigilatorDomain( "Ting")});
        when(findInvigilatorsService.getByCoreskill(dragon)).thenReturn(new InvigilatorDomain[]{new InvigilatorDomain( "Yusuf")});

        ResponseEntity<InvigilatorsResponse> response = invigilatorsController.getInvigilatorsForCoreSkill(giraffe);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Dushan", invigilatorsController.getInvigilatorsForCoreSkill(giraffe).getBody().getNames()[0]);
        assertEquals("Ting", invigilatorsController.getInvigilatorsForCoreSkill(wolf).getBody().getNames()[0]);
        assertEquals("Yusuf", invigilatorsController.getInvigilatorsForCoreSkill(dragon).getBody().getNames()[0]);
    }
}
