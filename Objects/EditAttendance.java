package Objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import Objects.Home;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Objects.Home;


public class EditAttendance {

    Connection con;
    DefaultTableModel model = new DefaultTableModel();

    public void editView() throws SQLException {

        connect();
        JFrame frame = new JFrame();
        Font text = new Font("Arial", Font.BOLD, 18);
        Font btn = new Font("Arial", Font.BOLD, 20);

        //------------------------CLOSE---------------------------
        JLabel x = new JLabel("X");
        x.setForeground(Color.decode("#37474F"));
        x.setBounds(965, 10, 100, 20);
        x.setFont(new Font("Times New Roman", Font.BOLD, 20));
        frame.add(x);
        x.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });
        //----------------------------------------------------------

        //-----------------------BACK---------------------------------
        JLabel back = new JLabel("< BACK");
        back.setForeground(Color.decode("#37474F"));
        back.setFont(new Font("Times New Roman", Font.BOLD, 17));
        back.setBounds(18, 10, 100, 20);
        frame.add(back);
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                Home home = new Home();
                home.setVisible(true); // Assuming the Home class has a display() method to show the home screen
            }
        });

        //--------------------------------------------------------------

        //------------------Panel----------------------------------
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 1000, 35);
        panel.setBackground(Color.decode("#DEE4E7"));
        frame.add(panel);
        //---------------------------------------------------------

        //----------------TABLE---------------------------------
        JTable table = new JTable();
        model = (DefaultTableModel) table.getModel();
        model.addColumn("ID");
        model.addColumn("NAME");
        model.addColumn("STATUS");
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        JScrollPane scPane = new JScrollPane(table);
        scPane.setBounds(500, 50, 480, 525);
        frame.add(scPane);
        //------------------------------------------------------

        //-------------------------DATE-------------------------
        JLabel dt = new JLabel("DATE : ");
        dt.setFont(text);
        dt.setBounds(25, 60, 75, 20);
        dt.setForeground(Color.decode("#DEE4E7"));
        frame.add(dt);
        JTextField dtbox = new JTextField();
        dtbox.setBounds(100, 60, 150, 25);
        dtbox.setBackground(Color.decode("#DEE4E7"));
        dtbox.setFont(text);
        dtbox.setForeground(Color.decode("#37474F"));
        String dateInString = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        dtbox.setText(dateInString);
        frame.add(dtbox);
        //-------------------------------------------------------

        //--------------------CLASS---------------------------------
        JLabel classes = new JLabel("CLASS : ");
        classes.setFont(text);
        classes.setBounds(25, 150, 100, 20);
        classes.setForeground(Color.decode("#DEE4E7"));
        frame.add(classes);
        JComboBox<String> clss = new JComboBox<>(classEt());
        clss.setBounds(110, 150, 50, 25);
        frame.add(clss);
        //------------------------------------------------------------

        //----------------------VIEWBUTTON-----------------------
        JButton view = new JButton("VIEW");
        view.setBounds(175, 275, 150, 50);
        view.setFont(btn);
        view.setBackground(Color.decode("#DEE4E7"));
        view.setForeground(Color.decode("#37474F"));
        frame.add(view);
        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tblupdt(String.valueOf(clss.getSelectedItem()), dtbox.getText());
            }
        });
        //-------------------------------------------------------
