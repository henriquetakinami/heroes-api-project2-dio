package com.digitalinnovationone.heroesapi.config;

import com.amazonaws.auth.AWSCredentials; // para comunicação com o aws
import com.amazonaws.auth.BasicAWSCredentials; //
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB; // client normal(mais facil de mexer) ou assincrono
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories; //ele informa para o spring data que estamos usando dynamo como repositorio
import org.springframework.beans.factory.annotation.Value; // saber qual valor que estamos lendo; não passamos as chaves no application.properties
import org.springframework.context.annotation.Bean; //uma das principais annotations do spring
import org.springframework.context.annotation.Configuration;
//import org.springframework.util.SpringUtils;
import org.springframework.util.StringUtils;

@Configuration
@EnableDynamoDBRepositories // reconhecer as configs do dynamo

public class DynamoConfig {
    @Value("${amazon.dynamodb.endpoint}") // para passarmos o valor das chaves
    private String amazonDynamoDBEndpoint;

    @Value("${aws_access_key_id}") // para passarmos placeholder com o valor
    private String amazonAWSAcesskey;

    @Value("${amazon_secret_access_key}") // para passarmos o valor das chaves
    private String amazonAWSSecretkey;

    @Bean // instanciar o dynamo
    public AmazonDynamoDB amazonDynamoDB(){
        AmazonDynamoDB amazonDynamoDB = new AmazonDynamoDBClient(amazonAWSCredentials());
        if (!StringUtils.isEmpty(amazonDynamoDBEndpoint)){
            amazonDynamoDB.setEndpoint(amazonDynamoDBEndpoint);
        }
        return amazonDynamoDB;

    }

    @Bean
    public AWSCredentials amazonAWSCredentials(){
        return new BasicAWSCredentials(
                amazonAWSAcesskey,amazonAWSSecretkey);

    }

}
