package com.ramon.model.courses;

import com.ramon.model.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.*;

@Entity
@Table(name = "english_table")
@NoArgsConstructor
@EqualsAndHashCode
public class English {
    
    @Id
    @GeneratedValue
    @Column(name = "english_id")
    @Getter @Setter private Long id;
    
    @Column(name = "english_score1")
    @Getter @Setter private Double score1;

    @Column(name = "english_score2")
    @Getter @Setter private Double score2;

    @Column(name = "english_score3")
    @Getter @Setter private Double score3;

    @Column(name = "english_score4")
    @Getter @Setter private Double score4;

    @OneToMany(mappedBy = "english")
    private List<StudentModel> students;

    public English(Double score1, Double score2, Double score3, Double score4) {
        this.score1 = score1;
        this.score2 = score2;
        this.score3 = score3;
        this.score4 = score4;
    }

}

