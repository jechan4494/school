package SIS.Professorfunction;

import SIS.loginFunction.login;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.Font;
import java.io.*;

public class ProfessorGradePage extends JFrame {
  private static final String COURSE_FILE = "Courses.xlsx";
  private DefaultTableModel courseTableModel;
  private JTable courseTable;
  private Professor professor;

  public ProfessorGradePage(Professor professor) { // 교수 메인 페이지
    this.professor = professor; // 로그인한 교수 객체 전달
    setTitle(professor.getName() + " 교수님의 강의 관리");
    setSize(800, 500);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    initComponents();
  }

  private void initComponents() {
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());

    // 상단에 교수 정보 표시
    JPanel professorInfoPanel = new JPanel();
    professorInfoPanel.setLayout(new GridLayout(3, 1)); // 3줄로 설정
    JLabel professorNameLabel = new JLabel("교수 이름: " + professor.getName(), SwingConstants.CENTER);
    JLabel professorIdLabel = new JLabel("교수 번호: " + professor.getProfessorID(), SwingConstants.CENTER); // 교수 ID 표시
    professorNameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
    professorIdLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
    professorInfoPanel.add(professorNameLabel);
    professorInfoPanel.add(professorIdLabel);

    // 로그아웃 버튼 추가
    JButton logoutButton = new JButton("로그아웃");
    logoutButton.addActionListener(e -> logout());

    professorInfoPanel.add(logoutButton);

    // 강의 목록 테이블
    courseTable = new JTable();
    courseTableModel = new DefaultTableModel(new Object[][]{}, new String[]{
      "강좌 번호", "강의 이름", "담당 학과", "학점", "강의 가격", "최대 인원", "강의 소개"
    });
    courseTable.setModel(courseTableModel);
    JScrollPane courseScrollPane = new JScrollPane(courseTable);

    // 강의 선택 버튼
    JButton viewStudentsButton = new JButton("학생 목록 조회");
    viewStudentsButton.addActionListener(e -> viewStudentsInCourse());

    // 버튼 패널
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(viewStudentsButton);

    // 메인 패널 구성
    mainPanel.add(professorInfoPanel, BorderLayout.NORTH);
    mainPanel.add(courseScrollPane, BorderLayout.CENTER);
    mainPanel.add(buttonPanel, BorderLayout.SOUTH);

    add(mainPanel);

    loadCoursesForProfessor();  // 교수의 강의 목록 로드
    setVisible(true);
  }

  private void loadCoursesForProfessor() {
    try (FileInputStream fis = new FileInputStream(COURSE_FILE);
         Workbook workbook = new XSSFWorkbook(fis)) {

      Sheet sheet = workbook.getSheetAt(0); // 첫 번째 시트 사용
      if (sheet == null) {
        JOptionPane.showMessageDialog(this, "Courses 시트를 찾을 수 없습니다.");
        return;
      }

      courseTableModel.setRowCount(0);  // 기존 테이블 초기화

      for (Row row : sheet) {
        if (row.getRowNum() == 0) continue; // 헤더 스킵

        String professorNameInCourse = getCellValue(row, 5);  // 담당 교수 이름
        if (professorNameInCourse.equals(professor.getName())) {  // 교수 이름이 일치하는 강의만 표시
          String courseId = getCellValue(row, 0);         // 강좌 번호
          String courseName = getCellValue(row, 1);       // 강의 이름
          String department = getCellValue(row, 2);       // 담당 학과
          String credits = getCellValue(row, 3);          // 학점
          String price = getCellValue(row, 4);            // 강의 가격
          String maxEnrollment = getCellValue(row, 6);    // 최대 인원
          String description = getCellValue(row, 7);      // 강의 소개

          courseTableModel.addRow(new Object[]{
            courseId, courseName, department, credits, price, maxEnrollment, description
          });
        }
      }
    } catch (IOException e) {
      JOptionPane.showMessageDialog(this, "Course 파일 읽기 오류: " + e.getMessage());
    }
  }

  private void viewStudentsInCourse() {
    int selectedRow = courseTable.getSelectedRow();
    if (selectedRow == -1) {
      JOptionPane.showMessageDialog(this, "강의를 선택하세요.");
      return;
    }

    // 강의 이름 가져오기 (강의 이름은 1번 열)
    String courseName = (String) courseTable.getValueAt(selectedRow, 1);
    System.out.println("Selected Course Name: " + courseName); // 디버깅 출력

    // GradeEntryPage로 강의 이름 전달
    new GradeEntryPage(professor, courseName);
    this.dispose();
  }

  private void logout() {
    new login().setVisible(true); // Login 페이지로 돌아감
    this.dispose(); // 현재 교수 강의 관리 창 닫기
  }

  private static String getCellValue(Row row, int cellIndex) {
    Cell cell = row.getCell(cellIndex);
    return cell != null ? cell.toString().trim() : ""; // trim() 추가
  }

  public static void main(String[] args) {
    new ProfessorGradePage(new Professor("USERid", "password", "Professor", "이름", "기본 강의", "학과", "주민번호"));
  }
}
