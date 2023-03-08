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
public class TeacherModel extends User{
    @Id
    @GeneratedValue
    @Column(name = "teacher_id")
    @Getter private Long id;

    @Column(name = "teacher_salary")
    @Getter @Setter private Double salary;

    @Column(name = "teacher_role")
    @Getter @Setter private Role role;

    public TeacherModel(String name, String cpf, String email, Double salary, String type) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.salary = salary;
        if(type.equalsIgnoreCase("English")) {
            this.role = Role.TEACHERENGLISH;
        } else if(type.equalsIgnoreCase("Geography")) {
            this.role = Role.TEACHERGEOGRAPHY;
        } else if(type.equalsIgnoreCase("History")) {
            this.role = Role.TEACHERHISTORY;
        } else if(type.equalsIgnoreCase("Mathematics")) {
            this.role = Role.TEACHERMATHEMATICS;
        } else if(type.equalsIgnoreCase("Portuguese")) {
            this.role = Role.TEACHERPORTUGUESE;
        }
    }

}
