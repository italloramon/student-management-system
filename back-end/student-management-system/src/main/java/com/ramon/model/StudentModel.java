package com.ramon.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "student")
@NoArgsConstructor
@ToString @EqualsAndHashCode
public class StudentModel {
    
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
    @Getter @Setter private ResponsableModel responsable;

    public StudentModel(String name, String cpf, String email, ResponsableModel responsable) {
        this.id = GenerateId.id++;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.responsable = responsable;
    }

}
