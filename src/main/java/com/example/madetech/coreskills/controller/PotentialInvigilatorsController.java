package com.example.madetech.coreskills.controller;
import com.example.madetech.coreskills.domain.InvigilatorDomain;
import com.example.madetech.coreskills.response.InvigilatorsResponse;
import com.example.madetech.coreskills.service.FindInvigilatorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PotentialInvigilatorsController {
    @Autowired
    private FindInvigilatorsService findInvigilatorsService;

    @GetMapping("/invigilators/{coreSkill}")
    public ResponseEntity<InvigilatorsResponse> getInvigilatorsForCoreSkill(@PathVariable String coreSkill){
        InvigilatorDomain[] response = findInvigilatorsService.getByCoreskill(coreSkill);
        var invigilatorsResponse = new InvigilatorsResponse();

        invigilatorsResponse.setNames(new String[]{response[0].getName()});
        return new ResponseEntity<>(invigilatorsResponse, HttpStatus.OK);
    }
}
