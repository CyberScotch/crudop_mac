package com.grpcdemo.crudop.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.grpcdemo.crudop.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public String save(User user)
    {
        dynamoDBMapper.save(user);
        return  "User saved";
    }

    public User getEmployeeById(String id)
    {
        return dynamoDBMapper.load(User.class,id);
    }

    public String delete(String id)
    {
        User user=dynamoDBMapper.load(User.class,id);
        dynamoDBMapper.delete(user);
        return "User removed";
    }

    public String update(String id,User user)
    {
        dynamoDBMapper.save(user,
                new DynamoDBSaveExpression()
        .withExpectedEntry("id", //primary partition key in awsDynamoDB
                new ExpectedAttributeValue(
                        new AttributeValue().withS(id)
                )));
        return id;
    }
}
