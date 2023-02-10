package com.ramon.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.ramon.repository.StudentRepository;
import com.ramon.model.StudentModel;
import com.ramon.model.TeacherModel;
import com.ramon.model.ResponsableModel;
import com.ramon.repository.TeacherRepository;
import com.ramon.repository.ResponsableRepository;

//@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  //@Bean
  CommandLineRunner initDatabase(StudentRepository studentRepository, TeacherRepository teacherRepository, ResponsableRepository responsableRepository) {
    
    return args -> {
      log.info("Preloading " + responsableRepository.save(new ResponsableModel("Ramon", "124", "email@ramon")));
      //log.info("Saved " + responsableRepository.findById(1l).get());
      //log.info("Preloading " + studentRepository.save(new StudentModel("Carlos Eduardo", "12332145571", "cemg@ufal.com", responsableRepository.findById(1l).get())));
      //log.info("Preloading " + studentRepository.save(new StudentModel("José Dani", "11111111111", "djs@ufal.br", responsableRepository.findById(1l).get())));
      //log.info("Preloading " + teacherRepository.save(new TeacherModel("Paraquinho", "66661111111", "automato@ufal.br")));
    };
  }
}