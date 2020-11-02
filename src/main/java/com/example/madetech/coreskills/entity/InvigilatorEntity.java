package com.example.madetech.coreskills.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.List;

@DynamoDBTable(tableName = "Invigilator")
public class InvigilatorEntity {
    private String name;
    private List<String> coreSkills;
}
