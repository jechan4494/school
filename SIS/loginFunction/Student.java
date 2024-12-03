package SIS.loginFunction;

public class Student {
  private String name;          // 학생 이름
  private int appCredits;   // 신청 학점
  private double aveGrade;  // 평균 학점
  private String courseName;    // 신청 강의
  private int acquireCredits;    // 취득 학점

  // 학생 정보 반환 학점 매기는용
  public Student(String name, int appliedCredits, double averageGrade, String courseName, int earnedCredits) {
    this.name = name;
    this.appCredits = appliedCredits;
    this.aveGrade = averageGrade;
    this.courseName = courseName;
    this.appCredits = earnedCredits;
  }

  // Getter 메서드
  public String getName() {
    return name;
  }

  public int getAppliedCredits() {
    return appCredits;
  }

  public double getAverageGrade() {
    return aveGrade;
  }

  public String getCourseName() {
    return courseName;
  }

  public int getEarnedCredits() {
    return acquireCredits;
  }

  // Setter 메서드
  public void setName(String name) {
    this.name = name;
  }

}
