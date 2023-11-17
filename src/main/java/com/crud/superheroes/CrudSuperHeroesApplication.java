package com.crud.superheroes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class CrudSuperHeroesApplication {

  public static void main(String[] args) {
    SpringApplication.run(CrudSuperHeroesApplication.class, args);
  }
}
