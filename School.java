package model;

import java.util.List;

import dao.*;

public class School {
    private StudentDAO students;
    private TeacherDAO teachers;
    private ClassesDAO classes;

    public School() {
        this.students = new StudentDAO();
        this.teachers = new TeacherDAO();
        this.classes = new ClassesDAO();
    }

    public List<Student> getStudents() {
        return students.findAll();
    }

    public List<Teacher> getTeachers() {
        return this.teachers.findAll();
    }

    public List<SchoolClass> getSchoolClasses() {
        return this.classes.findAll();
    }

   
}
