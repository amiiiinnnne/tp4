package controller;


import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.TeacherDAO;
import model.Teacher;
import view.ViewTeacher;

public class TeacherController {
    private final TeacherDAO doa;
    private final ViewTeacher view;

    public TeacherController(ViewTeacher view,TeacherDAO doa) {
        this.doa=doa;
        this.view = view;
        loadStudents();
    }
    private void loadStudents(){
        try {
            ArrayList<Teacher> teachers=doa.findAll();
            DefaultTableModel model=view.getTableModel();
            model.setRowCount(0);
            for (Teacher student : teachers) {
                String [] s= {Integer.toString(student.getId()),student.getName(),student.getSubject()};
                model.addRow(s);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(view,"Erreur "+ e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    
  
}