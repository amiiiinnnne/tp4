import controller.SchoolClassController;
import controller.StudentController;
import controller.TeacherController;
import dao.*;
import view.*;
import javax.swing.*;
import java.awt.*;

public class Main {
    private static CardLayout cardLayout;
    private static JPanel contentPanel;

    private static class Cloud {
        int x, y;
        int speed;
        int width;
        
        Cloud(int x, int y, int speed, int width) {
            this.x = x;
            this.y = y;
            this.speed = speed;
            this.width = width;
        }
        
        void move() {
            x += speed;
            if (x > 400) {
                x = -width;
            }
        }
    }

    private static void showSplashScreen() {
        JWindow splashScreen = new JWindow();
        
        // Create clouds
        Cloud[] clouds = {
            new Cloud(-50, 20, 1, 80),
            new Cloud(150, 40, 1, 80),
            new Cloud(300, 30, 1, 80)
        };
        
        // Create flag animation variables
        final int[] flagWave = {0};
        
        // Create splash panel with animations
        JPanel splashPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                
                // Draw gradient background
                GradientPaint gradient = new GradientPaint(0, 0, new Color(135, 206, 235),
                        getWidth(), getHeight(), new Color(65, 105, 225));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                
                // Draw clouds
                g2d.setColor(Color.WHITE);
                for (Cloud cloud : clouds) {
                    // Enable antialiasing for smoother clouds
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    
                    // Create more circular segments for each cloud
                    g2d.fillOval(cloud.x, cloud.y + 10, 30, 30);
                    g2d.fillOval(cloud.x + 15, cloud.y + 5, 35, 35);
                    g2d.fillOval(cloud.x + 35, cloud.y + 10, 30, 30);
                    g2d.fillOval(cloud.x + 20, cloud.y + 15, 40, 25);
                    
                    // Add slight shadow/depth
                    g2d.setColor(new Color(245, 245, 245));
                    g2d.fillOval(cloud.x + 10, cloud.y + 20, 35, 20);
                    g2d.setColor(Color.WHITE);
                }
                
                // Draw ground
                g2d.setColor(new Color(34, 139, 34)); // Forest green
                g2d.fillRect(0, 200, getWidth(), 100);
                
                // Draw house body
                g2d.setColor(new Color(255, 239, 213)); // Papaya whip (cream color)
                g2d.fillRect(150, 120, 100, 80); // House body
                
                // Draw roof
                g2d.setColor(Color.RED);
                int[] xPoints = {140, 200, 260};
                int[] yPoints = {120, 80, 120};
                g2d.fillPolygon(xPoints, yPoints, 3);
                
                // Draw door
                g2d.setColor(new Color(139, 69, 19)); // Brown
                g2d.fillRect(185, 160, 30, 40);
                
                // Draw windows
                g2d.setColor(Color.WHITE);
                g2d.fillRect(160, 140, 20, 20); // Left window
                g2d.fillRect(220, 140, 20, 20); // Right window
                
                // Draw flag pole
                g2d.setColor(Color.BLACK);
                g2d.fillRect(260, 70, 2, 50);
                
                // Draw animated red flag
                g2d.setColor(Color.RED);
                int[] flagX = {262, 282, 282, 262};
                int[] flagY = {
                    75 + (int)(Math.sin(flagWave[0] * 0.1) * 2),
                    75 + (int)(Math.sin(flagWave[0] * 0.1 + 1) * 2),
                    85 + (int)(Math.sin(flagWave[0] * 0.1 + 1) * 2),
                    85 + (int)(Math.sin(flagWave[0] * 0.1) * 2)
                };
                g2d.fillPolygon(flagX, flagY, 4);
            }
        };
        splashPanel.setLayout(new BorderLayout());
        
        // Add title
        JLabel titleLabel = new JLabel("School Management System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        
        // Add loading text
        JLabel loadingLabel = new JLabel("Loading...", SwingConstants.CENTER);
        loadingLabel.setForeground(Color.WHITE);
        loadingLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        splashPanel.add(titleLabel, BorderLayout.NORTH);
        splashPanel.add(loadingLabel, BorderLayout.SOUTH);
        
        splashScreen.setContentPane(splashPanel);
        splashScreen.setSize(400, 300);
        splashScreen.setLocationRelativeTo(null);
        
        // Create animation timer
        Timer timer = new Timer(50, e -> {
            // Move clouds
            for (Cloud cloud : clouds) {
                cloud.move();
            }
            // Update flag wave
            flagWave[0]++;
            splashPanel.repaint();
        });
        
        timer.start();
        splashScreen.setVisible(true);
        
        // Show splash screen for 5 seconds
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        timer.stop();
        splashScreen.dispose();
    }

    public static void main(String[] args) {
        // Show splash screen
        showSplashScreen();
        
        // Create main frame
        JFrame mainFrame = new JFrame("School Management System");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);
        mainFrame.setLocationRelativeTo(null);
        
        // Create menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu managementMenu = new JMenu("Management");
        
        // Create Quit button
        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> System.exit(0));
        
        // Create menu items
        JMenuItem studentItem = new JMenuItem("Student Management");
        JMenuItem teacherItem = new JMenuItem("Teacher Management");
        JMenuItem classItem = new JMenuItem("Class Management");
        
        // Add menu items to menu
        managementMenu.add(studentItem);
        managementMenu.add(teacherItem);
        managementMenu.add(classItem);
        
        // Add menu and quit button to menu bar
        menuBar.add(managementMenu);
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(quitButton);
        
        // Create card layout and content panel
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        
        // Initialize views and controllers
        ViewStudent viewStudent = new ViewStudent();
        StudentDAO studentDao = new StudentDAO();
        new StudentController(viewStudent, studentDao);
        JPanel studentPanel = new JPanel();
        studentPanel.add(viewStudent.getContentPane());
        
        ViewTeacher viewTeacher = new ViewTeacher();
        TeacherDAO teacherDao = new TeacherDAO();
        new TeacherController(viewTeacher, teacherDao);
        JPanel teacherPanel = new JPanel();
        teacherPanel.add(viewTeacher.getContentPane());
        
        ViewSchoolClass viewSchoolClass = new ViewSchoolClass();
        ClassesDAO classesDao = new ClassesDAO();
        new SchoolClassController(viewSchoolClass, classesDao);
        JPanel classPanel = new JPanel();
        classPanel.add(viewSchoolClass.getContentPane());
        
        // Add all panels to content panel with names
        contentPanel.add(studentPanel, "STUDENT");
        contentPanel.add(teacherPanel, "TEACHER");
        contentPanel.add(classPanel, "CLASS");
        
        // Add action listeners
        studentItem.addActionListener(e -> cardLayout.show(contentPanel, "STUDENT"));
        teacherItem.addActionListener(e -> cardLayout.show(contentPanel, "TEACHER"));
        classItem.addActionListener(e -> cardLayout.show(contentPanel, "CLASS"));
        
        // Add components to frame
        mainFrame.setJMenuBar(menuBar);
        mainFrame.add(contentPanel);
        
        // Show frame
        mainFrame.setVisible(true);
        
        // Show student panel initially
        cardLayout.show(contentPanel, "STUDENT");
    }
}