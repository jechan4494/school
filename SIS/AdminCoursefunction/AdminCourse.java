package SIS.AdminCoursefunction;

import SIS.loginFunction.User;

public class AdminCourse extends User { // 관리자 정보 페이지
  private String adminID;
  private String ssn;
  private String adminName;

  // 수정된 생성자
  public AdminCourse(String userID, String password, String role, String adminName, String adminID, String ssn) {
    super(userID, password, role, adminName, ssn);  // 부모 생성자 호출
    this.adminID = adminID;
    this.ssn = ssn;
    this.adminName = adminName;
  }

  // Getter and Setter methods
  public String getAdminID() {
    return adminID;
  }

  public String getSsn() {
    return ssn;
  }

  public String getName() {
    return adminName;
  }

}
