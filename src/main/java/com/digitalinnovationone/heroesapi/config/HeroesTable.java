package com.digitalinnovationone.heroesapi.config;

import com.amazonaws.auth.AWSCredentials; // para comunicação com o aws
import com.amazonaws.auth.BasicAWSCredentials; //
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB; // client normal(mais facil de mexer) ou assincrono
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories; //ele informa para o spring data que estamos usando dynamo como repositorio
import org.springframework.beans.factory.annotation.Value; // saber qual valor que estamos lendo; não passamos as chaves no application.properties
import org.springframework.context.annotation.Bean; //uma das principais annotations do spring
import org.springframework.context.annotation.Configuration;
//import org.springframework.util.SpringUtils;
import org.springframework.util.StringUtils;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType; // tipode atributo que estou passando para o dynamodb
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput; // quanto de espaço vai ocupar no dynamo
import com.amazonaws.services.dynamodbv2.model.KeyType; // chave que vou utilizar
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement; // esquema de chave que vou utilizar
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition; // nome dos atributos/campos que vou estar utilizando
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import static com.digitalinnovationone.heroesapi.constants.HeroesConstant.REGION_DYNAMO; // importei depois de criar as constants
import static com.digitalinnovationone.heroesapi.constants.HeroesConstant.ENDPOINT_DYNAMO;
import java.lang.reflect.Array;
import java.util.Arrays;


@Configuration
@EnableDynamoDBRepositories
public class HeroesTable {
    public static void main(String[] args) throws Exception{

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(ENDPOINT_DYNAMO, REGION_DYNAMO))
                .build();

        DynamoDB dynamoDB = new DynamoDB(client);

        String tableName = "HeroesDemo_Table";

        try{
            Table table = dynamoDB.createTable(tableName,
                    Arrays.asList(new KeySchemaElement("id", KeyType.HASH)),
                    Arrays.asList(new AttributeDefinition("id", ScalarAttributeType.S)),
                    new ProvisionedThroughput(5L, 5l));
            table.waitForActive();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
