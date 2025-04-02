package StudentManagementSystem;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainSystem {
    public static void main(String[] args) {

        //Scanner for user Input
        Scanner sc = new Scanner(System.in);

        boolean toContinue = true ;

        while(toContinue){
            System.out.println("\n\n");
            System.out.println("--------Welcome To Student Management System--------");
            System.out.println("1.Add Student Record ");
            System.out.println("2.View Student Record ");
            System.out.println("3.Update Student Record ");
            System.out.println("4.Delete Student Record ");
            System.out.println("5.Exit");
            System.out.println("-----------------------------------------------------");

            int choice = 0;
            while (choice == 0) {
                System.out.print("Enter your choice: ");
                try {
                    choice = Integer.parseInt(sc.nextLine()); // Use nextLine and parse it to an integer
                    if (choice < 1 || choice > 5) {
                        System.out.println("Invalid choice, please enter a number between 1 and 5.");
                        choice = 0; // Invalid choice, prompt again
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Input Mismatch Exception occurred, please try again.");
                }
            }

            switch (choice){
                case 1 :
                    ManageStudent.AddStudent(sc);
                    break;

                case 2 :
                    ManageStudent.ViewStudentRecord();
                    break;
                case 3 :

                    ManageStudent.UpdateStudentRecord(sc);
                    break;

                case 4 :
                    ManageStudent.DeleteStudentRecord(sc);
                    break;

                case 5 :
                    System.out.println("Have a nice Day !");
                    toContinue=false;
                    sc.close();
                    break;

                default:
                    System.out.println("Invalid Option !! please Try Again");
            }
        }
    }
}
