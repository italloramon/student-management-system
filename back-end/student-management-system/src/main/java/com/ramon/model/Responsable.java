package com.ramon.model;

import com.ramon.state.State;
import com.ramon.state.WithoutStudents;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @Column(name = "with_students")
    @Getter @Setter
    private Boolean withStudents = false;

    @Transient
    @Getter @Setter
    private State state = new WithoutStudents();
    public void nextState() {
        state.next(this);
    }
    public String printStatus() {
        return state.printStatus();
    }
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
