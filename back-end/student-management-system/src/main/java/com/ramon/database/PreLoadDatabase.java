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

        Responsable responsable = new Responsable("Trafalgar Law", "12442111110", "traw@gmail.com");
        responsableRepository.save(responsable);

        Responsable responsable2 = new Responsable("Nico Robin", "34678765432", "nrobin@gmail.com");
        responsableRepository.save(responsable2);

        Responsable responsable3 = new Responsable("Usopp", "56473829102", "usopp@gmail.com");
        responsableRepository.save(responsable3);

        Responsable responsable4 = new Responsable("Brook", "90129384756", "brook@gmail.com");
        responsableRepository.save(responsable4);

        Responsable responsable5 = new Responsable("Jinbe", "87564321901", "jinbe@gmail.com");
        responsableRepository.save(responsable5);


        // responsableRepository.findAll().forEach((respon) -> {
        //     logger.info("{}", respon);
        // });

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

        Student student1 = new Student("Monkey D. Luffy", "12332145571", "luffy@gmail.com", responsableRepository.findById(1l).get(), englishRepository.findById(1l).get(), geographyRepository.findById(1l).get(), historyRepository.findById(1l).get(), mathematicsRepository.findById(1l).get(), portugueseRepository.findById(1l).get(), 254.2);
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

        Student student2 = new Student("Son Goku", "11111111111", "kamehameha@gmail.com", responsableRepository.findById(1l).get(), englishRepository.findById(2l).get(), geographyRepository.findById(2l).get(), historyRepository.findById(2l).get(), mathematicsRepository.findById(2l).get(), portugueseRepository.findById(2l).get(), 1250.345);
        studentRepository.save(student2);
        
        English english3 = new English();
        englishRepository.save(english3);
        
        Geography geography3 = new Geography();
        geographyRepository.save(geography3);

        History history3 = new History();
        historyRepository.save(history3);

        Mathematics mathematics3 = new Mathematics();
        mathematicsRepository.save(mathematics3);

        Portuguese portuguese3 = new Portuguese();
        portugueseRepository.save(portuguese3);
            
        Student student3 = new Student("Nami", "34567891234", "nami@gmail.com", responsableRepository.findById(1l).get(), englishRepository.findById(3l).get(), geographyRepository.findById(3l).get(), historyRepository.findById(3l).get(), mathematicsRepository.findById(3l).get(), portugueseRepository.findById(3l).get(), 310.5);
        studentRepository.save(student3);

        English english4 = new English();
        englishRepository.save(english4);

        Geography geography4 = new Geography();
        geographyRepository.save(geography4);

        History history4 = new History();
        historyRepository.save(history4);

        Mathematics mathematics4 = new Mathematics();
        mathematicsRepository.save(mathematics4);

        Portuguese portuguese4 = new Portuguese();
        portugueseRepository.save(portuguese4);

        Student student4 = new Student("Sanji", "78901234567", "sanji@gmail.com", responsableRepository.findById(2l).get(), englishRepository.findById(4l).get(), geographyRepository.findById(4l).get(), historyRepository.findById(4l).get(), mathematicsRepository.findById(4l).get(), portugueseRepository.findById(4l).get(), 275.8);
        studentRepository.save(student4);

        English english5 = new English();
        englishRepository.save(english5);

        Geography geography5 = new Geography();
        geographyRepository.save(geography5);

        History history5 = new History();
        historyRepository.save(history5);

        Mathematics mathematics5 = new Mathematics();
        mathematicsRepository.save(mathematics5);

        Portuguese portuguese5 = new Portuguese();
        portugueseRepository.save(portuguese5);

        Student student5 = new Student("Zoro", "90123456789", "zoro@gmail.com", responsableRepository.findById(3l).get(), englishRepository.findById(5l).get(), geographyRepository.findById(5l).get(), historyRepository.findById(5l).get(), mathematicsRepository.findById(5l).get(), portugueseRepository.findById(5l).get(), 298.3);
        studentRepository.save(student5);

        // studentRepository.findAll().forEach((student) -> {
        //     logger.info("{}", student.getName());
        // });

        TeacherModel teacher1 = new TeacherModel("Hatake Kakashi", "66661111111", "thousandjutsus@gmail.com", 5000.0, "English");
        teacherRepository.save(teacher1);
        TeacherModel teacher2 = new TeacherModel("Red-Haired Shanks", "666432111111", "onearm@gmail.com", 5000.0, "Portuguese");
        teacherRepository.save(teacher2);
        TeacherModel teacher3 = new TeacherModel("Boa Hancock", "888876543210", "boahancock@gmail.com", 4500.0, "Mathematics");
        teacherRepository.save(teacher3);

        TeacherModel teacher4 = new TeacherModel("Trafalgar D. Water Law", "345678901234", "trafalgarlaw@gmail.com", 5500.0, "Science");
        teacherRepository.save(teacher4);

        TeacherModel teacher5 = new TeacherModel("Portgas D. Ace", "777788889999", "portgasace@gmail.com", 4000.0, "Geography");
        teacherRepository.save(teacher5);

        TeacherModel teacher6 = new TeacherModel("Kuzan", "333321111222", "kuzan@gmail.com", 6000.0, "History");
        teacherRepository.save(teacher6);

        // teacherRepository.findAll().forEach((teacher) -> {
        //     logger.info("{}", teacher);
        // });


        ExpenseModel expense = new ExpenseModel("Water", "Basics", 1250.25);
        ExpenseModel expense2 = new ExpenseModel("Cleaning Products", "Basics", 120.5);
        expenseRepository.save(expense);
        expenseRepository.save(expense2);

        //repository.save(new City("Bratislava", 432000));
        //repository.save(new City("Budapest", 1759000));
        //repository.save(new City("Prague", 1280000));

        
    }
}

