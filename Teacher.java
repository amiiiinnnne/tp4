package model;

public class Teacher {
    private String name;
    private String subject;
    private int id;

    public Teacher(int id, String name, String subject) { 
        this.id = id;
        this.name = name;
        this.subject = subject;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Teacher : [ id :"+ id +" , name : " + name + " , Subject : " + subject + " ]";
    }
}
