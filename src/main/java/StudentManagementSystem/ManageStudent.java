package StudentManagementSystem;

import java.sql.*;
import java.util.Scanner;

public class ManageStudent {
    public void AddStudent(StudentData studentData) {

        // Database credentials
        String url = "jdbc:mysql://localhost:3306/student_db";
        String user = "root";
        String pass = "bikram123@";

        // Corrected SQL query with 4 placeholders
        String sql = "INSERT INTO studentsdata(symbol_no, name, course, phone) VALUES (?, ?, ?, ?)";

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            Connection con = DriverManager.getConnection(url, user, pass);

            // Create PreparedStatement
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1,studentData.getSymbolNo());
            stmt.setString(2,studentData.getName());
            stmt.setString(3,studentData.getCourse());
            stmt.setString(4,studentData.getPhone());

            // Execute update
            int rowAffected = stmt.executeUpdate();
            if (rowAffected > 0) {
                System.out.println("Successfully inserted");
            } else {
                System.out.println("Data Can't added to Database .");
            }


            // Close resources
            stmt.close();
            con.close();


        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
}