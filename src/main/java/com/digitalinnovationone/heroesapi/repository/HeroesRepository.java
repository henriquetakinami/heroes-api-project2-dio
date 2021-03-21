package com.digitalinnovationone.heroesapi.repository;

import com.digitalinnovationone.heroesapi.document.Heroes;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository; // para habilitar requisições básicas do nosso repositório


// camada de acesso aos dados da nossa model que vamos criar a tabela
@EnableScan
public interface HeroesRepository extends CrudRepository<Heroes, String>{ // chamo Heroes e passo como repository


}
