package model;

import java.util.ArrayList;

public class SchoolClass {
    private String className;
    private int id;

    private Teacher teacher;
    private ArrayList<Student> students;

    public SchoolClass(int id, String className, Teacher teacher) {
        this.id = id;
        this.className = className;
        this.teacher = teacher;
        this.students = new ArrayList<Student>();
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void addStudents(Student student) {
        this.students.add(student);
    }

    public Teacher getTeacher() {
        return this.teacher;
    }
    public void setTeacher(Teacher teacher) {
         this.teacher=teacher;
    }

    public ArrayList<Student> getStudents() {
        return this.students;
    }

    @Override
    public String toString() {
        return "School Class : [ id : "+this.id+",  name : " + this.className + " , teacher : " + this.teacher + " , Nombre of Students: "
                + this.students.size() + " ]";
    }
}
