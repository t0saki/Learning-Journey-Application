import java.util.Scanner;

import Base.Student;

import java.util.ArrayList;

public class Login {
   public static void addStudent(ArrayList<Student> array) {
      Scanner sc = new Scanner(System.in);
      String sid = sc.nextLine();
      while (true) {
         System.out.println("Plesae input the student sid:");
         sid = sc.nextLine();
         boolean flag = isUsed(array, sid);
         if (flag) {
            System.out.println("The sid you input has been used,please reinput");
         } else {
            break;
         }
      }
      // System.out.println("Plesae input the student name:");
      // String name = sc.nextLine();
      System.out.println("Please input your password: ");
      String password = sc.nextLine();
      Student s = new Student(sid, password);
      array.add(s);
      System.out.println("Add the student successfully!");
   }

   public static void studentLogin(ArrayList<Student> array) {
      Scanner sc = new Scanner(System.in);
      System.out.println("Plesae input the student sid: ");

   }

   public static void forgetPassword(ArrayList<Student> array) {
      Scanner sc = new Scanner(System.in);
      System.out.println("Please input your sid:");
      String sid = sc.nextLine();
      System.out.println("Please input the new password: ");
      String password = sc.nextLine();
      Student s = new Student(sid, password);

      for (int i = 0; i < array.size(); i++) {
         Student student = array.get(i);
         if (student.getSid().equals(sid)) {
            array.set(i, s);
         }
      }
      System.out.println("Update successfully!");
   }

   public static boolean isUsed(ArrayList<Student> array, String sid) {
      boolean flag = false;
      for (int i = 0; i < array.size(); i++) {
         Student s = array.get(i);
         if (s.getSid().equals(sid)) {
            flag = true;
            break;
         }
      }
      return flag;
   }
}
