package com.ramon.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import java.util.List;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "responsable_table")
@NoArgsConstructor
@EqualsAndHashCode
public class ResponsableModel {
    @Id
    @GeneratedValue
    @Column(name = "responsable_id")
    @Getter private Long id;

    @Column(name = "responsable_name")
    @Getter @Setter private String name;

    @Column(name = "reponsable_cpf")
    @Getter @Setter private String cpf;

    @Column(name = "responsable_email")
    @Getter @Setter private String email;

    @OneToMany(mappedBy="responsable")
    private List<StudentModel> students;

    public ResponsableModel(String name, String cpf, String email) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
    }

}
