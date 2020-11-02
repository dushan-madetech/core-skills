package com.example.madetech.coreskills.controller;

import com.example.madetech.coreskills.domain.InvigilatorDomain;
import com.example.madetech.coreskills.service.FindInvigilatorsService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PotentialInvigilatorsControllerTests {
    @InjectMocks
    PotentialInvigilatorsController invigilatorsController;

    @Mock
    FindInvigilatorsService findInvigilatorsService;

    @Test
    public void testControllerCallsFindInvigilatorsServiceWithAGivenCoreSkill() throws IOException {
        String giraffe = "giraffe";
        String wolf = "wolf";
        String dragon = "dragon";

        when(findInvigilatorsService.getByCoreskill(giraffe)).thenReturn(new InvigilatorDomain[]{new InvigilatorDomain("Dushan")});
        when(findInvigilatorsService.getByCoreskill(wolf)).thenReturn(new InvigilatorDomain[]{new InvigilatorDomain( "Ting")});
        when(findInvigilatorsService.getByCoreskill(dragon)).thenReturn(new InvigilatorDomain[]{new InvigilatorDomain( "Yusuf")});

        assertEquals("Dushan", invigilatorsController.getInvigilatorsForCoreSkill(giraffe).getBody().getNames()[0]);
        assertEquals("Ting", invigilatorsController.getInvigilatorsForCoreSkill(wolf).getBody().getNames()[0]);
        assertEquals("Yusuf", invigilatorsController.getInvigilatorsForCoreSkill(dragon).getBody().getNames()[0]);
    }
}
