package com.ramon.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ramon.repository.ExpenseRepository;
import com.ramon.repository.TeacherRepository;
import java.util.List;
import java.util.*;
import com.ramon.model.ExpenseModel;
import com.ramon.model.TeacherModel;
import com.ramon.exception.*;

@RestController
@RequestMapping("/api/")
public class ExpenseController {
    private final ExpenseRepository expenseRepository;
    private final TeacherRepository teacherRepository;

    public ExpenseController(ExpenseRepository expenseRepository, TeacherRepository teacherRepository) {
        this.expenseRepository = expenseRepository;
        this.teacherRepository = teacherRepository;
    }

    @GetMapping("expenses/")
    public List<ExpenseModel> getAllExpenses() {
        return this.expenseRepository.findAll();
    }

    @GetMapping("expenses/{id}")
    public ExpenseModel getExpense(@PathVariable Long id) {
        return this.expenseRepository.findById(id).orElseThrow(() -> new ExpenseNotFoundException(id));
    }

    @PostMapping("expenses/")
    public ExpenseModel createExpense(@RequestBody ExpenseModel expense) {
        return this.expenseRepository.save(expense);
    }

    @PutMapping("expenses/{id}")
    public ExpenseModel updateExpense(@RequestBody ExpenseModel newExpense, @PathVariable Long id) {
        return this.expenseRepository.findById(id).map(expense -> {
            expense.setName(newExpense.getName());
            expense.setCategory(newExpense.getCategory());
            expense.setPrice(newExpense.getPrice());
            return this.expenseRepository.save(expense);
        }).orElseThrow(() -> new ExpenseNotFoundException(id));
    }

    @DeleteMapping("expenses/{id}")
    public void deleteExpense(@PathVariable Long id) {
        this.expenseRepository.deleteById(id);
    }

    @GetMapping("expenses/financial")
    public Map<Integer, Object> getPayroll(){
        List<TeacherModel> teachers = teacherRepository.findAll();
        Map<Integer, Object> map = new HashMap<>();
        for (int i = 1; i <= teachers.size(); i++) {
            map.put(i, teachers.get(i-1));
        }
        return map;
    }

}
