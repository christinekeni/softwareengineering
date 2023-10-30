package Objects;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class StudentH {

    Connection con;
    JFrame frame = new JFrame();
    DefaultTableModel model = new DefaultTableModel();

    public void stView(int id) throws SQLException {

        //------------------------CLOSE---------------------------
        JLabel x = new JLabel("x");
        x.setForeground(Color.red);
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


        //-----------------------MINIMIZE-----------------------------
        JLabel min = new JLabel("_");
        min.setForeground(Color.black);
        min.setBounds(935, 0, 100, 20);
        frame.add(min);
        min.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.setState(JFrame.ICONIFIED);
            }
        });
        //-------------------------------------------------------------

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
        JPanel panel = new  JPanel();
        panel.setBounds(0, 0, 1000, 35);
        panel.setBackground(Color.decode("#DEE4E7"));
        frame.add(panel);
        //---------------------------------------------------------

        //-------------------Welcome---------------------------------
        JLabel welcome = new JLabel("Welcome "+getUser(id)+",");
        welcome.setForeground(Color.decode("#000000"));
        welcome.setBounds(25, 100, 300, 20);
        welcome.setFont(new Font("Segoe UI", Font.BOLD, 24));
        frame.add(welcome);
        //-----------------------------------------------------------


        //----------------TABLE---------------------------------
        JTable table=new JTable();
        model = (DefaultTableModel)table.getModel();
        model.addColumn("DATE");
        model.addColumn("STATUS");
        JScrollPane scPane=new JScrollPane(table);
        scPane.setBounds(500, 50, 480, 525);
        table.setFont(new Font("Segoe UI", Font.BOLD, 20));
        table.setRowHeight(50);
        frame.add(scPane);
        //------------------------------------------------------

        //--------------------------INFO------------------------
        JLabel totalclass = new JLabel("Total Classes : ");
        totalclass.setBounds(25, 180, 250, 20);
        totalclass.setForeground(Color.decode("#000000"));
        totalclass.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        frame.add(totalclass);
        JLabel ttbox = new JLabel("");
        ttbox.setBounds(60, 220, 250, 20);
        ttbox.setForeground(Color.decode("#000000"));
        ttbox.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        frame.add(ttbox);
        JLabel classAtt = new JLabel("CLASSES ATTENDED : ");
        classAtt.setBounds(25, 280, 250, 20);
        classAtt.setForeground(Color.decode("#000000"));
        classAtt.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        frame.add(classAtt);
        JLabel atbox = new JLabel("");
        atbox.setBounds(60, 320, 250, 20);
        atbox.setForeground(Color.decode("#000000"));
        atbox.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        frame.add(atbox);
        JLabel classAbs = new JLabel("CLASSES MISSED : ");
        classAbs.setBounds(25, 380, 250, 20);
        classAbs.setForeground(Color.decode("#000000"));
        classAbs.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        frame.add(classAbs);
        JLabel mtbox = new JLabel("");
        mtbox.setBounds(60, 420, 250, 20);
        mtbox.setForeground(Color.decode("#000000"));
        mtbox.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        frame.add(mtbox);
        JLabel AttPer = new JLabel("ATTENDANCE PERCENTAGE : ");
        AttPer.setBounds(25, 480, 300, 20);
        AttPer.setForeground(Color.decode("#000000"));
        AttPer.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        frame.add(AttPer);
        JLabel prbox = new JLabel("");
        prbox.setBounds(60, 520, 250, 20);
        prbox.setForeground(Color.decode("#000000"));
        prbox.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        frame.add(prbox);
        //------------------------------------------------------

        //----------------------SETVALUES---------------------------
        int[] arr = stat(id); // Pass the 'id' parameter to the stat() method
        ttbox.setText(String.valueOf(arr[0]));
        atbox.setText(String.valueOf(arr[1]));
        mtbox.setText(String.valueOf(arr[2]));
        prbox.setText(String.valueOf(arr[3])+"%");
        //----------------------------------------------------------

        //-------------------------------------------------------
        frame.setSize(1000,600);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setFocusable(true);
        frame.getContentPane().setBackground(new Color(255, 250, 250));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //--------------------------------------------------------------
    }

    public String getUser(int id) throws SQLException {
        String DB_URL = "jdbc:mysql://localhost:3306/biometrics_db";
        String USER = "root";
        String PASS = "";

        con = DriverManager.getConnection(DB_URL,USER,PASS);
        String str = "SELECT studentName FROM student WHERE studentId = "+id;
        Statement stm = con.createStatement();
        ResultSet rst = stm.executeQuery(str);
        rst.next();
        return rst.getString("studentName");
    }

    public void tblupdt(int id) {
        try {
            ResultSet res = dbSearch(id);
            model.setRowCount(0); // Clear the existing rows in the model
            while (res.next()) {
                model.addRow(new Object[] { res.getString("dt"), res.getString("status") });
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public int[] stat(int id) throws SQLException {
        String str = "SELECT COUNT(*) AS Present FROM attendance WHERE studentId = " + id + " AND status = 'Present'";
        String str2 = "SELECT COUNT(*) AS Absent FROM attendance WHERE studentId = " + id + " AND status = 'Absent'";
        int[] x = new int[4];
        Statement stm = con.createStatement();
        ResultSet rst = stm.executeQuery(str);
        rst.next();
        x[1] = rst.getInt("Present");
        rst = stm.executeQuery(str2);
        rst.next();
        x[2] = rst.getInt("Absent");
        x[0] = x[1] + x[2];

        if (x[0] != 0) {
            x[3] = (x[1] * 100) / x[0];
        } else {
            x[3] = 0; // or any other value you want to assign in case of division by zero
        }

        tblupdt(id);
        return x;
    }


    public ResultSet dbSearch(int id) throws SQLException {
        String str1 = "SELECT * from attendance where studentId = "+id+" ORDER BY dt desc";
        Statement stm = con.createStatement();
        ResultSet rst = stm.executeQuery(str1);
        return rst;
    }

    public static void main(String[] args) {
        try {
            StudentH studentH = new StudentH();
            studentH.stView(4); // Provide the student ID to display the view
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
