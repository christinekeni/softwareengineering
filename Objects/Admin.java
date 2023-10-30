package Objects;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Admin extends JFrame implements ActionListener {

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

    public Admin() {
        try {
            // Set look and feel to system's default
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Admin Login Page");
        setSize(800, 600); // Adjust the frame size as needed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Set background image
        ImageIcon backgroundImageIcon = new ImageIcon("images/background3.jpg"); // Replace with your image path
        Image backgroundImage = backgroundImageIcon.getImage();
        int newWidth = getWidth();
        int newHeight = getHeight();
        Image scaledImage = backgroundImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledBackgroundImageIcon = new ImageIcon(scaledImage);
        JLabel backgroundLabel = new JLabel(scaledBackgroundImageIcon);
        backgroundLabel.setBounds(0, 0, newWidth, newHeight);
        add(backgroundLabel);

        // Create and configure components
        titleLabel = new JLabel("Admin Login Page");
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
        titleLabel.setBounds(250, 50, 320, 40);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

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

        Font placeholderFont = new Font("Segoe UI", Font.PLAIN, 20);
        usernameField.setFont(placeholderFont);
        usernameField.setForeground(Color.GRAY);
        usernameField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (usernameField.getText().equals(usernamePlaceholder)) {
                    usernameField.setText("");
                    usernameField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (usernameField.getText().isEmpty()) {
                    usernameField.setText(usernamePlaceholder);
                    usernameField.setForeground(Color.GRAY);
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
        passwordField.setEchoChar('*');
        passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (new String(passwordField.getPassword()).equals(passwordPlaceholder)) {
                    passwordField.setText("");
                    passwordField.setEchoChar('*');
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

        backgroundLabel.add(titleLabel);
        backgroundLabel.add(usernameLabel);
        backgroundLabel.add(passwordLabel);
        backgroundLabel.add(usernameField);
        backgroundLabel.add(passwordField);
        backgroundLabel.add(loginButton);
        backgroundLabel.add(usernameUnderscore);
        backgroundLabel.add(passwordUnderscore);

        loginButton.setBackground(Color.BLACK);
        loginButton.setForeground(Color.BLACK);
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
        loginButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));

        loginButton.addActionListener(this);

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
            Admin admin = new Admin();
            admin.setVisible(true);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            try {
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement stmt = conn.prepareStatement("SELECT password FROM admin WHERE adminId = ?");
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String storedPassword = rs.getString("password");

                    if (password.equals(storedPassword)) {
                        JOptionPane.showMessageDialog(this, "Login Successful");
                        AdminH adminH = new AdminH();
                        adminH.homeView(Integer.parseInt(username));
                        dispose();
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
