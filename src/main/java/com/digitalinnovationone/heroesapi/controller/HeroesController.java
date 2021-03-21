package com.digitalinnovationone.heroesapi.controller;

// praticamente importa todos os outros arquivos que criei
import com.digitalinnovationone.heroesapi.document.Heroes;
import com.digitalinnovationone.heroesapi.repository.HeroesRepository;
import com.digitalinnovationone.heroesapi.service.HeroesService;
import lombok.extern.slf4j.Slf4j; // tem uns logs
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.digitalinnovationone.heroesapi.constants.HeroesConstant.HERO_ENDPOINT_LOCAL;

@RestController //saber que é o controller da api
//@Slf4j //habilita o log
public class HeroesController {
    HeroesService heroesService;
    HeroesRepository heroesRepository;

    //instanciar o log
    private static final org.slf4j.Logger log=
            org.slf4j.LoggerFactory.getLogger(HeroesController.class); // habilitando na classe do próprio controller

    //criar construtor
    public HeroesController (HeroesService heroesService, HeroesRepository heroesRepository){
        this.heroesRepository = heroesRepository;
        this.heroesService = heroesService;

    }
    //criar os métodos

    @GetMapping(HERO_ENDPOINT_LOCAL)
    public Flux<Heroes> getAllItems(){
        log.info("requesting the list of all heroes");
        return heroesService.findAll();
    }

    @GetMapping(HERO_ENDPOINT_LOCAL+"/id")
    public Mono <ResponseEntity<Heroes>> findByIdHero(@PathVariable String id){
        log.info("requesting heroes by id"); // mostrar qual id
        return heroesService.findByIdHero(id)
                .map((item)-> new ResponseEntity<>(item,HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(HERO_ENDPOINT_LOCAL)
    @ResponseStatus(code = HttpStatus.CREATED)
    public Mono <Heroes> createHero(@RequestBody Heroes heroes){
        log.info("a new hero was created");
        return heroesService.save(heroes);
    }

    @DeleteMapping(HERO_ENDPOINT_LOCAL+"/id")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Mono <HttpStatus> deleteByIdHero(@PathVariable String id){
        heroesService.deleteByIdHero(id);
        log.info("delete a hero by id: ");
        return Mono.just(HttpStatus.CONTINUE);
    }

}
