package SIS.Professorfunction;

import SIS.loginFunction.login;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.Iterator;

public class GradeEntryPage extends JFrame {
  private static final String STUDENT_FILE = "Students.xlsx";
  private static final String USER_FILE = "user.xlsx"; // 사용자 엑셀 파일 추가
  private DefaultTableModel studentTableModel;
  private JTable studentTable;
  private Professor professor;
  private String currentCourseName;

  public GradeEntryPage(Professor professor, String courseName) { // 교수 수강관리 페이지
    this.professor = professor;
    this.currentCourseName = courseName;
    setTitle(professor.getName() + " 교수님의 성적 입력");
    setSize(800, 500);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    initComponents();
  }

  private void initComponents() {
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());

    // 학생 목록 테이블
    studentTable = new JTable();
    studentTableModel = new DefaultTableModel(new Object[][]{}, new String[]{
      "학생 이름", "신청 강의", "취득 학점", "학번"
    });
    studentTable.setModel(studentTableModel);
    JScrollPane studentScrollPane = new JScrollPane(studentTable);

    // 학점 입력 버튼
    JButton gradeButton = new JButton("학점 주기");
    gradeButton.addActionListener(e -> enterGradeForStudents());

    // 뒤로가기 버튼
    JButton backButton = new JButton("뒤로가기");
    backButton.addActionListener(e -> goBackToProfessorPage());

    // 로그아웃 버튼
    JButton logoutButton = new JButton("로그아웃");
    logoutButton.addActionListener(e -> logout());

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(gradeButton);
    buttonPanel.add(backButton);
    buttonPanel.add(logoutButton);

    mainPanel.add(studentScrollPane, BorderLayout.CENTER);
    mainPanel.add(buttonPanel, BorderLayout.SOUTH);

    add(mainPanel);

    loadStudentsForCourse();
    setVisible(true);
  }

  // 학생 목록 로드
  private void loadStudentsForCourse() {
    try (FileInputStream fisStudent = new FileInputStream(STUDENT_FILE);
         Workbook workbookStudent = new XSSFWorkbook(fisStudent);
         FileInputStream fisUser = new FileInputStream(USER_FILE);
         Workbook workbookUser = new XSSFWorkbook(fisUser)) {

      Sheet studentSheet = workbookStudent.getSheetAt(0); // 첫 번째 시트 사용
      Sheet userSheet = workbookUser.getSheetAt(0); // 사용자 정보 시트

      if (studentSheet == null || userSheet == null) {
        JOptionPane.showMessageDialog(this, "시트를 찾을 수 없습니다.");
        return;
      }

      studentTableModel.setRowCount(0);  // 기존 테이블 초기화
      boolean studentFound = false;

      // 사용자 시트에서 학생 정보 로드
      Iterator<Row> userRowIterator = userSheet.iterator();
      while (userRowIterator.hasNext()) {
        Row userRow = userRowIterator.next();
        String userName = getCellValue(userRow, 0); // 이름
        String userRole = getCellValue(userRow, 3); // 직업 (학생일 경우 'Student')

        if ("Student".equals(userRole)) {  // 직업이 학생인 경우
          String studentID = getCellValue(userRow, 6); // 학번/교수번호

          // 학생 시트에서 해당 학생의 정보 찾기
          Iterator<Row> studentRowIterator = studentSheet.iterator();
          while (studentRowIterator.hasNext()) {
            Row studentRow = studentRowIterator.next();
            String studentName = getCellValue(studentRow, 0);  // 이름
            String enrolledCourse = getCellValue(studentRow, 3);  // 신청 강의

            // 이름과 강의명이 정확히 일치하는지 확인 (trim()과 equalsIgnoreCase() 사용)
            if (studentName.trim().equalsIgnoreCase(userName.trim()) && enrolledCourse.equalsIgnoreCase(currentCourseName.trim())) {
              String currentGrade = getCellValue(studentRow, 4);  // 취득 학점
              studentTableModel.addRow(new Object[]{
                studentName, enrolledCourse,
                currentGrade.isEmpty() ? "미입력" : currentGrade, studentID
              });

              studentFound = true;
              break;
            }
          }
        }
      }

      if (!studentFound) {
        JOptionPane.showMessageDialog(this, "이 강의를 수강한 학생이 없습니다.");
      }
    } catch (IOException e) {
      JOptionPane.showMessageDialog(this, "엑셀 파일 읽기 오류: " + e.getMessage());
    }
  }

  // 학점 입력
  private void enterGradeForStudents() {
    int selectedRow = studentTable.getSelectedRow();
    if (selectedRow == -1) {
      JOptionPane.showMessageDialog(this, "학생을 선택하세요.");
      return;
    }

    String studentName = (String) studentTable.getValueAt(selectedRow, 0);
    String[] options = {"A", "B", "C", "D", "F"};
    String grade = (String) JOptionPane.showInputDialog(this,
      "학생 이름: " + studentName + "\n성적을 선택하세요:",
      "성적 입력",
      JOptionPane.QUESTION_MESSAGE,
      null,
      options,
      options[0]);

    if (grade != null) {
      saveGradeToExcel(studentName, grade);
      loadStudentsForCourse(); // 테이블 새로고침
    }
  }

  // 학점 저장
  private void saveGradeToExcel(String studentName, String grade) {
    try (FileInputStream fis = new FileInputStream(STUDENT_FILE);
         Workbook workbook = new XSSFWorkbook(fis)) {

      Sheet sheet = workbook.getSheetAt(0);
      if (sheet == null) {
        JOptionPane.showMessageDialog(this, "시트를 찾을 수 없습니다.");
        return;
      }

      boolean found = false;
      for (Row row : sheet) {
        if (row.getRowNum() == 0) continue; // 헤더 스킵

        String currentStudentName = getCellValue(row, 0);
        String enrolledCourse = getCellValue(row, 3);

        if (currentStudentName.equals(studentName) && enrolledCourse.equals(currentCourseName)) {
          Cell gradeCell = row.getCell(4); // "취득 학점" 열
          if (gradeCell == null) {
            gradeCell = row.createCell(4);
          }
          gradeCell.setCellValue(grade);

          found = true;
          break;
        }
      }

      if (!found) {
        JOptionPane.showMessageDialog(this, "해당 학생의 수강 정보를 찾을 수 없습니다.");
        return;
      }

      try (FileOutputStream fos = new FileOutputStream(STUDENT_FILE)) {
        workbook.write(fos);
        JOptionPane.showMessageDialog(this, "성적이 성공적으로 저장되었습니다.");
      }

    } catch (IOException e) {
      JOptionPane.showMessageDialog(this, "성적 저장 중 오류 발생: " + e.getMessage());
    }
  }

  // 셀 값 가져오기
  private static String getCellValue(Row row, int cellIndex) {
    Cell cell = row.getCell(cellIndex);
    return cell != null ? cell.toString() : "";
  }

  // 뒤로가기
  private void goBackToProfessorPage() {
    new ProfessorGradePage(professor);
    this.dispose();
  }

  // 로그아웃
  private void logout() {
    new login().setVisible(true); // Login 페이지로 돌아감
    this.dispose(); // 현재 강의 관리 창 닫기
  }

  public static void main(String[] args) {
    new GradeEntryPage(new Professor("prof123", "password", "Professor", "Prof. Kim", "Computer Science", "12345", "123-45-6789"), "테스트");
  }
}
