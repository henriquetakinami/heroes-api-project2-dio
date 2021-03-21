package com.digitalinnovationone.heroesapi.document;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import org.springframework.data.annotation.Id; // para acessar a chave primaria do nosso banco
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data // indicar que é uma classe de dados
@NoArgsConstructor
@DynamoDBTable(tableName = "HeroesDemo_Table")

public class Heroes {
    //criar atributos da tabela com modificadores de acesso
    @Id
    @DynamoDBHashKey(attributeName = "id") // definindo minha chave primaria
    private String id;

    @DynamoDBAttribute(attributeName = "name")
    private String name;

    @DynamoDBAttribute(attributeName = "universe")
    private String universe;

    @DynamoDBAttribute(attributeName = "films")
    private int films;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUniverse() {
        return universe;
    }

    public int getFilms() {
        return films;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUniverse(String universe) {
        this.universe = universe;
    }

    public void setFilms(int films) {
        this.films = films;
    }

    // gerar construtor para as annotations

    public Heroes(String id, String name, String universe, int films){
        this.id = id;
        this.name = name;
        this.universe = universe;
        this.films = films;
    }
}
