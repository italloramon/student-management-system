package com.ramon.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.CollectionTable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ElementCollection;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;

import com.ramon.model.*;
import com.ramon.model.courses.*;
import com.ramon.repository.EnglishRepository;
import com.ramon.repository.GeographyRepository;
import com.ramon.repository.HistoryRepository;
import com.ramon.repository.MathematicsRepository;
import com.ramon.repository.PortugueseRepository;

@Entity
@Table(name = "student")
@NoArgsConstructor
@ToString @EqualsAndHashCode
public class Student {
    
    @Id
    @Column(name = "student_id")
    @Getter private Long id;

    @Column(name = "student_name")
    @Getter @Setter private String name;

    @Column(name = "student_cpf")
    @Getter @Setter private String cpf;

    @Column(name = "student_email")
    @Getter @Setter private String email;

    @ManyToOne
    @JoinColumn(name = "responsable_id", nullable = false)
    @Getter @Setter private Responsable responsable;

    @Column(name = "student_tuition")
    @Getter @Setter private Double tuition;

    //@ElementCollection
    //@Getter @Setter private List<CourseModel> courses = new ArrayList<>();
    //@ManyToOne

    @ManyToOne
    @JoinColumn(name = "english_id")
    @Getter @Setter private English english;

    @ManyToOne
    @JoinColumn(name = "geography_id")
    @Getter @Setter private Geography geography;

    @ManyToOne
    @JoinColumn(name = "history_id")
    @Getter @Setter private History history;

    @ManyToOne
    @JoinColumn(name = "mathematics_id")
    @Getter @Setter private Mathematics mathematics;

    @ManyToOne
    @JoinColumn(name = "portuguese_id")
    @Getter @Setter private Portuguese portuguese;

    //@Getter @Setter private Double scoreRanking;
     
    
    public Student(String name, String cpf, String email, Responsable responsable, English english, Geography geography, History history, Mathematics mathematics, Portuguese portuguese, Double tuition) {
        this.id = GenerateId.id++;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.responsable = responsable;
        this.english = english;
        this.geography = geography;
        this.history = history;
        this.mathematics = mathematics;
        this.portuguese = portuguese;
        this.tuition = tuition;
    }
    

    public Double getRankingStudent() {
        return (this.english.getScores() + this.geography.getScores() + this.history.getScores() + this.mathematics.getScores() + this.portuguese.getScores()) / 5;    
    }

    public Role getRole() {
        return Role.STUDENT;
    }
    
    public void createId() {
    	this.id = GenerateId.id++;
    }
}