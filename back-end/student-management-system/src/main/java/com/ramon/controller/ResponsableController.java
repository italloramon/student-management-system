package com.ramon.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ramon.repository.ResponsableRepository;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.ramon.model.StudentModel;
import com.ramon.model.ResponsableModel;
import com.ramon.exception.*;

@RestController
@RequestMapping("/api/")
public class ResponsableController {
    private final ResponsableRepository responsableRepository;

    public ResponsableController(ResponsableRepository responsableRepository) {
        this.responsableRepository = responsableRepository;
    }

    @GetMapping("responsables/")
    public List<ResponsableModel> getAllResponsables() {
        return this.responsableRepository.findAll();
    }

    @GetMapping("responsables/{id}")
    public ResponsableModel getResponsable(@PathVariable Long id) {
        return this.responsableRepository.findById(id).orElseThrow(() -> new ResponsableNotFoundException(id));
    }

    @PostMapping("responsables/")
    public ResponsableModel createResponsable(@RequestBody ResponsableModel responsable) {
        return this.responsableRepository.save(responsable);
    }

    @PutMapping("responsables/{id}")
    public ResponsableModel updateResponsable(@RequestBody ResponsableModel newResponsable, @PathVariable Long id) {
        return this.responsableRepository.findById(id).map(responsable -> {
            responsable.setNameResponsable(newResponsable.getNameResponsable());
            responsable.setCpfResponsable(newResponsable.getCpfResponsable());
            responsable.setEmailResponsable(newResponsable.getEmailResponsable());
            return this.responsableRepository.save(responsable);
        }).orElseThrow(() -> new ResponsableNotFoundException(id));
    }

    //TO-DO
    //When delete a responsable your students should be delete too
    //@DeleteMapping("responsables/{id}")
    //public void deleteResponsable(@PathVariable Long id) {
    //    this.responsableRepository.deleteById(id);
    //}

    @PostMapping("responsables/login")
    public ResponsableModel loginResponsable(@RequestBody Map<String, String> responsable) {
        String password = responsable.get("password");
        if (responsableRepository.existsByCpfResponsable(password)) {
            ResponsableModel responsableLogin = responsableRepository.findByCpfResponsable(password);
            if (responsableLogin.getEmailResponsable().equals(responsable.get("login"))) {
                return responsableLogin;
            }
        }
        throw new ResponsableNotFoundException(-1l);
    }

    @GetMapping("responsables/{id}")
    public Map<String, Double> getTotalTuition(@PathVariable Long id) {
        ResponsableModel responsable = responsableRepository.findById(id).get();
        Map<String, Double> studentsTuition = new HashMap<>();
        Double total = 0.0;
        for (StudentModel student : responsable.getStudents()) {
            studentsTuition.put(student.getName(), student.getTuition());
            total += student.getTuition();
        }
        studentsTuition.put("Total to pay:", total);

        return studentsTuition;
    }

}