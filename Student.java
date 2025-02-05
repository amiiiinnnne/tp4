
package model;

public class Student {
    private String name;
    private Integer age;
    private Integer id;

    public Student(int id, String name, int age) {
        this.name = name;
        this.age = age;
        this.id = id;

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

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student : [ id : "+id+",  name : " + this.name + " , age : " + age + " ]";
    }
}
