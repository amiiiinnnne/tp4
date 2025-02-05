package dao;

import java.sql.*;
import java.util.ArrayList;

import database.DatabaseManager;
import model.Teacher;

public class TeacherDAO implements GeDAO<Teacher, Integer> {
    @Override
    public void save(Teacher teacher) {
        String sql = "INSERT INTO Teachers (name, subject) VALUES (?, ?)";
        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, teacher.getName());
            pstmt.setString(2, teacher.getSubject());
            pstmt.executeUpdate();
            conn.close();  
            System.out.println("l'enseignant est ajouté avec succès.");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de l'enseignant : " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Teacher> findAll() {
        String sql = "SELECT * FROM Teachers";
        ArrayList<Teacher> teachers = new ArrayList<>();
        try (Connection conn = DatabaseManager.connect();
             Statement stmt = conn.createStatement();
             ResultSet rows = stmt.executeQuery(sql)) {
            while (rows.next()) {
                Teacher teacher = new Teacher(
                        rows.getInt("id"),
                        rows.getString("name"),
                        rows.getString("subject")
                );
                teachers.add(teacher);
            }conn.close();  
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des enseignants : " + e.getMessage());
        }
        return teachers;
    }

    @Override
    public Teacher findById(Integer id) {
        Teacher teacher = null;
        String sql = "SELECT * FROM Teachers WHERE id = ?";
        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    teacher = new Teacher(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("subject")
                    );
                }
            }conn.close();  

        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche  : " + e.getMessage());
        }
        return teacher;
    }

    @Override
    public void update(Teacher teacher) {
        String sql = "UPDATE teachers SET name = ?, subject = ? WHERE id = ?";
        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, teacher.getName());
            pstmt.setString(2, teacher.getSubject());
            pstmt.setInt(3, teacher.getId());
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("enseignant modifié avec succès.");
            } else {
                System.out.println("  aucune enseignant á été modifié.");
            }conn.close();  
        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification de l'enseignant : " + e.getMessage());
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM Teachers WHERE id = ?";
        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("enseignant supprimé avec succès.");
            } else {
                System.out.println("aucun enseignement á été supprimé.");
            }conn.close();  
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de l'enseignant : " + e.getMessage());
        }
    }
}
