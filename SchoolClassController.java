package controller;


import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.*;
import model.SchoolClass;
import view.ViewSchoolClass;

public class SchoolClassController {
    private final ClassesDAO doa;
    private final ViewSchoolClass view;

    public SchoolClassController(ViewSchoolClass view,ClassesDAO doa) {
        this.doa=doa;
        this.view = view;
        loadStudents();
    }
    private void loadStudents(){
        try {
            ArrayList<SchoolClass> classes=doa.findAll();
            DefaultTableModel model=view.getTableModel();
            model.setRowCount(0);
            for (SchoolClass class1 : classes) {
                String [] s= {Integer.toString(class1.getId()),class1.getClassName(),class1.getTeacher().getName()};
                model.addRow(s);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(view,"Erreur "+ e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    
  
}