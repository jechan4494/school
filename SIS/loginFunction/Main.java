package SIS.loginFunction;

import SIS.AdminCoursefunction.AdminCourse;
import SIS.AdminUserFunction.AdminUser;
import SIS.Professorfunction.Professor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// 메인 컴파일 페이지 엑셀 로드하는곳
public class Main {

  // 사용자 추가 메서드
  public static void addUserToExcel(String fileName, User newUser) throws IOException {
    File file = new File(fileName);
    Workbook workbook;
    Sheet userSheet;

    // 파일이 없으면 새로 생성하고 추가
    if (!file.exists()) {
      workbook = new XSSFWorkbook();
      userSheet = workbook.createSheet("Users");

      // 첫 번째 행(헤더) 생성 기본값
      Row headerRow = userSheet.createRow(0);
      headerRow.createCell(0).setCellValue("이름");
      headerRow.createCell(1).setCellValue("아이디");
      headerRow.createCell(2).setCellValue("비밀번호");
      headerRow.createCell(3).setCellValue("직업");
      headerRow.createCell(4).setCellValue("학번/교수번호");
      headerRow.createCell(5).setCellValue("학과");
      headerRow.createCell(6).setCellValue("주민번호");
    } else {
      try (FileInputStream fis = new FileInputStream(fileName)) {
        workbook = new XSSFWorkbook(fis);
        userSheet = workbook.getSheetAt(0);
      }
    }

    // 새 행을 추가
    int rowIndex = userSheet.getPhysicalNumberOfRows();
    Row newRow = userSheet.createRow(rowIndex);

    // 사용자 타입에 따라 데이터 설정
    if (newUser instanceof StudentRol student) {
      newRow.createCell(0).setCellValue(student.getName()); // 이름
      newRow.createCell(1).setCellValue(student.getUserID()); // 아이디
      newRow.createCell(2).setCellValue(student.getPassword()); // 비밀번호
      newRow.createCell(3).setCellValue("Student"); // 직업
      newRow.createCell(4).setCellValue(student.getStudentID()); // 학번
      newRow.createCell(5).setCellValue(student.getDepartment()); // 학과
      newRow.createCell(6).setCellValue(student.getSsn()); // 주민번호
    } else if (newUser instanceof Professor professor) {
      newRow.createCell(0).setCellValue(professor.getName()); // 이름
      newRow.createCell(1).setCellValue(professor.getUserID()); // 아이디
      newRow.createCell(2).setCellValue(professor.getPassword()); // 비밀번호
      newRow.createCell(3).setCellValue("Professor"); // 직업
      newRow.createCell(4).setCellValue(professor.getProfessorID()); // 교수번호
      newRow.createCell(5).setCellValue(professor.getDepartment()); // 학과
      newRow.createCell(6).setCellValue(professor.getSsn()); // 주민번호
    } else if (newUser instanceof AdminCourse adminCourse) {
      newRow.createCell(0).setCellValue(adminCourse.getName()); // 이름
      newRow.createCell(1).setCellValue(adminCourse.getUserID()); // 아이디
      newRow.createCell(2).setCellValue(adminCourse.getPassword()); // 비밀번호
      newRow.createCell(3).setCellValue("AdminCourse"); // 직업
      newRow.createCell(4).setCellValue(adminCourse.getAdminID()); // 관리자ID
      newRow.createCell(5).setCellValue("AdminCourse"); // 학과는 비워둠
      newRow.createCell(6).setCellValue(adminCourse.getSsn()); // 주민번호
    }else if(newUser instanceof AdminUser adminUser){
      newRow.createCell(0).setCellValue(adminUser.getName());
      newRow.createCell(1).setCellValue(adminUser.getUserID());
      newRow.createCell(2).setCellValue(adminUser.getPassword());
      newRow.createCell(3).setCellValue("AdminUser");
      newRow.createCell(4).setCellValue(adminUser.getAdminUserID());
      newRow.createCell(5).setCellValue("AdminUser");
      newRow.createCell(6).setCellValue(adminUser.getSsn());
    }

    // 엑셀 파일 저장
    try (FileOutputStream fos = new FileOutputStream(fileName)) {
      workbook.write(fos);
    }
  }

