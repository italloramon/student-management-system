package com.ramon.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "teacher")
@NoArgsConstructor
@ToString @EqualsAndHashCode
public class TeacherModel {
    @Id
    @GeneratedValue
    @Column(name = "teacher_id")
    @Getter private Long id;

    @Column(name = "teacher_name")
    @Getter @Setter private String name;

    @Column(name = "teacher_cpf")
    @Getter @Setter private String cpf;

    @Column(name = "teacher_email")
    @Getter @Setter private String email;

    @Column(name = "teacher_salary")
    @Getter @Setter private Double salary;

    @Column(name = "teacher_role")
    @Getter @Setter private Role role;

    public TeacherModel(String name, String cpf, String email, Double salary, String type) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.salary = salary;
        if(type == "English") {
            this.role = Role.TEACHERENGLISH;

        } else if(type == "Geography") {
            this.role = Role.TEACHERGEOGRAPHY;
        } else if(type == "History") {
            this.role = Role.TEACHERHISTORY;
        } else if(type == "Mathematics") {
            this.role = Role.TEACHERHISTORY;
        } else if(type == "Portuguese") {
            this.role = Role.TEACHERPORTUGUESE;
        } else if (type == "Enlgish") {
            this.role = Role.TEACHERENGLISH;
        }
    }


}
