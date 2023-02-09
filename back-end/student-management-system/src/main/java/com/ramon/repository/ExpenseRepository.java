package com.ramon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ramon.model.ExpenseModel;

public interface ExpenseRepository extends JpaRepository<ExpenseModel, Long>{
    
}
