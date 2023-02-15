package com.ramon.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.*;
import com.ramon.model.*;
import com.ramon.repository.*;
import com.ramon.exception.*;

@RestController
@RequestMapping("/api/")
public class ExpenseController {
    private final ExpenseRepository expenseRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    public ExpenseController(ExpenseRepository expenseRepository, TeacherRepository teacherRepository, StudentRepository studentRepository) {
        this.expenseRepository = expenseRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
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
        ExpenseModel expense = expenseRepository.findById(id).orElseThrow(() -> new ExpenseNotFoundException(id));
        if (newExpense.getName() != null) {
            expense.setName(newExpense.getName());
        }
        if (newExpense.getCategory() != null) {
            expense.setCategory(newExpense.getCategory());
        }
        if (newExpense.getPrice() != null) {
            expense.setPrice(newExpense.getPrice());
        }
        
        return expenseRepository.save(expense);
    }

    @DeleteMapping("expenses/{id}")
    public void deleteExpense(@PathVariable Long id) {
        this.expenseRepository.deleteById(id);
    }

    // @GetMapping("expenses/financial")
    // public Map<Integer, Object> getPayroll(){
    //     List<TeacherModel> teachers = teacherRepository.findAll();
    //     Map<Integer, Object> map = new HashMap<>();
    //     for (int i = 1; i <= teachers.size(); i++) {
    //         map.put(i, teachers.get(i-1));
    //     }
    //     return map;
    // }

    @GetMapping("expenses/dashboard")
    public Map<String, Double> getDashboard() {
        Map<String, Double> dashboard = new HashMap<>();
        List<StudentModel> students = studentRepository.findAll();
        List<TeacherModel> teachers = teacherRepository.findAll();
        List<ExpenseModel> expenses = expenseRepository.findAll();

        Double total = 0.0;
        for (StudentModel student: students) {
            dashboard.put(student.getName(), student.getTuition());
            total += student.getTuition();
        }
        for (TeacherModel teacher: teachers) {
            dashboard.put(teacher.getName(), teacher.getSalary());
            total -= teacher.getSalary();
        }
        for (ExpenseModel expense: expenses) {
            dashboard.put(expense.getName(), expense.getPrice());
            total -= expense.getPrice();
        }

        dashboard.put("Total: ", total);

        return dashboard;

    }

}
