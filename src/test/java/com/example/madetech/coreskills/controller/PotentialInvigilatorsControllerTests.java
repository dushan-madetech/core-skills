package com.example.madetech.coreskills.controller;

import com.example.madetech.coreskills.domain.InvigilatorDomain;
import com.example.madetech.coreskills.service.FindInvigilatorsService;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

        when(findInvigilatorsService.getByCoreskill(giraffe)).thenReturn(new InvigilatorDomain[]{new InvigilatorDomain("Dushan", new ArrayList<>())});
        when(findInvigilatorsService.getByCoreskill(wolf)).thenReturn(new InvigilatorDomain[]{new InvigilatorDomain( "Ting", new ArrayList<>())});
        when(findInvigilatorsService.getByCoreskill(dragon)).thenReturn(new InvigilatorDomain[]{new InvigilatorDomain( "Yusuf", new ArrayList<>())});

        assertEquals("Dushan", invigilatorsController.getInvigilatorsForCoreSkill(giraffe).getBody().getNames()[0]);
        assertEquals("Ting", invigilatorsController.getInvigilatorsForCoreSkill(wolf).getBody().getNames()[0]);
        assertEquals("Yusuf", invigilatorsController.getInvigilatorsForCoreSkill(dragon).getBody().getNames()[0]);
    }
}
