package view;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dao.TeacherDAO;
import model.Teacher;

import java.awt.*;

public class ViewSchoolClass extends JFrame{
    Color primaryColor=Color.decode("#0078D7");
    Color secondaryColor=Color.decode("#FFFFFF");
    Color backgroundColor=Color.decode("#F3F3F3");
    Color tableRowColor=Color.decode("#E8F4FF");
    
    public JTable SchoolClassTable;
    public JLabel lblNameTeacher, lblClassName;
    public DefaultTableModel SchoolClassTableModel;
    public JComboBox<String> teacherComboBox;
    
    public JPanel formJPanel, actioPanel;
    public JLabel nameJLabel, teacherNameJLabel;
    public JTextField nomField;
    public JButton btnaddSchoolClass, btnewSchoolClass, btneditSchoolClass, btnremoveSchoolClass;
    public ViewSchoolClass(){
        setTitle("Gestion des classes");
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout(20,20));
        getContentPane().setBackground(backgroundColor);
        JLabel title=new JLabel("Gestion des classes ",JLabel.CENTER);
        title.setFont(new Font("Segoe UI",Font.BOLD,24));
        title.setForeground(primaryColor);

        add(title,BorderLayout.NORTH);

        



        GridBagConstraints gbc=new GridBagConstraints();


        gbc.insets=new Insets(10, 10, 10, 10);
        gbc.fill=GridBagConstraints.HORIZONTAL;
        formJPanel=new JPanel();
        formJPanel.setLayout(new GridBagLayout());
        formJPanel.setBackground(secondaryColor);
        formJPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        nameJLabel=new JLabel("Name  :");
        nameJLabel.setFont(new Font("Segoe UI",Font.PLAIN,16));
        gbc.gridx=0;
        gbc.gridy=0;

        formJPanel.add(nameJLabel,gbc);

        nomField=new JTextField();
        nomField.setFont(new Font("Segoe UI",Font.PLAIN,14));

        gbc.gridx=0;
        gbc.gridy=1;
        gbc.gridwidth=2;
        formJPanel.add(nomField,gbc);

        teacherNameJLabel=new JLabel("Teacher Name :");
        teacherNameJLabel.setFont(new Font("Segoe UI",Font.PLAIN,14));
        gbc.gridx=0;
        gbc.gridy=2;
        formJPanel.add(teacherNameJLabel,gbc);
        

        TeacherDAO teacherDAO=new TeacherDAO();

        teacherComboBox=new JComboBox<String>(teacherDAO.findAll().stream().map(Teacher::getName).toArray(String[]::new));
     
        teacherComboBox.setFont(new Font("Segoe UI",Font.PLAIN,14));
        gbc.gridx=0;
        gbc.gridy=3;
        gbc.gridwidth=2;

        formJPanel.add(teacherComboBox,gbc);

        btnaddSchoolClass=createModerButton("Ajouter Classe",Color.decode("#2ECC71"),secondaryColor);
        gbc.gridx=0;
        gbc.gridy=4;
        gbc.gridwidth=2;
        gbc.fill=GridBagConstraints.CENTER;

        formJPanel.add(btnaddSchoolClass,gbc);
        add(formJPanel,BorderLayout.WEST);
        nameJLabel.setLabelFor(nomField);
        teacherNameJLabel.setLabelFor(teacherComboBox);
        SchoolClassTableModel=new DefaultTableModel(new Object[]{"ID", "Nom de la Classe" , "Nom de l'Enseignant" },0);
        SchoolClassTable=new JTable(SchoolClassTableModel);
        SchoolClassTable.setRowHeight(30);
        SchoolClassTable.setFont(new Font("Segoe UI",Font.PLAIN,14));
        SchoolClassTable.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,16));
        SchoolClassTable.getTableHeader().setBackground(primaryColor);

        SchoolClassTable.getTableHeader().setForeground(secondaryColor);
        SchoolClassTable.setSelectionBackground(tableRowColor);

        JScrollPane scrollPane=new JScrollPane(SchoolClassTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane,BorderLayout.CENTER);


        actioPanel=new JPanel();
        actioPanel.setLayout(new FlowLayout(FlowLayout.CENTER,15,10));
        actioPanel.setBackground(backgroundColor);

        btnewSchoolClass=createModerButton("Ajouter Nouveau",primaryColor,secondaryColor);
        btneditSchoolClass=createModerButton("Modifier Nouveau",Color.decode("#F39C12"),secondaryColor);
        btnremoveSchoolClass=createModerButton("Supprimer Nouveau",Color.decode("#E74C3C"),secondaryColor);
        actioPanel.add(btnewSchoolClass);
        actioPanel.add(btneditSchoolClass);
        actioPanel.add(btnremoveSchoolClass);
        add(actioPanel,BorderLayout.SOUTH);

        btnewSchoolClass.setEnabled(false);
        btneditSchoolClass.setEnabled(false);
        btnremoveSchoolClass.setEnabled(false);


 


    }
    private JButton createModerButton(String text,Color bgColor,Color fgColor){
        JButton button=new JButton(text);
        button.setFont(new Font("Segoe UI",Font.PLAIN,16));
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setOpaque(true);
        return button;

    }
    public JTextField getTxtName(){
        return nomField;
    }
    public JComboBox<String> getTeacherComboBox(){
        return teacherComboBox;
    }
    public JButton getBtnAdd(){
        return btnaddSchoolClass;
    }
    public JButton getBtnEdit(){
        return btneditSchoolClass;
    }
    public JButton getBtnDelete(){
        return btnremoveSchoolClass;
    }
    public JButton getBtnNew(){
        return btnewSchoolClass;
    }
    public DefaultTableModel getTableModel(){
        return SchoolClassTableModel;
    }
    public JTable getTableSchoolClasss(){
        return SchoolClassTable;
    }
 

}