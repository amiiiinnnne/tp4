package database;
import java.sql.*;
public class DatabaseManager {
    private static final String DATABASE_URL="jdbc:sqlite:school.db";

    public static void intializeDatabase(){

      try {
        Connection connection=connect();
        Statement stmt=connection.createStatement();
        String createStudentsTable="""
                CREATE TABLE IF NOT EXISTS students(
                id integer primary key autoincrement,
                name text not null,
                age integer not null
                ) ;
                """;
                stmt.execute(createStudentsTable); 
        String createTeacherTable="""
                create table if not exists teachers(
                id integer primary key autoincrement,
                name text not null,
                subject text not null
            );
                """;
                stmt.execute(createTeacherTable);
        String createClassesTable="""
                create table if not exists classes(
                id integer primary key autoincrement,
                name text not null,
                teacher_id integer not null,
                foreign key (teacher_id) references teachers(id)
                );
                """;
                stmt.execute(createClassesTable);

        String createStudentClassTable="""
                create table if not exists student_classes(
                student_id integer not null,
                class_id integer not null,
                primary key (student_id,class_id),
                foreign key (student_id) references students(id),
                foreign key (class_id) references classes(id)
                );
                """;
   
                stmt.execute(createStudentClassTable);
         System.out.println("Base de donnée initalisée ");

      } catch (SQLException e) {
        System.err.println("Erreur de la création de la base de donnée :"+ e.getMessage());

        }
    }
   
           
            public static Connection connect(){
        try  {
            return DriverManager.getConnection( DATABASE_URL);
            
        } catch (SQLException e) {
            System.out.println("Erreur de la connexion à la base de donneés :"+ e.getMessage());
            return null;
        }
    }
   
 
}
