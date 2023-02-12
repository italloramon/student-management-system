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
@Table(name = "portuguese_table")
@NoArgsConstructor
@EqualsAndHashCode
public class Portuguese {
    
    @Id
    @GeneratedValue
    @Column(name = "portuguese_id")
    @Getter @Setter private Long id;
    
    @Column(name = "portuguese_score1")
    @Getter @Setter private Double score1 = 0.0;

    @Column(name = "portuguese_score2")
    @Getter @Setter private Double score2 = 0.0;

    @Column(name = "portuguese_score3")
    @Getter @Setter private Double score3 = 0.0;

    @Column(name = "portuguese_score4")
    @Getter @Setter private Double score4 = 0.0;

    @OneToMany(mappedBy = "portuguese")
    private List<StudentModel> students;

    public Portuguese(Double score1, Double score2, Double score3, Double score4) {
        this.score1 = score1;
        this.score2 = score2;
        this.score3 = score3;
        this.score4 = score4;
    }

}