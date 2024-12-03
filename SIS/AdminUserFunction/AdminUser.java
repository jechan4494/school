package SIS.AdminUserFunction;

import SIS.AdminCoursefunction.AdminCourse;
import SIS.Professorfunction.Professor;
import SIS.loginFunction.StudentRol;
import SIS.loginFunction.User;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdminUser extends User {
  private String adminUserID;
  private String ssn;
  private String adminUserName;

  public AdminUser(String userID, String password, String role, String name, String ssn, String department) {
    super(userID, password, role, name, ssn);
    this.adminUserID = userID;
    this.ssn = ssn;
    this.adminUserName = name;
  }

  public String getAdminUserID() {
    return adminUserID;
  }

  public String getSsn() {
    return ssn;
  }

  public String getName() {
    return adminUserName;
  }

  // 엑셀 파일에 사용자 추가
  public static void addUserToExcel(String fileName, User newUser) throws IOException {
    File file = new File(fileName);
    Workbook workbook;
    Sheet userSheet;

    // 파일이 없으면 새로 생성하고 헤더 추가
    if (!file.exists()) {
      workbook = new XSSFWorkbook();
      userSheet = workbook.createSheet("Users");

      // 헤더 추가
      Row headerRow = userSheet.createRow(0);
      headerRow.createCell(0).setCellValue("이름");
      headerRow.createCell(1).setCellValue("아이디");
      headerRow.createCell(2).setCellValue("비밀번호");
      headerRow.createCell(3).setCellValue("직업");
      headerRow.createCell(4).setCellValue("주민번호");
      headerRow.createCell(5).setCellValue("학과");
      headerRow.createCell(6).setCellValue("학번/교수번호");
    } else {
      try (FileInputStream fis = new FileInputStream(fileName)) {
        workbook = new XSSFWorkbook(fis);
        userSheet = workbook.getSheetAt(0);
      }
    }

    // 새 행을 추가
    int rowIndex = userSheet.getPhysicalNumberOfRows();
    Row newRow = userSheet.createRow(rowIndex);

    // 사용자 데이터를 행에 추가
    newRow.createCell(0).setCellValue(newUser.getName());  // 이름
    newRow.createCell(1).setCellValue(newUser.getUserID());  // 아이디
    newRow.createCell(2).setCellValue(newUser.getPassword());  // 비밀번호
    newRow.createCell(3).setCellValue(newUser.getRole());  // 역할

    // 역할에 맞는 데이터 추가
    if ("Student".equalsIgnoreCase(newUser.getRole())) {
      StudentRol student = (StudentRol) newUser;
      newRow.createCell(4).setCellValue(student.getSsn());  // 주민번호
      newRow.createCell(5).setCellValue(student.getDepartment());  // 학과
      newRow.createCell(6).setCellValue(student.getStudentID());  // 학번
    } else if ("Professor".equalsIgnoreCase(newUser.getRole())) {
      Professor professor = (Professor) newUser;
      newRow.createCell(4).setCellValue(professor.getSsn());  // 주민번호
      newRow.createCell(5).setCellValue(professor.getDepartment());  // 학과
      newRow.createCell(6).setCellValue(professor.getProfessorID());  // 교수번호
    } else if ("Admin".equalsIgnoreCase(newUser.getRole())) {
      AdminCourse adminCourse = (AdminCourse) newUser;
      newRow.createCell(4).setCellValue(adminCourse.getSsn());  // 주민번호
      newRow.createCell(5).setCellValue("수강 관리자");  // 관리자 학과
      newRow.createCell(6).setCellValue(adminCourse.getAdminID());  // 관리자 ID
    } else if ("AdminUser".equalsIgnoreCase(newUser.getRole())) {
      AdminUser adminUser = (AdminUser) newUser;
      newRow.createCell(4).setCellValue(adminUser.getSsn());
      newRow.createCell(5).setCellValue("학생 관리자");
      newRow.createCell(6).setCellValue(adminUser.getRole());
    }

    // 엑셀 파일 저장
    try (FileOutputStream fos = new FileOutputStream(fileName)) {
      workbook.write(fos);
    }
  }

  // 엑셀에서 사용자 불러오기
  public static List<User> loadUsersFromExcel(String fileName) throws IOException {
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
        if (row.getRowNum() == 0) continue; // 헤더 건너뜀

        String userID = getCellValue(row, 1);
        String password = getCellValue(row, 2);
        String role = getCellValue(row, 3);
        String name = getCellValue(row, 0);
        String ssn = getCellValue(row, 4);
        String department = getCellValue(row, 5);
        String studentOrProfessorID = getCellValue(row, 6);

        if ("Student".equalsIgnoreCase(role)) {
          users.add(new StudentRol(name, userID, password, role, studentOrProfessorID, department, ssn));
        } else if ("Professor".equalsIgnoreCase(role)) {
          users.add(new Professor(userID, password, role, name, studentOrProfessorID, department, ssn));
        } else if ("Admin".equalsIgnoreCase(role)) {
          users.add(new AdminCourse(userID, password, role, name, studentOrProfessorID, ssn));
        } else if ("AdminUser".equalsIgnoreCase(role)) {
          users.add(new AdminUser(userID, password, role, name, studentOrProfessorID, ssn));
        }
      }
    }
    return users;
  }

  // 수정된 getCellValue 메서드
  private static String getCellValue(Row row, int cellIndex) {
    Cell cell = row.getCell(cellIndex);
    if (cell == null) {
      return "";
    }

    // 셀 타입에 따라 값 읽기
    switch (cell.getCellType()) {
      case STRING:
        return cell.getStringCellValue();
      case NUMERIC:
        return String.valueOf(cell.getNumericCellValue());  // 숫자 타입일 경우 문자열로 변환
      default:
        return "";
    }
  }
}
