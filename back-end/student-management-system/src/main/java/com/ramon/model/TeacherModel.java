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
public class TeacherModel extends ExpenseModel{
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

    public TeacherModel(String name, String cpf, String email, Double salary) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.salary = salary;
    }

}