  // 사용자 인증
  public static User authenticate(String userID, String password, List<User> users) {
    for (User user : users) {
      System.out.println("로그인 시 비교: ID = " + userID + ", 비밀번호 = " + password);
      if (user.getUserID().equals(userID) && user.getPassword().equals(password)) {
        System.out.println("로그인 성공: " + user.getUserID());
        return user;  // 로그인 성공
      }
    }
    System.out.println("로그인 실패: 사용자 정보 일치 없음");
    return null;  // 로그인 실패
  }


  public List<User> loadUsersFromExcel(String fileName) throws IOException {
    List<User> users = new ArrayList<>();
    File file = new File(fileName);

    if (!file.exists()) {
      System.out.println("엑셀 파일이 존재하지 않습니다: " + fileName);
      return users;
    }

    try (FileInputStream fis = new FileInputStream(file);
         Workbook workbook = new XSSFWorkbook(fis)) {

      Sheet userSheet = workbook.getSheetAt(0);
      if (userSheet == null) {
        System.out.println("'Users' 시트를 찾을 수 없습니다.");
        return users;
      }

      for (Row row : userSheet) {
        if (row.getRowNum() == 0) continue; // 첫 번째 행은 헤더이므로 건너뜀

        String userID = getCellValue(row, 1); // userID
        String password = getCellValue(row, 2); // 비밀번호
        String role = getCellValue(row, 3); // 역할

        // 데이터 출력: userID, password, role 확인
        System.out.println("Loaded user: userID = " + userID + ", password = " + password + ", role = " + role);

        if ("Student".equalsIgnoreCase(role)) {
          String studentName = getCellValue(row, 0); // 이름
          String department = getCellValue(row, 5);  // 학과
          String ssn = getCellValue(row, 4);  // 주민번호
          String studentID = getCellValue(row, 6);  // 학번
          users.add(new StudentRol(studentName, userID, password, role, studentID, department, ssn));
        } else if ("Professor".equalsIgnoreCase(role)) {
          String professorName = getCellValue(row, 0);  // 이름
          String department = getCellValue(row, 5);    // 학과
          String professorID = getCellValue(row, 6);   // 교수번호
          users.add(new Professor(userID, password, role, professorName, department, professorID, ""));
        } else if ("AdminCourse".equalsIgnoreCase(role)) {
          String adminName = getCellValue(row, 0); // 이름
          String ssn = getCellValue(row, 4);  // 주민번호
          String adminID = getCellValue(row, 6);  // 관리자 ID (optional, 빈 값일 수 있음)
          users.add(new AdminCourse(userID, password, role, adminName, adminID, ssn));
        } else if ("AdminUser".equalsIgnoreCase(role)) {
          // AdminUser를 일반 사용자처럼 처리: String으로 저장
          String adminUserName = getCellValue(row, 0);
          String ssn = getCellValue(row, 4);
          String adminUserID = getCellValue(row, 6);
          users.add(new AdminUser(userID, password, role, adminUserName, adminUserID, ssn));
        }
      }
    }
    return users;
  }

  public String getCellValue(Row row, int columnIndex) {
    Cell cell = row.getCell(columnIndex);
    if (cell == null) {
      return "";
    }

    return switch (cell.getCellType()) {
      case NUMERIC -> {
        // 숫자일 경우, 소수점 처리
        double value = cell.getNumericCellValue();
        // 정수인 경우 소수점을 제거하고 문자열로 변환
        yield (value % 1 == 0) ? String.format("%.0f", value) : String.valueOf(value);
        // 정수인 경우 소수점을 제거하고 문자열로 변환
      }
      case STRING -> cell.getStringCellValue();
      case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
      default -> "";
    };
  }

  public static void main(String[] args) {
    java.awt.EventQueue.invokeLater(() -> new login().setVisible(true)); // 로그인 창을 띄웁니다
  }
}
