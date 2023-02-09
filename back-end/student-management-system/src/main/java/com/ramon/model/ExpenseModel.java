package com.ramon.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "expense")
@NoArgsConstructor
@ToString @EqualsAndHashCode
public class ExpenseModel {
    
    @Id
    @GeneratedValue
    @Column(name = "expense_id")
    @Getter private Long id;

    @Column(name = "expense_name")
    @Getter @Setter private String name;

    @Column(name = "expense_category")
    @Getter @Setter private String category;

    @Column(name = "expense_price")
    @Getter @Setter private Double price;

    public ExpenseModel(String name, String category, Double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }
}