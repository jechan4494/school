package SIS.loginFunction;

public class StudentRol extends User {
  private String studentID;  // 학번
  private String department;  // 학과
  private String ssn;  // 주민번호

  // 학생 정보 반환
  public StudentRol(String studentName, String userID, String password, String role, String studentID, String department, String ssn) {
    super(userID, password, role, studentName,ssn);
    this.studentID = studentID;
    this.department = department;
    this.ssn = ssn;
  }

  // Getter methods
  public String getStudentID() {
    return studentID;
  }

  public String getDepartment() {
    return department;
  }

  public String getSsn() {
    return ssn;
  }

  @Override
  public String getUserID() {
    return super.getUserID();  // User 클래스의 getUserID() 메서드를 호출하여 반환
  }

  @Override
  public String getPassword() {
    return super.getPassword();  // User 클래스의 getPassword() 메서드를 호출하여 반환
  }

  @Override
  public String getRole() {
    return super.getRole();  // User 클래스의 getRole() 메서드를 호출하여 반환
  }

  @Override
  public String getName() {
    return super.getName();  // User 클래스의 getName() 메서드를 호출하여 반환
  }
}
