package StudentManagementSystem;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainSystem {
    public static void main(String[] args){


        //Scanner for user Input
        Scanner sc = new Scanner(System.in);

        boolean toContinue = true ;
        int choice = 0;
        while(toContinue){
            System.out.println("\n\n");
            System.out.println("--------Welcome To Student Management System--------");
            System.out.println("1.Add Student Record ");
            System.out.println("2.View Student Record ");
            System.out.println("3.Update Student Record ");
            System.out.println("4.Delete Student Record ");
            System.out.println("5.Exit");
            System.out.println("-----------------------------------------------------");
            try {
                System.out.print("Enter you choice : ");
                choice = sc.nextInt();
            }catch (InputMismatchException e ){
                System.out.println("Input Mismatch Exception occur , please try again .");
            }
            finally{
                sc.nextLine();
            }
            int symbolNo = 0;
            String name = null;
            String course= null;
            String Phone = null;
            switch (choice){
                case 1 :
                    try {
                        System.out.println("Enter A symbol no of Student :");
                        symbolNo = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Enter a Name of Student :");
                        name = sc.nextLine();
                        System.out.println("Enter a Course of Student :");
                        course = sc.nextLine();
                        System.out.println("Enter a Phone of Student :");
                        Phone = sc.nextLine();

                        ManageStudent.AddStudent(new StudentData(symbolNo,name,course,Phone));
                        break;
                    }catch (InputMismatchException e ){
                        System.out.println("Input Mismatch Exception Occur , please try again");
                    }catch(Exception e){
                        System.out.println("Error : "+e.getMessage());
                    }

                case 2 :
                    ManageStudent.ViewStudentRecord();
                    break;
                case 3 :
//                    ManageStudent.UpdateStudentRecord();
                    break;

                case 4 :
//                    ManageStudent.DeleteStudentRecord( );
                    break;

                case 5 :
                    System.out.println("Have a nice Day !");
                    toContinue=false;
                    break;

                default:
                    System.out.println("Invalid Option !! please Try Again");
            }
        }



    }
}
