package Objects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import Objects.Home;


class LecturerH {
    public void tcView(int id) throws SQLException {

        JFrame frame = new JFrame();
        Font btn = new Font("Arial", Font.BOLD, 14);

        // Set background image
        ImageIcon backgroundImage = new ImageIcon("images/background.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 40)); // Set layout manager to FlowLayout with center alignment and vertical gap of 40
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
        JLabel welcome = new JLabel("Welcome " + getUser(id) + ",");
        welcome.setForeground(new Color(139, 69, 19)); // Brown color
        welcome.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel titleLabel = new JLabel("Lecturer Home Page");
        titleLabel.setForeground(new Color(139, 69, 19));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));

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

        // Add components to the backgroundLabel
        backgroundLabel.add(welcome);
        backgroundLabel.add(titleLabel);
        backgroundLabel.add(addAttendanceButton);
        backgroundLabel.add(editAttendanceButton);

        // Configure button actions
        addAttendanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddAttendance addatt = new AddAttendance();
                try {
                    addatt.addView();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        editAttendanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Instantiate and show the EditAttendance frame or view
                EditAttendance editatt = new EditAttendance();
                try {
                    editatt.editView();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        // Frame configuration
        frame.setTitle("Lecturer Home Page");
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
        String str = "SELECT lecName FROM lecturer WHERE lecId = " + id;
        Statement stm = con.createStatement();
        ResultSet rst = stm.executeQuery(str);
        rst.next();
        return rst.getString("lecName");
    }
}
