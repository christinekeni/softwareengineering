package Objects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Home extends JFrame {

    private JPanel rolePanel;
    private JButton lecturerButton;
    private JButton studentButton;
    private JButton adminButton;

    public Home() {
        setTitle("Attendance System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);

        // Create role panel
        rolePanel = new JPanel(new GridLayout(1, 3, 10, 10));
        rolePanel.setBackground(Color.WHITE);

        // Create role icons
        ImageIcon lecturerIcon = new ImageIcon("icons\\lecturer.png");
        ImageIcon studentIcon = new ImageIcon("icons\\student.png");
        ImageIcon adminIcon = new ImageIcon("icons\\admin.png");

        // Create role buttons
        lecturerButton = createRoleButton(lecturerIcon, "Lecturer");
        studentButton = createRoleButton(studentIcon, "Student");
        adminButton = createRoleButton(adminIcon, "Admin");

        // Add role buttons to the role panel
        rolePanel.add(lecturerButton);
        rolePanel.add(studentButton);
        rolePanel.add(adminButton);

        // Add the role panel to the frame
        add(rolePanel, BorderLayout.CENTER);
    }

    private JButton createRoleButton(ImageIcon icon, String role) {
        JButton button = new JButton(role, icon);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setFocusPainted(false);
        button.setFont(button.getFont().deriveFont(Font.BOLD, 14));

        // Set button size
        int buttonWidth = 120;
        int buttonHeight = 150;
        button.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        button.setMinimumSize(new Dimension(buttonWidth, buttonHeight));
        button.setMaximumSize(new Dimension(buttonWidth, buttonHeight));

        // Add hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBorderPainted(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBorderPainted(false);
            }
        });

        // Add ActionListener to handle button clicks
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openModule(role);
                dispose(); // Close the Home page
            }
        });

        return button;
    }

    private void openModule(String role) {
        if (role.equals("Lecturer")) {
            // Open teacher module
            Lecturer lecturer = new Lecturer();
            lecturer.setVisible(true);
        } else if (role.equals("Student")) {
            Student student = new Student();
            student.setVisible(true);
        } else if (role.equals("Admin")) {
            Admin admin = new Admin();
            admin.setVisible(true);
        }
    }

    public static void main(String[] args) {
        loadHomeObject();
    }

    public static void loadHomeObject() {
        try {
            // Set look and feel to system's default
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            Home home = new Home();
            home.setVisible(true);
        });
    }
}
