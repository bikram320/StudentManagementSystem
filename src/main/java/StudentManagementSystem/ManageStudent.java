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
    public static void AddStudent(Scanner sc) {
        try {
            int symbolNo = 0;
            while (symbolNo == 0) {
                System.out.println("Enter A symbol no of Student :");
                try {
                    symbolNo = Integer.parseInt(sc.nextLine()); // Convert input to integer
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter a valid symbol number.");
                }
            }
            System.out.println("Enter a Name of Student :");
            String name = sc.nextLine();
            System.out.println("Enter a Course of Student :");
            String course = sc.nextLine();
            System.out.println("Enter a Phone of Student :");
            String phone = sc.nextLine();
            StudentData studentData = new StudentData(symbolNo,name,course,phone);

            //SQL query with 4 placeholders
            String insertSql = "INSERT INTO studentsdata(symbol_no, name, course, phone) VALUES (?, ?, ?, ?)";

            try {

                //Establishing connection for insertion
                Connection con = ManageStudent.getConnection();
                // Create PreparedStatement
                PreparedStatement stmt = con.prepareStatement(insertSql);

                stmt.setInt(1, studentData.getSymbolNo());
                stmt.setString(2, studentData.getName());
                stmt.setString(3, studentData.getCourse());
                stmt.setString(4, studentData.getPhone());

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

            } catch (Exception e) {
                System.out.println("Error : " + e.getMessage());
            }
        }catch (Exception e){
            System.out.println("Error : "+e.getMessage());
        }
    }
    public static void ViewStudentRecord(){
        try {
            String readSql = "SELECT * FROM studentsdata";
            try {
                //Establishing Connection to Read
                Connection con = ManageStudent.getConnection();
                //Creating Statement
                PreparedStatement stmt = con.prepareStatement(readSql);

                //Executing Query
                ResultSet rs = stmt.executeQuery();
                System.out.println("\nRecord in Database: ");
                while (rs.next()) {
                    System.out.println("Symbol No. : " + rs.getInt(1) +
                            " Name : " + rs.getString(2) +
                            " Course : " + rs.getString(3) +
                            " Phone : " + rs.getString(4));
                }
                System.out.println("\nData Read Successfully .");
                //closing resources
                con.close();
                stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }catch (Exception e){
            System.out.println("Error : "+e.getMessage());
        }
    }
    public static void UpdateStudentRecord(Scanner sc) {
        try {

            //Displaying the Record by which user can select and update it
            ManageStudent.ViewStudentRecord();

            int updateSymbolNo = 0;
            while (updateSymbolNo == 0) {
                System.out.print("Enter a Symbol no. to update it's Record : ");
                try {
                    updateSymbolNo = Integer.parseInt(sc.nextLine()); // Convert input to integer
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter a valid symbol number.");
                }
            }

            System.out.println("Enter a Updated Data : ");
            System.out.print("Enter a Name :");
            String updateName = sc.nextLine();
            System.out.print("Enter a Course : ");
            String updateCourse = sc.nextLine();
            System.out.print("Enter a Phone : ");
            String updatePhone = sc.nextLine();

            String updateSql = "UPDATE studentsdata SET name=? ,course = ? , phone=? WHERE symbol_no=?";

            try {

                //Establishing connection for Update
                Connection con = ManageStudent.getConnection();
                // Create PreparedStatement
                PreparedStatement stmt = con.prepareStatement(updateSql);

                stmt.setString(1, updateName);
                stmt.setString(2, updateCourse);
                stmt.setString(3, updatePhone);
                stmt.setInt(4, updateSymbolNo);

                // Execute update
                int rowAffected = stmt.executeUpdate();
                if (rowAffected > 0) {
                    System.out.println("Successfully Updated");
                } else {
                    System.out.println("Invalid symbol number.");
                }

                // Close resources
                stmt.close();
                con.close();

            } catch (Exception e) {
                System.out.println("Error : " + e.getMessage());
            }
        }catch (Exception e){
            System.out.println("Error : " +e.getMessage());
        }
    }
    public static void DeleteStudentRecord(Scanner sc)  {
        try {

            //Displaying the record before Deleting
            ManageStudent.ViewStudentRecord();
            int deleteSymbolNo = 0;
            while (deleteSymbolNo == 0) {
                System.out.print("Enter a Symbol_No to Delete Record : ");
                try {
                    deleteSymbolNo = Integer.parseInt(sc.nextLine()); // Convert input to integer
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter a valid symbol number.");
                }
            }


            String deleteSql = "DELETE FROM studentsdata WHERE symbol_no=?";

            try {
                //Establishing connection for Update
                Connection con = ManageStudent.getConnection();
                // Create PreparedStatement
                PreparedStatement stmt = con.prepareStatement(deleteSql);

                stmt.setInt(1, deleteSymbolNo);

                // Execute update
                int rowAffected = stmt.executeUpdate();
                if (rowAffected > 0) {
                    System.out.println("Successfully Deleted Data .");
                } else {
                    System.out.println("Invalid Symbol Number.");
                }

                // Close resources
                stmt.close();
                con.close();


            } catch (Exception e) {
                System.out.println("Error : " + e.getMessage());
            }
        }catch (Exception e){
            System.out.println("Error : "+e.getMessage());
        }

    }
}