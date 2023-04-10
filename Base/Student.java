package Base;

public class Student {
   private String studentId;
   private String userName;
   private String password;

   public Student(String sid, String name, String password) {
      this.studentId = sid;
      this.userName = name;
      this.password = password;
   }

   public void setStudentId(String sid) {
      this.studentId = sid;
   }

   public String getStudentId() {
      return studentId;
   }

   public void setUserName(String name) {
      this.userName = name;
   }

   public String getUserName() {
      return userName;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getPassword() {
      return password;
   }

   public String toString() {
      return "User Name: " + userName + "\nStudent Id: " + studentId + "\nPassword: " + password;
   }

   public String toCSVRow() {
      return userName + "," + studentId + "," + password;
   }
}
