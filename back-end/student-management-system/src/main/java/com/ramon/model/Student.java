package com.ramon.model;

import com.ramon.model.courses.English;
import com.ramon.model.courses.Geography;
import com.ramon.model.courses.History;
import com.ramon.model.courses.Mathematics;
import com.ramon.model.courses.Portuguese;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "student")
@NoArgsConstructor
@ToString @EqualsAndHashCode
public class Student implements User{
    
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

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "english_id")
    @Getter @Setter private English english;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "geography_id")
    @Getter @Setter private Geography geography;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "history_id")
    @Getter @Setter private History history;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "mathematics_id")
    @Getter @Setter private Mathematics mathematics;

    @OneToOne(cascade = CascadeType.PERSIST)
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

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return cpf;
    }
}
