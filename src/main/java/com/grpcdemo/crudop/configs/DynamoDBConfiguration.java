package com.grpcdemo.crudop.configs;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamoDBConfiguration {

    @Bean
    public DynamoDBMapper dynamoDBMapper()
    {
        return  new DynamoDBMapper(buildAmazonDynamoDB());
    }

    private AmazonDynamoDB buildAmazonDynamoDB()
    {
        return AmazonDynamoDBClientBuilder
                .standard().withEndpointConfiguration(
                    new AwsClientBuilder.EndpointConfiguration(
                            "",""
                            //signing_region is the location : us-east-1
                            //service_endpoint : dynamodb.us-east-1.amazonaws.com
                    )
                ).withCredentials(
                        new AWSStaticCredentialsProvider(
                                new BasicAWSCredentials(
                                        "",""
                                        //access and user keys of user
                                        //cross-check port in ap.properties file
                                )
                        )
                )
                .build();
    }
}
