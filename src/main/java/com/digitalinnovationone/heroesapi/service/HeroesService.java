package com.digitalinnovationone.heroesapi.service;

import com.digitalinnovationone.heroesapi.document.Heroes;
import com.digitalinnovationone.heroesapi.repository.HeroesRepository;
import org.springframework.stereotype.Service; // anotation para reconhecer como uma service
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service

// criar a service, quem vai ser o nosso serviço de aplicação para o controler de exceção
public class HeroesService {
    //fazer métodos do controller
    private final HeroesRepository heroesRepository;

    public HeroesService(HeroesRepository heroesRepository){
        this.heroesRepository = heroesRepository;
    }

    //primeiro métodp -
    public Flux<Heroes> findAll(){
        return Flux.fromIterable(this.heroesRepository.findAll());
    }

    //Mono porque é um unico dado
    public Mono <Heroes> findByIdHero(String id){
        return Mono.justOrEmpty(this.heroesRepository.findById(id));
    }

    public Mono <Heroes> save(Heroes heroes){
        return Mono.justOrEmpty(this.heroesRepository.save(heroes));
    }

    public Mono <Boolean> deleteByIdHero (String id){
        heroesRepository.deleteById(id);
        return Mono.just(true);
    }
}
