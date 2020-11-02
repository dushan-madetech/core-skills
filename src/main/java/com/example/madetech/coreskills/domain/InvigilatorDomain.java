package com.example.madetech.coreskills.domain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class InvigilatorDomain {
    private String name;
    private List<String> coreSkills;
}
