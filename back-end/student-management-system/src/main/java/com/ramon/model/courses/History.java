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
@Table(name = "history_table")
@NoArgsConstructor
@EqualsAndHashCode
public class History {
    
    @Id
    @GeneratedValue
    @Column(name = "history_id")
    @Getter @Setter private Long id;
    
    @Column(name = "history_score1")
    @Getter @Setter private Double score1 = 0.0;

    @Column(name = "hisotry_score2")
    @Getter @Setter private Double score2 = 0.0;

    @Column(name = "history_score3")
    @Getter @Setter private Double score3 = 0.0;

    @Column(name = "history_score4")
    @Getter @Setter private Double score4 = 0.0;

    @OneToMany(mappedBy = "history")
    private List<StudentModel> students;

    public History(Double score1, Double score2, Double score3, Double score4) {
        this.score1 = score1;
        this.score2 = score2;
        this.score3 = score3;
        this.score4 = score4;
    }

    public Double getScores() {
        return ((this.score1 + this.score2 + this.score3 + this.score4) / 4);
    }

}