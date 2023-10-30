package Objects;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Student extends JFrame implements ActionListener {

    private JLabel titleLabel;
    private JLabel usernameLabel;
    private JLabel usernameUnderscore;
    private JLabel passwordLabel;
    private JLabel passwordUnderscore;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    // Define placeholders
    private String usernamePlaceholder = "Enter your username";
    private String passwordPlaceholder = "Enter your password";

    static final String DB_URL = "jdbc:mysql://localhost:3306/biometrics_db";
    static final String USER = "root";
    static final String PASS = "";

    public Student() {
        try {
            // Set look and feel to system's default
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Student Login Page");
        setSize(800, 600); // Adjust the frame size as needed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Set background image
        ImageIcon backgroundImageIcon = new ImageIcon("images/background5.png"); // Replace with your image path
        Image backgroundImage = backgroundImageIcon.getImage();
        // Calculate the scaled dimensions
        int newWidth = getWidth();
        int newHeight = getHeight();
        // Scale the background image
        Image scaledImage = backgroundImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        // Create a new ImageIcon from the scaled image
        ImageIcon scaledBackgroundImageIcon = new ImageIcon(scaledImage);
        // Create a JLabel with the scaled background image
        JLabel backgroundLabel = new JLabel(scaledBackgroundImageIcon);
        backgroundLabel.setBounds(0, 0, newWidth, newHeight);
        add(backgroundLabel);


        // Create and configure components
        titleLabel = new JLabel("Student Login Page");
        titleLabel.setForeground(Color.BLACK); // Black color
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
        titleLabel.setBounds(250, 50, 320, 40);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Create new UI components similar to the design you provided
        usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.BLACK);
        usernameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        usernameLabel.setBounds(200, 150, 100, 30);

        usernameUnderscore = new JLabel("____________________________________");
        usernameUnderscore.setForeground(Color.BLACK);
        usernameUnderscore.setBounds(420, 155, 300, 30);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.BLACK);
        passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        passwordLabel.setBounds(200, 250, 100, 30);

        usernameField = new JTextField(usernamePlaceholder);
        usernameField.setBounds(420, 150, 200, 30);
        usernameField.setBorder(null);
        usernameField.setOpaque(false);

        // Set the placeholder font
        Font placeholderFont = new Font("Segoe UI", Font.PLAIN, 20);
        usernameField.setFont(placeholderFont);
        usernameField.setForeground(Color.GRAY); // Set the initial text color to gray

        // Add FocusListener to handle the placeholder
        usernameField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (usernameField.getText().equals(usernamePlaceholder)) {
                    usernameField.setText("");
                    usernameField.setForeground(Color.BLACK); // Change text color when focused
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (usernameField.getText().isEmpty()) {
                    usernameField.setText(usernamePlaceholder);
                    usernameField.setForeground(Color.GRAY); // Change text color when not focused
                }
            }
        });

        passwordField = new JPasswordField(passwordPlaceholder);
        passwordField.setBounds(420, 250, 200, 30);
        passwordField.setBorder(null);
        passwordField.setOpaque(false);
        Font passwordPlaceholderFont = new Font("Segoe UI", Font.PLAIN, 20);
        passwordField.setFont(passwordPlaceholderFont);
        passwordField.setForeground(Color.GRAY);
        passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (new String(passwordField.getPassword()).equals(passwordPlaceholder)) {
                    passwordField.setText("");
                    passwordField.setEchoChar('*'); // Replace '*' with the character you want for password masking
                    passwordField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (new String(passwordField.getPassword()).isEmpty()) {
                    passwordField.setText(passwordPlaceholder);
                    passwordField.setEchoChar((char) 0);
                    passwordField.setForeground(Color.GRAY);
                }
            }
        });


        passwordUnderscore = new JLabel("____________________________________");
        passwordUnderscore.setForeground(Color.BLACK);
        passwordUnderscore.setBounds(420, 255, 300, 30);

        loginButton = new JButton("Login");
        loginButton.setBounds(350, 350, 100, 40);


        // Add new components to the frame
        backgroundLabel.add(titleLabel);
        backgroundLabel.add(usernameLabel);
        backgroundLabel.add(passwordLabel);
        backgroundLabel.add(usernameField);
        backgroundLabel.add(passwordField);
        backgroundLabel.add(loginButton);
        backgroundLabel.add(usernameUnderscore);
        backgroundLabel.add(passwordUnderscore);


        // Configure the new login button
        loginButton.setBackground(Color.BLACK);
        loginButton.setForeground(Color.BLACK); // Set text color to white
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 20)); // Increase font size
        loginButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));

// Add ActionListener to the login button
        loginButton.addActionListener(this);

// Add MouseAdapter for hover effect
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                loginButton.setBorderPainted(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                loginButton.setBorderPainted(false);
            }
        });


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> {
            Student student = new Student();
            student.setVisible(true);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            try {
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement stmt = conn.prepareStatement("SELECT password FROM student WHERE studentId = ?");
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String storedPassword = rs.getString("password");

                    if (password.equals(storedPassword)) {
                        JOptionPane.showMessageDialog(this, "Login Successful");

                        // Open the new page
                        StudentH studentH = new StudentH();
                        studentH.stView(Integer.parseInt(username));
                        dispose(); // Close the current login page
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid username or password");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid username or password");
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error connecting to the database");
            }
        }
    }
}
