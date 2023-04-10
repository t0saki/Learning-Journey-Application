package Base;

public class Student {
   private String sid;
   private String name;
   private String password;

   public Student(String sid, String name, String password) {
      this.sid = sid;
      this.name = name;
      this.password = password;
   }

   public void setSid(String sid) {
      this.sid = sid;
   }

   public String getSid() {
      return sid;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getPassword() {
      return password;
   }

   public String toString() {
      return "Student Id: " + sid + "\nName: " + name + "\nPassword: " + password;
   }
}
