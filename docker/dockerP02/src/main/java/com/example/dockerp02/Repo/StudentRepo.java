package com.example.dockerp02.Repo;

import com.example.dockerp02.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student,Long> {
}
