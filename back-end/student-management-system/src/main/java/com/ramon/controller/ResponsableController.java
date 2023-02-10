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
            responsable.setName(newResponsable.getName());
            responsable.setCpf(newResponsable.getCpf());
            responsable.setEmail(newResponsable.getEmail());
            return this.responsableRepository.save(responsable);
        }).orElseThrow(() -> new ResponsableNotFoundException(id));
    }

    //TO-DO
    //When delete a responsable your students should be delete too
    //@DeleteMapping("responsables/{id}")
    //public void deleteResponsable(@PathVariable Long id) {
    //    this.responsableRepository.deleteById(id);
    //}

}