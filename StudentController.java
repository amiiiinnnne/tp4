package controller;


import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.StudentDAO;
import model.Student;
import view.ViewStudent;

public class StudentController {
    private final StudentDAO doa;
    private final ViewStudent view;

    public StudentController(ViewStudent view,StudentDAO doa) {
        this.doa=doa;
        this.view = view;
        loadStudents();
    }
    private void loadStudents(){
        try {
            ArrayList<Student> students=doa.findAll();
            DefaultTableModel model=view.getTableModel();
            model.setRowCount(0);
            for (Student student : students) {
                String [] s= {Integer.toString(student.getId()),student.getName(),Integer.toString(student.getAge())};
                model.addRow(s);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(view,"Erreur "+ e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    
  
}