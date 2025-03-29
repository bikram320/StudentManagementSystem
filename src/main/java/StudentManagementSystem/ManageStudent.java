package StudentManagementSystem;

import java.sql.*;
import java.util.Scanner;

public class ManageStudent {

    // Database credentials
   final static String url = "jdbc:mysql://localhost:3306/student_db";
    final static String user = "root";
    final static String pass = "bikram123@";
    private static Connection con ;

    /*
         Creating connection with database that will work for all task(INSERTION , DELETION etc.)
     */
    public static Connection getConnection(){
        try {
            if(con==null || con.isClosed()){
                // Load MySQL JDBC Driver
                Class.forName("com.mysql.cj.jdbc.Driver");
                // Establishing connection
                con = DriverManager.getConnection(url, user, pass);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return  con;
    }
    public static void AddStudent(StudentData studentData) {
        //SQL query with 4 placeholders
        String insertSql = "INSERT INTO studentsdata(symbol_no, name, course, phone) VALUES (?, ?, ?, ?)";

        try {

            //Establishing connection for insertion
            Connection con = ManageStudent.getConnection();
            // Create PreparedStatement
            PreparedStatement stmt = con.prepareStatement(insertSql);

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

        } catch (Exception e ){
            System.out.println("Error : "+e.getMessage());
        }
    }
    public static void ViewStudentRecord(){
        String readSql = "SELECT * FROM studentsdata";
        try{
            //Establishing Connection to Read
            Connection con = ManageStudent.getConnection();
            //Creating Statement
            PreparedStatement stmt = con.prepareStatement(readSql);

            //Executing Query
            ResultSet rs = stmt.executeQuery();
            System.out.println("\nRecord in Database: ");
            while(rs.next()){
                System.out.println("Symbol No. : "+rs.getInt(1)+
                                    " Name : "+rs.getString(2)+
                                    " Course : "+rs.getString(3)+
                                    " Phone : "+rs.getString(4));
            }
            System.out.println("\nData Read Successfully .\n");
            //closing resources
            con.close();
            stmt.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}