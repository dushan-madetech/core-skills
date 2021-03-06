package com.example.madetech.coreskills.entity;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.util.Set;

@Builder
@AllArgsConstructor
@DynamoDBTable(tableName = "Invigilator")
public class InvigilatorEntity {
    private String id;
    private String name;
    private Set<String> coreSkills;

    public InvigilatorEntity(){}

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    public String getId() {
        return id;
    }
    public void setId(String name) {
        this.id = name;
    }

    @DynamoDBAttribute
    public String getName() { return name; }
    @DynamoDBAttribute
    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute
    public Set<String> getCoreSkills() { return coreSkills; };
    @DynamoDBAttribute
    public void setCoreSkills(Set<String> coreSkills) {
        this.coreSkills = coreSkills;
    }
}
