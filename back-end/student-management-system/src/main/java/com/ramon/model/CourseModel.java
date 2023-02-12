package com.ramon.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Entity
@NoArgsConstructor @AllArgsConstructor
@ToString @EqualsAndHashCode
public class CourseModel {
    
    //@Id
    //@Column(name = "course_id")
    @Getter @Setter private Long id;

    //@Column(name = "course_name")
    @Getter @Setter private String name;

    //@Column(name = "course_score1")
    @Getter @Setter private Double score1;
    
    //@Column(name = "course_score2")
    @Getter @Setter private Double score2;
    
    //@Column(name = "course_score3")
    @Getter @Setter private Double score3;
    
    //@Column(name = "course_score4")
    @Getter @Setter private Double score4;
}
