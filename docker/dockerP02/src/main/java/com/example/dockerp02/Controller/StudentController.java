package com.example.dockerp02.Controller;

import com.example.dockerp02.Entity.Student;
import com.example.dockerp02.Repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private final StudentRepo studentRepo;

    public StudentController(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @GetMapping("/students")
    public List<Student> GetAllStudent(){
        return studentRepo.findAll();
    }

    @GetMapping("/student/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentRepo.findById(id).get();
    }

    @PostMapping("/student/save")
    public Student createStudent(@RequestBody Student student) {
        return studentRepo.save(student);
    }
}
