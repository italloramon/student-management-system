package com.ramon.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "responsable_table")
@NoArgsConstructor
@EqualsAndHashCode
public class Responsable implements User {
    @Id
    @GeneratedValue
    @Column(name = "responsable_id")
    @Getter private Long id;

    @Column(name = "responsable_name")
    @Getter @Setter private String nameResponsable;

    @Column(name = "reponsable_cpf")
    @Getter @Setter private String cpfResponsable;

    @Column(name = "responsable_email")
    @Getter @Setter private String emailResponsable;

    @OneToMany(mappedBy="responsable", cascade=CascadeType.ALL)
    @JsonIgnore @Getter private List<Student> students;

    public Responsable(String nameResponsable, String cpfResponsable, String emailResponsable) {
        this.nameResponsable = nameResponsable;
        this.cpfResponsable = cpfResponsable;
        this.emailResponsable = emailResponsable;
    }

    public Role getRole() {
        return Role.RESPONSABLE;
    }

    @Override
    public String getUsername() {
        return emailResponsable;
    }

    @Override
    public String getPassword() {
        return cpfResponsable;
    }
}
