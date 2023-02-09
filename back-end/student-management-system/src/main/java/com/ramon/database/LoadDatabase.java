package com.ramon.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.ramon.repository.StudentRepository;
import com.ramon.model.StudentModel;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(StudentRepository repository) {

    return args -> {
      log.info("Preloading " + repository.save(new StudentModel("Carlos Eduardo", "12332145571", "cemg@ufal.com")));
      log.info("Preloading " + repository.save(new StudentModel("José Dani", "11111111111", "djs@ufal.br")));
    };
  }
}