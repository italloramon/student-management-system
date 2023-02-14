package com.ramon.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ramon.repository.*;

import java.util.Arrays;
import java.util.List;
import com.ramon.model.*;
import com.ramon.exception.*;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.ramon.model.courses.*;
import java.util.Comparator;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/")
public class StudentController {

    private final StudentRepository studentRepository;
    private final ResponsableRepository responsableRepository;
    private final EnglishRepository englishRepository;
    private final GeographyRepository geographyRepository;
    private final HistoryRepository historyRepository;
    private final MathematicsRepository mathematicsRepository;
    private final PortugueseRepository portugueseRepository;
    private final NoticeRepository noticeRepository;


    public StudentController(StudentRepository studentRepository, ResponsableRepository responsableRepository, EnglishRepository englishRepository, GeographyRepository geographyRepository, HistoryRepository historyRepository, MathematicsRepository mathematicsRepository, PortugueseRepository portugueseRepository, NoticeRepository noticeRepository) {
        this.studentRepository = studentRepository;
        this.responsableRepository = responsableRepository;
        this.englishRepository = englishRepository;
        this.geographyRepository = geographyRepository;
        this.historyRepository = historyRepository;
        this.mathematicsRepository = mathematicsRepository;
        this.portugueseRepository = portugueseRepository;
        this.noticeRepository = noticeRepository;
    }

    @GetMapping("students/")
    public List<StudentModel> getAllStudents() {
        return this.studentRepository.findAll();
    }

    @GetMapping("students/{id}")
    public StudentModel getStudent(@PathVariable Long id) {
        return this.studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
    }
    
    //@PostMapping("students/")
    //public StudentModel createStudent(@RequestBody StudentModel student) {
    //    return this.studentRepository.save(student);
    //}
    
    //@PostMapping(value = {"/search/", "/search"})    
    //public String search(@RequestParam Map<String,String> allRequestParams) {
    //    String test = "";
    //    String name = allRequestParams.get("studentName");
    //    String email = allRequestParams.get("studentEmail");
    //    ModelAndView model = new ModelAndView("Look at this:");
    //    test += ("This is the name: " + name + " And this is the email: " + email);
    //    
    //    return test;
    //}

    @PostMapping("students/")
    public StudentModel createStudent(@RequestBody Map<String, String> requestBody) {
        String nameResponsable = requestBody.get("nameResponsable");
        System.out.println(nameResponsable);
        String emailResponsable = requestBody.get("emailResponsable");
        String cpfResponsable = requestBody.get("cpfResponsable");
        ResponsableModel newResponsable = new ResponsableModel(nameResponsable, emailResponsable, cpfResponsable);
        responsableRepository.save(newResponsable);
        String name = requestBody.get("name");
        String email = requestBody.get("email");
        String cpf = requestBody.get("cpf");
        Double tuition = Double.valueOf(requestBody.get("tuition"));

        if (studentRepository.existsByCpf(cpf)) {
            throw new StudentAlreadyCreatedException(cpf);
        }

        English english = new English();
        this.englishRepository.save(english);
        Geography geography = new Geography();
        geographyRepository.save(geography);
        History history = new History();
        historyRepository.save(history);
        Mathematics mathematics = new Mathematics();
        mathematicsRepository.save(mathematics);
        Portuguese portuguese = new Portuguese();
        portugueseRepository.save(portuguese);
        StudentModel newStudent = new StudentModel(name, cpf, email, newResponsable, english, geography, history, mathematics, portuguese, tuition);
        return this.studentRepository.save(newStudent);
    }

    @PutMapping("students/{id}")
    public StudentModel updateStudent(@RequestBody StudentModel newStudent, @PathVariable Long id) {
        return this.studentRepository.findById(id).map(student -> {
            student.setName(newStudent.getName());
            student.setCpf(newStudent.getCpf());
            student.setEmail(newStudent.getEmail());
            return this.studentRepository.save(student);
        }).orElseThrow(() -> new StudentNotFoundException(id));
    }

    @DeleteMapping("students/{id}")
    public void deleteStudent(@PathVariable Long id) {
        this.studentRepository.deleteById(id);
    }

    @PostMapping("students/{idStudent}/{idCourse}/{score}")
    public void updateScore (@PathVariable Long idStudent, Long idCourse, @PathVariable Double score) {
        StudentModel student = studentRepository.findById(idStudent).get();
        English english = student.getEnglish();
        english.setScore1(score);
        englishRepository.save(english);
    }

    @GetMapping("students/getRanking")
    public StudentModel[] getRankingStudents() {
        List<StudentModel> studentsList = studentRepository.findAll();
        StudentModel[] studentsArray = new StudentModel[studentsList.size()];

        for (int i = 0; i  < studentsList.size(); i++) {
            studentsArray[i] = studentsList.get(i);
        }

        StudentModel[] studentsArraySorted = StudentController.getSorterdStudents(studentsArray, Comparator.comparing(StudentModel::getRankingStudent).reversed());

        return studentsArraySorted;
        
    }

    @PostMapping("students/login")
    public StudentModel loginStudent(@RequestBody Map<String, String> student) {
        String password = student.get("password");
        if (studentRepository.existsByCpf(password)) {
            StudentModel studentLogin = studentRepository.findByCpf(password);
            if (studentLogin.getEmail().equals(student.get("login"))) {
                return studentLogin;
            }
        }
        throw new StudentNotFoundException(-1l);
    }

    public static StudentModel[] getSorterdStudents(StudentModel[] students, Comparator<StudentModel> comparator) {
        StudentModel[] sorted = students.clone();
        Arrays.sort(sorted, comparator);
        return sorted;
    }

    @GetMapping("students/getNotices")
    public List<NoticeModel> getNotices() {
        return noticeRepository.findAll();
    }

}
