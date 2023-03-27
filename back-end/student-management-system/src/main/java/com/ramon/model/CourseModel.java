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
public interface CourseModel {
    public String courseName();
    
    public Double score1();
    
    public Double score2();
    
    public Double score3();
    
    public Double score4();
    
}
