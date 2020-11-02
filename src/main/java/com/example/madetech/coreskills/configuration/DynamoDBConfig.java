package com.example.madetech.coreskills.configuration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Configuration
@EnableDynamoDBRepositories(basePackages = "repositories")
public class DynamoDBConfig {
    @Value("${amazon.dynamodb.endpoint}")
    private String amazonDynamoDBEndpoint;

    @Value("${amazon.aws.accesskey}")
    private String amazonAWSAccessKey;

    @Value("${amazon.aws.secretkey}")
    private String amazonAWSSecretKey;

    @Value("${amazon.aws.region}")
    private String awsRegion;

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        if (StringUtils.isEmpty(amazonDynamoDBEndpoint)) amazonDynamoDBEndpoint = "http://localhost:8000";
        if (StringUtils.isEmpty(awsRegion)) awsRegion = "eu-west-2";
        return AmazonDynamoDBClientBuilder.standard().withCredentials(
                new AWSStaticCredentialsProvider(new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey))).withEndpointConfiguration(
                new AwsClientBuilder.EndpointConfiguration(amazonDynamoDBEndpoint, awsRegion))
                .build();
    }
}