package view;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;


public class ViewStudent extends JFrame{

    public JTable StudentTable;
    public JLabel lblName, lblAge;
    public JTextField txtNom, txtAge;
    public JButton btnAdd, btnEdit, btnDelete, btnNew;
    public DefaultTableModel tableModel;
    


    public ViewStudent(){
        
    Color primaryColor=Color.decode("#0078D7");
    Color secondaryColor=Color.decode("#FFFFFF");
    Color backgroundColor=Color.decode("#F3F3F3");
    Color tableRowColor=Color.decode("#E8F4FF");
   

        setTitle("Gestion des étudiants");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(backgroundColor);
        JLabel title=new JLabel("Gestion des étudiants", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(primaryColor);
        add(title, BorderLayout.NORTH);
         tableModel=new DefaultTableModel(new Object[]{"ID","Nom","Age"}, 0);
        StudentTable=new JTable(tableModel);
        StudentTable.setRowHeight(30);
        StudentTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        StudentTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
        StudentTable.getTableHeader().setBackground(primaryColor);
        StudentTable.getTableHeader().setForeground(secondaryColor);
        StudentTable.setSelectionBackground(tableRowColor);
        JScrollPane scrollPane=new JScrollPane(StudentTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);
        JButton btnNew = createModernButton("Nouveau", primaryColor, secondaryColor);
        JPanel panelAction=new JPanel();
        panelAction.setLayout(new FlowLayout(FlowLayout.CENTER,15,15));
        panelAction.setBackground(backgroundColor);
         JButton btnDelete = createModernButton("Supprimer", Color.decode("#F39C12"), secondaryColor);
        panelAction.add(btnDelete);

        JButton btnEdit = createModernButton("Modifier", Color.decode("#E74C3C"), secondaryColor);
        panelAction.add(btnEdit);
        btnNew = createModernButton("Nouveau", primaryColor, secondaryColor);
        panelAction.add(btnNew);
        add(panelAction, BorderLayout.SOUTH);
        btnNew.setEnabled(false);
        btnEdit.setEnabled(false);
        btnDelete.setEnabled(false);



        JPanel panelForm=new JPanel();
        panelForm.setLayout(new GridBagLayout());
        panelForm.setBackground(secondaryColor);
        panelForm.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10, 10, 10, 10);
        gbc.fill=GridBagConstraints.HORIZONTAL;
        lblName=new JLabel("Nom");
        lblName.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx=0;
        gbc.gridy=0;
        panelForm.add(lblName, gbc);
        txtNom=new JTextField();
        txtNom.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.gridwidth=2;
        panelForm.add(txtNom, gbc);
        lblAge=new JLabel("Age");
        lblAge.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx=0;
        gbc.gridy=2;
        panelForm.add(lblAge, gbc);
        txtAge=new JTextField();
        txtAge.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx=0;
        gbc.gridy=3;
        gbc.gridwidth=2;
        panelForm.add(txtAge, gbc);
        btnAdd = createModernButton("Ajouter", Color.decode("#2ECC71"), secondaryColor);
        gbc.gridx=0;
        gbc.gridy=4;
        gbc.gridwidth=2;
        gbc.fill=GridBagConstraints.CENTER;
        panelForm.add(btnAdd, gbc);
        add(panelForm, BorderLayout.WEST);

    }
    private JButton createModernButton(String text , Color bgColor, Color fgColor){
        JButton btn=new JButton(text);
        btn.setBackground(bgColor);
        btn.setForeground(fgColor);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setOpaque(true);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return btn;
    }
    public JTextField getTxtNom(){
        return  txtNom;
    }

    public JTextField getTxtAge(){
        return txtAge;
    }
    public JButton getBtnAdd(){
        return btnAdd;
    }
    public JButton getBtnEdit(){
        return btnEdit;
    }
    public JButton getBtnDelete(){
        return btnDelete;
    }
    public JButton getBtnNew(){
        return btnNew;
    }
    public DefaultTableModel getTableModel(){
        return tableModel;
    }

    public JTable getStudentTable(){
        return StudentTable;
    }

}