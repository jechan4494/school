package SIS.loginFunction;

public class User {
  private String userID;
  private String password;
  private String role;
  private String name;
  private String ssn;// 이름 추가

  // 생성자
  public User(String userID, String password, String role, String name,String Ssn) { //유저관리
    this.userID = userID;
    this.password = password;
    this.role = role;
    this.name = name;
    this.ssn = ssn;

  }

  // Getter methods
  public String getSsn(){
    return ssn;
  }
  public String getUserID() {
    return userID;
  }

  public String getPassword() {
    return password;
  }

  public String getRole() {
    return role;
  }

  public String getName() {
    return name;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
