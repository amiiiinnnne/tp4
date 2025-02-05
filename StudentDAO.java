package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.DatabaseManager;
import model.Student;

public class StudentDAO implements GeDAO<Student,Integer> {

    @Override
    public void save(Student student) {
       String sql="INSERT INTO students (name,age) values(?,?)";
       try {
        Connection connection=DatabaseManager.connect();
        PreparedStatement stmt=connection.prepareStatement(sql);
        stmt.setString(1, student.getName());
        stmt.setInt(2, student.getAge());
        stmt.executeUpdate();
        connection.close();
        System.out.println("étudiant est ajouté  !");
       } catch (SQLException e) {
        System.err.println("Erreur survenue lors de l'ajout de l'étudiant" + e.getMessage());
       }
        
    }

    @Override
    public Student findById(Integer id) {
        String sql="SELECT * FROM students where id =?";
        Student student =null;
        try {
            Connection conn=DatabaseManager.connect();
            PreparedStatement stmt=conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs=stmt.executeQuery();
            if (rs.next()) {
            student=new Student(rs.getInt("id"),
             rs.getString("name"), 
            rs.getInt("age"));
            
            }
            conn.close();
        } catch (SQLException e) {
            System.err.println("Erreur survenue lors de la récupération de l'étudiant" + e.getMessage());

        }
        return student;

    }

    @Override
    public ArrayList<Student> findAll() {
        String sql="SELECT * FROM students;";
        ArrayList<Student> students= new ArrayList<>();
        try {
            Connection conn=DatabaseManager.connect();
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            while (rs.next()) {
                Student student=new Student(rs.getInt("id"), rs.getString("name"), rs.getInt("age"));
                students.add(student);
            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Erreur survenue lors de la récupération des étudiants" + e.getMessage());

        }
        return students;
    }

    @Override
    public void update(Student student) {
        String sql ="UPDATE students set name=? , age = ? where id= ?";
        try {
            Connection conn=DatabaseManager.connect();
            PreparedStatement stmt=conn.prepareStatement(sql);
            stmt.setString(1, student.getName());
            stmt.setInt(2, student.getAge());
            stmt.setInt(3, student.getId());
            int rows=stmt.executeUpdate();
            if (rows > 0) {
            System.out.println("l'étudiant à été modifié" );
                 
            }else System.err.println("Erreur survenue lors de la modification" );
            conn.close();
           


            
        } catch (SQLException e) {
            System.err.println("Erreur survenue lors de la modification de l'étudiant" + e.getMessage());

        }

    }

    @Override
    public void delete(Integer id) {
        String sql="DELETE FROM students WHERE id=?";
        try {
            Connection conn=DatabaseManager.connect();
            PreparedStatement stmt=conn.prepareStatement(sql);
            stmt.setInt(1, id);
            int rows=stmt.executeUpdate();
            if (rows > 0) {
            System.out.println("l'étudiant à été supprimé" );
                 
            }else System.err.println("erreur lors de la suppression" );
            conn.close();
        } catch (Exception e) {
            System.err.println("Erreur lors de la supprission " + e.getMessage());

        }
    }
    
}
