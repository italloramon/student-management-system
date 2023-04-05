package com.ramon.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.GeneratedValue;

@Entity
@Table(name = "notices")
@ToString @EqualsAndHashCode
@NoArgsConstructor
public class Notice {
    
    @Id
    @GeneratedValue
    @Column(name = "notice_id")
    @Getter private Long id;

    @Column(name = "notice_text")
    @Getter @Setter private String text;
    
}
