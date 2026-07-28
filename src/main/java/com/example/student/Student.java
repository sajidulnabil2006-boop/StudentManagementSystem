package com.example.student;

public class Student {
    private String name;
    private String id;
    private String department;
    private double cgpa;
    private String email;

    // 1. Constructor: To create a student object easily
    public Student(String name, String id, String department,double cgpa) {
        this.name = name;
        this.id = id;
        this.department = department;
        this.email = email;
        this.cgpa = cgpa;
    }

    // 2. Getters: TableView NEEDS these to "see" the data
    public String getName() {
        return name;
    }
    public String getId() {

        return id;
    }
    public String getDepartment() {
        return department;
    }
    public double getCgpa() {
        return cgpa;
    }
}
