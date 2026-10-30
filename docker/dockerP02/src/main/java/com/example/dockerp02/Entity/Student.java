package com.example.dockerp02.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String cousre;

    public Student() {
    }
    public Student(String name, String cousre) {
        this.name = name;
        this.cousre = cousre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCousre() {
        return cousre;
    }

    public void setCousre(String cousre) {
        this.cousre = cousre;
    }
}
