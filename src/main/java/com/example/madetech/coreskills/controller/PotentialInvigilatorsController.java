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
import java.util.ArrayList;

@RestController
public class PotentialInvigilatorsController {
    @Autowired
    private FindInvigilatorsService findInvigilatorsService;

    @GetMapping("/invigilators/{coreSkill}")
    public ResponseEntity<InvigilatorsResponse> getInvigilatorsForCoreSkill(@PathVariable String coreSkill){
        InvigilatorDomain[] response = findInvigilatorsService.getByCoreskill(coreSkill);

        return new ResponseEntity<>(mapDomainToResponse(response), HttpStatus.OK);
    }

    private InvigilatorsResponse mapDomainToResponse(InvigilatorDomain[] domainObjects) {
        ArrayList<String> names = new ArrayList<>();
        InvigilatorsResponse invigilatorsResponse = new InvigilatorsResponse();
        for (InvigilatorDomain invigilatorDomain : domainObjects)
        {
            names.add(invigilatorDomain.getName());
        }

        invigilatorsResponse.setNames(names.toArray(String[]::new));
        return invigilatorsResponse;
    }
}
