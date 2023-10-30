package Objects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AdminH {
    public void homeView(int id) throws SQLException {
        JFrame frame = new JFrame();
        Font btn = new Font("Arial", Font.BOLD, 14);

        // Set background image
        ImageIcon backgroundImage = new ImageIcon("images/background.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 40)); // Set layout manager to FlowLayout with center alignment and vertical gap of 40
        backgroundLabel.setOpaque(true); // Set to true for JLabel to be visible
        frame.setContentPane(backgroundLabel); // Use setContentPane to add components

        //-----------------------BACK---------------------------------
        JLabel back = new JLabel("< BACK");
        back.setForeground(Color.decode("#37474F"));
        back.setFont(new Font("Times New Roman", Font.BOLD, 17));
        back.setBounds(10, 10, 100, 20);
        frame.add(back);
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                Home home = new Home();
                home.setVisible(true); // Assuming the Home class has a display() method to show the home screen
            }
        });
        //-----------------------------------------------------------------


        // Create and configure components
        JLabel titleLabel = new JLabel("Admin Home Page");
        titleLabel.setForeground(new Color(139, 69, 19)); // Brown color
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));

        JLabel emptyLabel = new JLabel(); // Empty label as a placeholder

        JButton studentsButton = new JButton("STUDENTS");
        studentsButton.setFont(btn);
        studentsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                studentsButton.setForeground(new Color(139, 69, 19)); // Change text color on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                studentsButton.setForeground(Color.BLACK); // Reset text color
            }
        });

        JButton addAttendanceButton = new JButton("ADD ATTENDANCE");
        addAttendanceButton.setFont(btn);
        addAttendanceButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addAttendanceButton.setForeground(new Color(139, 69, 19)); // Change text color on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                addAttendanceButton.setForeground(Color.BLACK); // Reset text color
            }
        });

        JButton editAttendanceButton = new JButton("EDIT ATTENDANCE");
        editAttendanceButton.setFont(btn);
        editAttendanceButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                editAttendanceButton.setForeground(new Color(139, 69, 19)); // Change text color on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                editAttendanceButton.setForeground(Color.BLACK); // Reset text color
            }
        });

        JButton lecturersButton = new JButton("LECTURERS");
        lecturersButton.setFont(btn);
        lecturersButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lecturersButton.setForeground(new Color(139, 69, 19)); // Change text color on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                lecturersButton.setForeground(Color.BLACK); // Reset text color
            }
        });

        JButton classButton = new JButton("CLASS");
        classButton.setFont(btn);
        classButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                classButton.setForeground(new Color(139, 69, 19)); // Change text color on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                classButton.setForeground(Color.BLACK); // Reset text color
            }
        });

        // Add components to the backgroundLabel
        backgroundLabel.add(titleLabel);
        backgroundLabel.add(emptyLabel);
        backgroundLabel.add(studentsButton);
        backgroundLabel.add(addAttendanceButton);
        backgroundLabel.add(editAttendanceButton);
        backgroundLabel.add(lecturersButton);
        backgroundLabel.add(classButton);

        // Configure button actions
        studentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StudentH studentH = new StudentH();
                try {
                    studentH.stView(id);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        addAttendanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddAttendance addAttendance = new AddAttendance();
                try {
                    addAttendance.addView();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        editAttendanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditAttendance editAttendance = new EditAttendance();
                try {
                    editAttendance.editView();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        lecturersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LecturerH lecturerH = new LecturerH();
                try {
                    lecturerH.tcView(id);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        classButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Classes classes = new Classes();
                classes.classView();
            }
        });

        // Frame configuration
        frame.setTitle("Admin Home Page");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public String getUser(int id) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/biometrics_db";
        String user = "root";
        String pass = "";

        Connection con = DriverManager.getConnection(url, user, pass);
        String str = "SELECT adminName FROM admin WHERE adminId = " + id;
        Statement stm = con.createStatement();
        ResultSet rst = stm.executeQuery(str);

        if (rst.next()) {
            return rst.getString("adminName");
        } else {
            return "Unknown"; // or any other default value
        }
    }

}