//----------------------ABSENTBUTTON-----------------------
        JButton ab = new JButton("Absent");
        ab.setBounds(75, 365, 150, 50);
        ab.setFont(btn);
        ab.setBackground(Color.decode("#DEE4E7"));
        ab.setForeground(Color.decode("#37474F"));
        frame.add(ab);
        ab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    int modelRow = table.convertRowIndexToModel(selectedRow);
                    String currentStatus = (String) model.getValueAt(modelRow, 2);
                    String newStatus = currentStatus.equals("Absent") ? "Present" : "Absent";
                    model.setValueAt(newStatus, modelRow, 2);
                    int studentId = (int) model.getValueAt(modelRow, 0);
                    String date = dtbox.getText();
                    try {
                        editItem(studentId, newStatus, date);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
//-------------------------------------------------------

//----------------------PRESENTBUTTON-----------------------
        JButton pre = new JButton("Present");
        pre.setBounds(275, 365, 150, 50);
        pre.setFont(btn);
        pre.setBackground(Color.decode("#DEE4E7"));
        pre.setForeground(Color.decode("#37474F"));
        frame.add(pre);
        pre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    int modelRow = table.convertRowIndexToModel(selectedRow);
                    String currentStatus = (String) model.getValueAt(modelRow, 2);
                    String newStatus = currentStatus.equals("Present") ? "Absent" : "Present";
                    model.setValueAt(newStatus, modelRow, 2);
                    int studentId = (int) model.getValueAt(modelRow, 0);
                    String date = dtbox.getText();
                    try {
                        editItem(studentId, newStatus, date);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
//-------------------------------------------------------


//----------------------SUBMITBUTTON-----------------------
        JButton sbmt = new JButton("SUBMIT");
        sbmt.setBounds(75, 450, 150, 50);
        sbmt.setFont(btn);
        sbmt.setBackground(Color.decode("#DEE4E7"));
        sbmt.setForeground(Color.decode("#37474F"));
        frame.add(sbmt);
        sbmt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowCount = model.getRowCount();
                for (int i = 0; i < rowCount; i++) {
                    try {
                        int studentId = Integer.parseInt(String.valueOf(table.getValueAt(i, 0)));
                        String status = String.valueOf(table.getValueAt(i, 2));
                        String date = dtbox.getText();
                        editItem(studentId, status, date);
                    } catch (NumberFormatException | SQLException e1) {
                        e1.printStackTrace();
                    }
                }
                model.setRowCount(0);
            }
        });
//-------------------------------------------------------

//----------------------DELETEBUTTON-----------------------
        JButton del = new JButton("DELETE");
        del.setBounds(275, 450, 150, 50);
        del.setFont(btn);
        del.setBackground(Color.decode("#DEE4E7"));
        del.setForeground(Color.decode("#37474F"));
        frame.add(del);
        del.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedClass = String.valueOf(clss.getSelectedItem());
                String selectedDate = dtbox.getText();
                deleteAttendance(selectedClass, selectedDate);
                model.setRowCount(0);
            }
        });
//-------------------------------------------------------


        //-------------------------------------------------------
        frame.setSize(1000, 600);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setFocusable(true);
        frame.getContentPane().setBackground(new Color(139, 69, 19));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //--------------------------------------------------------------
    }

    public void connect() throws SQLException {
        // ENTER PORT, USER, PASSWORD.
        String url = "jdbc:mysql://localhost:3306/biometrics_db";
        String user = "root";
        String pass = "";

        con = DriverManager.getConnection(url, user, pass);
    }

    public ResultSet dbSearch(String className, String date) throws SQLException {
        String query = "SELECT * FROM attendance JOIN student ON attendance.studentId = student.studentId WHERE attendance.class = '"
                + className + "' AND attendance.dt = '" + date + "'";
        Statement stm = con.createStatement();
        return stm.executeQuery(query);
    }

    public String[] classEt() throws SQLException {
        String query = "SELECT className FROM class";
        Statement stm = con.createStatement();
        ResultSet rst = stm.executeQuery(query);
        int count = 0;
        while (rst.next()) {
            count++;
        }
        String[] classes = new String[count];
        rst.beforeFirst();
        int i = 0;
        while (rst.next()) {
            classes[i] = rst.getString("className");
            i++;
        }
        return classes;
    }

    public void tblupdt(String className, String date) {
        try {
            model.setRowCount(0);
            ResultSet res = dbSearch(className, date);
            while (res.next()) {
                int studentId = res.getInt("studentId");
                String studentName = res.getString("studentName");
                String status = res.getString("status");
                model.addRow(new Object[] { studentId, studentName, status });
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

  /*public void editItem(int studentId, String status, String date) throws SQLException {
        String updateQuery = "UPDATE attendance SET status = '" + status + "' WHERE studentId = " + studentId + " AND dt = '"
                + date + "'";
        Statement stm = con.createStatement();
        stm.executeUpdate(updateQuery);
    }*/
  public void editItem(int studentId, String status, String date) throws SQLException {
      String updateQuery = "UPDATE attendance SET status = ? WHERE studentId = ? AND dt = ?";
      try (PreparedStatement preparedStatement = con.prepareStatement(updateQuery)) {
          preparedStatement.setString(1, status);
          ((PreparedStatement) preparedStatement).setInt(2, studentId);
          preparedStatement.setString(3, date);
          preparedStatement.executeUpdate();
      } catch (SQLException e) {
          e.printStackTrace();
      }
  }


    public void deleteAttendance(String className, String date) {
        String deleteQuery = "DELETE FROM attendance WHERE class = '" + className + "' AND dt = '" + date + "'";
        try {
            Statement stm = con.createStatement();
            stm.executeUpdate(deleteQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
