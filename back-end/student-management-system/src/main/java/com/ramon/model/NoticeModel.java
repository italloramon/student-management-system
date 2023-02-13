package com.ramon.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.CollectionTable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ElementCollection;
import java.util.*;
import com.ramon.model.courses.*;

@Entity
@Table(name = "notices")
@ToString @EqualsAndHashCode
@NoArgsConstructor
public class NoticeModel {
    
    @Id
    @GeneratedValue
    @Column(name = "notice_id")
    @Getter private Long id;

    @Column(name = "notice_text")
    @Getter @Setter private String text;
    
}
