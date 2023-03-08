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
public class StudentController extends MainController<StudentModel> {

    private final StudentRepository studentRepository;
    private final ResponsableRepository responsableRepository;
    private final EnglishRepository englishRepository;
    private final GeographyRepository geographyRepository;
    private final HistoryRepository historyRepository;
    private final MathematicsRepository mathematicsRepository;
    private final PortugueseRepository portugueseRepository;
    private final NoticeRepository noticeRepository;
    private final TeacherRepository teacherRepository;


    public StudentController(StudentRepository studentRepository, ResponsableRepository responsableRepository, EnglishRepository englishRepository, GeographyRepository geographyRepository, HistoryRepository historyRepository, MathematicsRepository mathematicsRepository, PortugueseRepository portugueseRepository, NoticeRepository noticeRepository, TeacherRepository teacherRepository) {
        this.studentRepository = studentRepository;
        this.responsableRepository = responsableRepository;
        this.englishRepository = englishRepository;
        this.geographyRepository = geographyRepository;
        this.historyRepository = historyRepository;
        this.mathematicsRepository = mathematicsRepository;
        this.portugueseRepository = portugueseRepository;
        this.noticeRepository = noticeRepository;
        this.teacherRepository = teacherRepository;
    }

	@Override
	@GetMapping("students/")
	public List<StudentModel> getAll() {
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
        //System.out.println(nameResponsable);
        String emailResponsable = requestBody.get("emailResponsable");
        String cpfResponsable = requestBody.get("cpfResponsable");

        if (responsableRepository.existsByCpfResponsable(cpfResponsable)) {
            ResponsableModel newResponsable = responsableRepository.findByCpfResponsable(cpfResponsable);
        } else {
            ResponsableModel newResponsable = new ResponsableModel(nameResponsable, emailResponsable, cpfResponsable);
            responsableRepository.save(newResponsable);
        }

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
        StudentModel newStudent = new StudentModel(name, cpf, email, responsableRepository.findByCpfResponsable(cpfResponsable), english, geography, history, mathematics, portuguese, tuition);
        return this.studentRepository.save(newStudent);
	}

	@Override
	@PutMapping("students/{id}")
	public StudentModel update(@RequestBody StudentModel newStudent, @PathVariable Long id) {
		StudentModel student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        if (newStudent.getName() != null) {
            student.setName(newStudent.getName());
        }
        if (newStudent.getCpf() != null) {
            student.setCpf(newStudent.getCpf());
        }
        if (newStudent.getEmail() != null) {
            student.setEmail(newStudent.getEmail());
        }
        if (newStudent.getTuition() != null) {
            student.setTuition(newStudent.getTuition());
        }
        if (newStudent.getResponsable() != null) {
            student.setResponsable(newStudent.getResponsable());
        }
        if (newStudent.getEnglish() != null) {
            student.setEnglish(newStudent.getEnglish());
        }
        if (newStudent.getGeography() != null) {
            student.setGeography(newStudent.getGeography());
        }
        if (newStudent.getHistory() != null) {
            student.setHistory(newStudent.getHistory());
        }
        if (newStudent.getPortuguese() != null) {
            student.setPortuguese(newStudent.getPortuguese());
        }
        if (newStudent.getMathematics() != null) {
            student.setMathematics(newStudent.getMathematics());
        }
        return studentRepository.save(student);
	}

	@Override
	@DeleteMapping("students/{id}")
	public void delete(@PathVariable Long id) {
		this.studentRepository.deleteById(id);
		
	}

    @PostMapping("students/{idStudent}/{idTeacher}/{bimester}/{score}")
    public void updateScore (@PathVariable Long idStudent, @PathVariable Long idTeacher, @PathVariable Integer bimester, @PathVariable Double score) {
        StudentModel student = studentRepository.findById(idStudent).get();
        TeacherModel teacher = teacherRepository.findById(idTeacher).get();

        if(teacher.getRole().equals(Role.TEACHERENGLISH)) {
            English english = student.getEnglish();
            if (bimester == 1) english.setScore1(score);
            else if (bimester == 2) english.setScore2(score);
            else if (bimester == 3) english.setScore3(score);
            else if (bimester == 4) english.setScore4(score);
            englishRepository.save(english);
        } else if(teacher.getRole().equals(Role.TEACHERPORTUGUESE)) { 
            Portuguese portuguese = student.getPortuguese();
            if (bimester == 1) portuguese.setScore1(score);
            else if (bimester == 2) portuguese.setScore2(score);
            else if (bimester == 3) portuguese.setScore3(score);
            else if (bimester == 4) portuguese.setScore4(score);
            portugueseRepository.save(portuguese);            
        } else if(teacher.getRole().equals(Role.TEACHERHISTORY)) {
            History history = student.getHistory();
            if (bimester == 1) history.setScore1(score);
            else if (bimester == 2) history.setScore2(score);
            else if (bimester == 3) history.setScore3(score);
            else if (bimester == 4) history.setScore4(score);
            historyRepository.save(history);  
        } else if(teacher.getRole().equals(Role.TEACHERGEOGRAPHY)) {
            Geography geography = student.getGeography();
            if (bimester == 1) geography.setScore1(score);
            else if (bimester == 2) geography.setScore2(score);
            else if (bimester == 3) geography.setScore3(score);
            else if (bimester == 4) geography.setScore4(score);
            geographyRepository.save(geography);  
        } else if (teacher.getRole().equals(Role.TEACHERMATHEMATICS)) {
            Mathematics mathematics = student.getMathematics();
            if (bimester == 1) mathematics.setScore1(score);
            else if (bimester == 2) mathematics.setScore2(score);
            else if (bimester == 3) mathematics.setScore3(score);
            else if (bimester == 4) mathematics.setScore4(score);
            mathematicsRepository.save(mathematics); 
        }


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

	@Override
	@PostMapping("students/login")
	public StudentModel create(@RequestBody Map<String, String> student) {
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
