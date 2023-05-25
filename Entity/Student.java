package Entity;

public class Student {
   private String studentId;
   private String userName;
   private String password;
   private String major;
   private String startYear;

   public Student(String sid, String name, String password) {
      this.studentId = sid;
      this.userName = name;
      this.password = password;
      this.major = "Telecommunications Engineering and Management";
      this.startYear = "2020";
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

   public void setMajor(String major) {
      this.major = major;
   }

   public String getMajor() {
      return major;
   }

   public void setStartYear(String startYear) {
      this.startYear = startYear;
   }

   public String getStartYear() {
      return startYear;
   }

   public String toString() {
      return "User Name: " + userName + "\nStudent Id: " + studentId + "\nPassword: " + password + "\nMajor: " + major
            + "\nStart Year: " + startYear;
   }

   public String toCSVRow() {
      return userName + "," + studentId + "," + password + "," + major + "," + startYear;
   }
}
