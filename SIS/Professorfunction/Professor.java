package SIS.Professorfunction;

import SIS.loginFunction.User;

public class Professor extends User { // 교수 정보
  private String department;
  private String professorID;
  private String ssn;

  // 생성자
  public Professor(String userID, String password, String role, String name,String professorID,String department ,String ssn) {
    super(userID, password, role, name,ssn);
    this.department = department;
    this.professorID = professorID;
    this.ssn = ssn;
  }


  // Getter methods
  public String getDepartment()
  {
    return department;
  }
  public String getProfessorID()
  {
    return professorID;
  }
  public String getSsn() {
    return ssn;
  }

  // ProfessorUser 서브클래스
  public static class ProfessorUser extends Professor {
    public ProfessorUser(String userID, String password, String name, String department, String professorID, String ssn) {
      super(userID, password, "Professor", name,  professorID, department,ssn);
    }
  }
}
