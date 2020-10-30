package com.example.madetech.coreskills.controller;
import com.example.madetech.coreskills.response.InvigilatorsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PotentialInvigilatorsController {
    @GetMapping("/invigilators/{coreSkill}")
    public ResponseEntity<InvigilatorsResponse> getInvigilatorsForCoreSkill(@PathVariable String coreSkill){
        var invigilatorsResponse = new InvigilatorsResponse();
        return new ResponseEntity<>(invigilatorsResponse, HttpStatus.OK);
    }
}
