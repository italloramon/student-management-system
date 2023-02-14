package com.ramon.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.ramon.repository.*;
import com.ramon.model.*;
import com.ramon.model.courses.English;
import com.ramon.model.courses.Geography;
import com.ramon.model.courses.*;

@Component
public class PreLoadDatabase implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(PreLoadDatabase.class);

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final ResponsableRepository responsableRepository;
    private final EnglishRepository englishRepository;
    private final GeographyRepository geographyRepository;
    private final HistoryRepository historyRepository;
    private final MathematicsRepository mathematicsRepository;
    private final PortugueseRepository portugueseRepository;
    private final ExpenseRepository expenseRepository;

    public PreLoadDatabase(StudentRepository studentRepository, TeacherRepository teacherRepository, ResponsableRepository responsableRepository, EnglishRepository englishRepository, GeographyRepository geographyRepository, HistoryRepository historyRepository, MathematicsRepository mathematicsRepository, PortugueseRepository portugueseRepository, ExpenseRepository expenseRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.responsableRepository = responsableRepository;
        this.englishRepository = englishRepository;
        this.geographyRepository = geographyRepository;
        this.historyRepository = historyRepository;
        this.mathematicsRepository = mathematicsRepository;
        this.portugueseRepository = portugueseRepository;
        this.expenseRepository = expenseRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        studentRepository.deleteAll();
        teacherRepository.deleteAll();
        responsableRepository.deleteAll();

        ResponsableModel responsable = new ResponsableModel("Ramon", "124", "email@ramon");
        responsableRepository.save(responsable);

        responsableRepository.findAll().forEach((respon) -> {
            logger.info("{}", respon);
        });

        English english = new English();
        englishRepository.save(english);
        
        Geography geography = new Geography();
        geographyRepository.save(geography);

        History history = new History();
        historyRepository.save(history);

        Mathematics mathematics = new Mathematics();
        mathematicsRepository.save(mathematics);

        Portuguese portuguese = new Portuguese();
        portugueseRepository.save(portuguese);

        StudentModel student1 = new StudentModel("Carlos Eduardo", "12332145571", "cemg@ufal.com", responsableRepository.findById(1l).get(), englishRepository.findById(1l).get(), geographyRepository.findById(1l).get(), historyRepository.findById(1l).get(), mathematicsRepository.findById(1l).get(), portugueseRepository.findById(1l).get());
        studentRepository.save(student1);

        English english2 = new English();
        englishRepository.save(english2);
        
        Geography geography2 = new Geography();
        geographyRepository.save(geography2);

        History history2 = new History();
        historyRepository.save(history2);

        Mathematics mathematics2 = new Mathematics();
        mathematicsRepository.save(mathematics2);

        Portuguese portuguese2 = new Portuguese();
        portugueseRepository.save(portuguese2);

        StudentModel student2 = new StudentModel("José Dani", "11111111111", "djs@ufal.br", responsableRepository.findById(1l).get(), englishRepository.findById(2l).get(), geographyRepository.findById(2l).get(), historyRepository.findById(2l).get(), mathematicsRepository.findById(2l).get(), portugueseRepository.findById(2l).get());
        studentRepository.save(student2);

        studentRepository.findAll().forEach((student) -> {
            logger.info("{}", student.getResponsable().getNameResponsable());
        });

        TeacherModel teacher1 = new TeacherModel("Paraquinho", "66661111111", "automato@ufal.br", 5000.0);
        teacherRepository.save(teacher1);
        teacherRepository.findAll().forEach((teacher) -> {
            logger.info("{}", teacher);
        });


        ExpenseModel expense = new ExpenseModel("Water", "Basics", 1250.25);
        ExpenseModel expense2 = new ExpenseModel("Fire", "Basics", 120.5);
        expenseRepository.save(expense);
        expenseRepository.save(expense2);

        //repository.save(new City("Bratislava", 432000));
        //repository.save(new City("Budapest", 1759000));
        //repository.save(new City("Prague", 1280000));

        
    }
}

