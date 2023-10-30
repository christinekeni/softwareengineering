package Objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CheckAttendance {
    private Connection con;

    public void createLoginGUI() {
        connectToDatabase();

        JFrame frame = new JFrame();
        Font font = new Font("Arial", Font.BOLD, 18);

        // ------------------------CLOSE---------------------------
        JLabel x = new JLabel("X");
        x.setForeground(Color.decode("#37474F"));
        x.setBounds(280, 10, 20, 20);
        x.setFont(new Font("Times New Roman", Font.BOLD, 20));
        frame.add(x);
        x.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.exit(0);
            }
        });
        // ----------------------------------------------------------

        // ------------------Panel----------------------------------
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 300, 35);
        panel.setBackground(Color.decode("#DEE4E7"));
        frame.add(panel);
        // ---------------------------------------------------------

        // --------------------ID Label-------------------------
        JLabel idLabel = new JLabel("Student ID:");
        idLabel.setFont(font);
        idLabel.setBounds(25, 60, 120, 25);
        frame.add(idLabel);
        // --------------------------------------------------------

        // --------------------ID TextField-------------------------
        JTextField idTextField = new JTextField();
        idTextField.setBounds(150, 60, 120, 25);
        frame.add(idTextField);
        // -----------------------------------------------------------

        // --------------------Password Label-------------------------
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(font);
        passwordLabel.setBounds(25, 100, 120, 25);
        frame.add(passwordLabel);
        // ------------------------------------------------------------

        // --------------------Password TextField-------------------------
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 120, 25);
        frame.add(passwordField);
        // ----------------------------------------------------------------

        // --------------------Login Button-------------------------
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(100, 150, 100, 30);
        frame.add(loginButton);
        // ---------------------------------------------------------

        // ----------------------Login Button ActionListener-----------------------
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentID = idTextField.getText().trim();
                String password = String.valueOf(passwordField.getPassword()).trim();

                if (studentID.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter both Student ID and Password.",
                            "Missing Information", JOptionPane.WARNING_MESSAGE);
                } else {
                    if (verifyCredentials(studentID, password)) {
                        JOptionPane.showMessageDialog(frame, "Attendance marked!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        idTextField.setText("");
                        passwordField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Invalid credentials.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        // -----------------------------------------------------------------------

        // ------------------------------------------------------------------
        frame.setSize(300, 220);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setFocusable(true);
        frame.getContentPane().setBackground(new Color(139, 69, 19));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ------------------------------------------------------------------
    }

    private void connectToDatabase() {
        String url = "jdbc:mysql://localhost:3306/biometrics_db?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String pass = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException |SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean verifyCredentials(String studentID, String password) {
        String query = "SELECT * FROM students WHERE student_id = ? AND password = ?";

        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, studentID);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void main(String[] args) {
        CheckAttendance checkAttendance = new CheckAttendance();
        checkAttendance.createLoginGUI();
    }
}
