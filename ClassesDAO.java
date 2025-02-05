package dao;

import java.sql.*;
import java.util.ArrayList;

import database.DatabaseManager;
import model.SchoolClass;
import model.Teacher;

public class ClassesDAO implements GeDAO<SchoolClass, Integer> {
    @Override
    public void save(SchoolClass classes) {
        String sql = "INSERT INTO classes (name,teacher_id) VALUES (?,?)";
        try {
            Connection conn = DatabaseManager.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql) ;
            pstmt.setString(1, classes.getClassName());
            pstmt.setInt(2, classes.getTeacher().getId());

            pstmt.executeUpdate();
            System.out.println("la class est ajouté .");
            conn.close(); 
        } catch (SQLException e) {
            System.out.println("Erreur survenue lors de l'ajout de la classe : " + e.getMessage());
        }
    }

    @Override
    public ArrayList<SchoolClass> findAll() {
        
        String sql = "SELECT id,name,teacher_id FROM classes";
        String sql_teacher = "SELECT id,name,subject FROM teachers where id=?";

        ArrayList<SchoolClass> classes = new ArrayList<>();
        try {
            Connection conn = DatabaseManager.connect();
            Statement stmt = conn.createStatement();
            ResultSet rows = stmt.executeQuery(sql);

            while (rows.next()) {

                PreparedStatement teacher_stmt = conn.prepareStatement(sql_teacher);
                teacher_stmt.setInt(1, rows.getInt("teacher_id"));
                ResultSet rows_teacher = teacher_stmt.executeQuery();
                Teacher teacher_find =null;
                if (rows_teacher.next()) {
                 teacher_find = new Teacher(rows_teacher.getInt("id"), rows_teacher.getString("name"),rows_teacher.getString("subject"));
                  SchoolClass classe = new SchoolClass(
                            rows.getInt("id"),
                            rows.getString("name"),
                            teacher_find);
                    classes.add(classe);  
                }

                    

                


            }
            conn.close(); 
        } catch (SQLException e) {
            System.out.println("Erreur survenue lors de la récupération des classes : " + e.getMessage());
        }

        return classes;
    }

    @Override
    public SchoolClass findById(Integer id) {
        SchoolClass classes = null;
        String sql = "SELECT id,name,teacher_id FROM classes WHERE id = ?";
        String sql_teacher = "SELECT id, name, subject FROM teachers WHERE id = ?";
    
        try {
            Connection conn = DatabaseManager.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                PreparedStatement teacher_stmt = conn.prepareStatement(sql_teacher);
                teacher_stmt.setInt(1, rs.getInt("teacher_id"));
                ResultSet rows_teacher = teacher_stmt.executeQuery();
                
                if (rows_teacher.next()) {
                    Teacher teacher_find = new Teacher(
                        rows_teacher.getInt("id"),
                        rows_teacher.getString("name"),
                        rows_teacher.getString("subject")
                    );
                    
                    classes = new SchoolClass(
                        rs.getInt("id"),
                        rs.getString("name"),
                        teacher_find
                    );
                }
                rows_teacher.close();  
                teacher_stmt.close();  
            }
            
            rs.close();  
            pstmt.close();  
            
            conn.close(); 
    
        } catch (SQLException e) {
            System.out.println("Erreur survenue lors de la recherche de la classe scolaire : " + e.getMessage());
        }
        
        return classes;
    }
    

    @Override
    public void update(SchoolClass classe) {
        String sql = "UPDATE classes SET name = ?, teacher_id = ? WHERE id = ?";
        try {
            Connection conn = DatabaseManager.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, classe.getClassName());
            pstmt.setInt(2, classe.getTeacher().getId());
            pstmt.setInt(3, classe.getId());
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("la classe est modifié .");
            } else {
                System.out.println(" aucune classe à été modifié.");
            }
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Erreur survenue lors de la modification de la classe : " + e.getMessage());
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM classes WHERE id = ?";
        try (Connection conn = DatabaseManager.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("la classe à été supprimé .");
            } else {
                System.out.println(" aucune classe à été supprimé.");
            }
            conn.close();  
        } catch (SQLException e) {
            System.out.println("Erreur survenue lors de la suppression de la class : " + e.getMessage());
        }
    }
}
