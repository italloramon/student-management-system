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
import com.ramon.model.Student;
import com.ramon.model.Responsable;
import com.ramon.exception.*;

@RestController
@RequestMapping("/api/")
public class ResponsableController {
    private final ResponsableRepository responsableRepository;

    public ResponsableController(ResponsableRepository responsableRepository) {
        this.responsableRepository = responsableRepository;
    }

    @GetMapping("responsables/")
    public List<Responsable> getAllResponsables() {
        return this.responsableRepository.findAll();
    }

    @GetMapping("responsables/{id}")
    public Responsable getResponsable(@PathVariable Long id) {
        return this.responsableRepository.findById(id).orElseThrow(() -> new ResponsableNotFoundException(id));
    }

    @PostMapping("responsables/")
    public Responsable createResponsable(@RequestBody Responsable responsable) {
        return this.responsableRepository.save(responsable);
    }

    @PutMapping("responsables/{id}")
    public Responsable updateResponsable(@RequestBody Responsable newResponsable, @PathVariable Long id) {
        Responsable responsable = responsableRepository.findById(id).orElseThrow(() -> new ResponsableNotFoundException(id));
        if (newResponsable.getNameResponsable() != null) {
            responsable.setNameResponsable(newResponsable.getNameResponsable());
        }
        if (newResponsable.getCpfResponsable() != null) {
            responsable.setCpfResponsable(newResponsable.getCpfResponsable());
        }
        if (newResponsable.getEmailResponsable() != null) {
            responsable.setEmailResponsable(newResponsable.getEmailResponsable());
        }
        
        return responsableRepository.save(responsable);
    }

    @DeleteMapping("responsables/{id}")
    public void deleteResponsable(@PathVariable Long id) {
        this.responsableRepository.deleteById(id);
    }

    @PostMapping("responsables/login")
    public Responsable loginResponsable(@RequestBody Map<String, String> responsable) {
        String password = responsable.get("password");
        if (responsableRepository.existsByCpfResponsable(password)) {
            Responsable responsableLogin = responsableRepository.findByCpfResponsable(password);
            if (responsableLogin.getEmailResponsable().equals(responsable.get("login"))) {
                return responsableLogin;
            }
        }
        throw new ResponsableNotFoundException(-1l);
    }

    @GetMapping("responsables/{id}/total")
    public Map<String, Double> getTotalTuition(@PathVariable Long id) {
        Responsable responsable = responsableRepository.findById(id).get();
        Map<String, Double> studentsTuition = new HashMap<>();
        Double total = 0.0;
        for (Student student : responsable.getStudents()) {
            studentsTuition.put(student.getName(), student.getTuition());
            total += student.getTuition();
        }
        studentsTuition.put("Total to pay:", total);

        return studentsTuition;
    }

}