package com.example.madetech.coreskills.acceptance;
import com.example.madetech.coreskills.response.InvigilatorsResponse;
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
public class CanFindPotentialInvigilatorsTest {
    @Autowired
    TestRestTemplate testRestTemplate;

    private ResponseEntity<InvigilatorsResponse> invigilatorsResponse;
    private final String coreSkill = "giraffe";

    @Test
    public void canFindPotentialInvigilatorsTest() throws IOException {
        givenThereArePeopleWithAGiraffeBadge();
        whenICallTheInvigilatorsEndpointWithGiraffeBadge();
        thenICanSeeThePotentialInvigilators();
    }

    private void givenThereArePeopleWithAGiraffeBadge() {
        //TODO: Setup database with invigilators
    }

    private void whenICallTheInvigilatorsEndpointWithGiraffeBadge() {
        invigilatorsResponse = testRestTemplate.exchange("/invigilators/" + coreSkill, HttpMethod.GET, null, InvigilatorsResponse.class);
    }

    private void thenICanSeeThePotentialInvigilators() {
        assertEquals(HttpStatus.OK, invigilatorsResponse.getStatusCode());
        assertEquals("Joe", invigilatorsResponse.getBody().getNames()[0]);
    }
}