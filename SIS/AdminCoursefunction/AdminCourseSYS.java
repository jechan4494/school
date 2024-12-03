package SIS.AdminCoursefunction;

import SIS.Professorfunction.Professor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AdminCourseSYS { // 수강관리 추가 시스템

  private static final String COURSE_FILE = "Courses.xlsx";
  private static final String USER_FILE = "user.xlsx";
  private AdminCourseList courseListPage;  // AdminCourseList 객체를 필드로 추가

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      try {
        new AdminCourseSYS().createGUI(); // 과목 관리 GUI 실행
      } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "파일 읽기 오류: " + e.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
      }
    });
  }

  // GUI 생성
  public void createGUI() throws IOException {
    JFrame frame = new JFrame("강의 추가 시스템");
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    JPanel mainPanel = new JPanel(new GridBagLayout());
    mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // 여백 추가
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5); // 컴포넌트 간 간격 설정
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 1.0;

    // 컴포넌트 추가
    JLabel courseIdLabel = new JLabel("강좌 번호:");
    JTextField courseIdField = new JTextField();

    JLabel nameLabel = new JLabel("강의 이름:");
    JTextField nameField = new JTextField();

    JLabel departmentLabel = new JLabel("담당 학과:");
    JComboBox<String> departmentBox = new JComboBox<>(new String[]{"전산학과", "전자공학과", "화학공학과", "기계공학과", "항공우주공학과"}); // 예시 학과

    JLabel creditLabel = new JLabel("학점 (1~3):");
    JComboBox<Integer> creditBox = new JComboBox<>(new Integer[]{1, 2, 3});

    JLabel maxStudentsLabel = new JLabel("최대 학생 수:");
    JTextField maxStudentsField = new JTextField();

    JLabel priceLabel = new JLabel("강의 가격:");
    JTextField priceField = new JTextField();

    JLabel professorLabel = new JLabel("담당 교수:");
    JComboBox<String> professorBox = new JComboBox<>();
    List<Professor> professors = loadProfessorsFromExcel(USER_FILE);
    for (Professor professor : professors) {
      professorBox.addItem(professor.getName());
    }

    JLabel descriptionLabel = new JLabel("강의 설명:");
    JTextArea descriptionArea = new JTextArea(3, 20);
    descriptionArea.setLineWrap(true);
    descriptionArea.setWrapStyleWord(true);
    JScrollPane descriptionScroll = new JScrollPane(descriptionArea);

    // 강의 추가 버튼
    JButton saveButton = new JButton("저장");
    saveButton.setBackground(new Color(60, 179, 113)); // 저장 버튼 색상
    saveButton.setForeground(Color.WHITE);
    saveButton.addActionListener(e -> {
      String courseId = courseIdField.getText().trim();
      String courseName = nameField.getText().trim();
      String department = (String) departmentBox.getSelectedItem();
      int credits = (int) creditBox.getSelectedItem();
      int maxStudents;
      double price;
      String description = descriptionArea.getText().trim();

      try {
        maxStudents = Integer.parseInt(maxStudentsField.getText().trim());
        price = Double.parseDouble(priceField.getText().trim());
      } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(frame, "숫자 입력 오류", "오류", JOptionPane.ERROR_MESSAGE);
        return;
      }

      String professorName = (String) professorBox.getSelectedItem();
      if (courseId.isEmpty() || courseName.isEmpty() || department == null || professorName == null || description.isEmpty()) {
        JOptionPane.showMessageDialog(frame, "모든 필드를 입력하세요", "오류", JOptionPane.ERROR_MESSAGE);
        return;
      }

      try {
        saveCourseToExcel(COURSE_FILE, courseId, courseName, department, credits, price, professorName,maxStudents, description);
        JOptionPane.showMessageDialog(frame, "강의가 저장되었습니다!", "성공", JOptionPane.INFORMATION_MESSAGE);
        courseIdField.setText("");
        nameField.setText("");
        maxStudentsField.setText("");
        priceField.setText("");
        descriptionArea.setText("");

        if (courseListPage != null) {
          courseListPage.loadCoursesFromExcel();  // 강의 리스트 새로고침
        }
      } catch (IOException ex) {
        JOptionPane.showMessageDialog(frame, "파일 저장 오류: " + ex.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
      }
    });

    // 뒤로가기 버튼
    JButton backButton = new JButton("뒤로가기");
    backButton.setBackground(new Color(220, 20, 60)); // 뒤로가기 버튼 색상
    backButton.setForeground(Color.WHITE);
    backButton.addActionListener(e -> {
      frame.dispose(); // 현재 창 닫기
      if (courseListPage != null) {
        courseListPage.setVisible(true);  // AdminCourseList 창 보이게 함
      }
    });

    // 레이아웃에 컴포넌트 추가
    gbc.gridx = 0;
    gbc.gridy = 0;
    mainPanel.add(courseIdLabel, gbc);
    gbc.gridx = 1;
    mainPanel.add(courseIdField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;
    mainPanel.add(nameLabel, gbc);
    gbc.gridx = 1;
    mainPanel.add(nameField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 2;
    mainPanel.add(departmentLabel, gbc);
    gbc.gridx = 1;
    mainPanel.add(departmentBox, gbc);

    gbc.gridx = 0;
    gbc.gridy = 3;
    mainPanel.add(creditLabel, gbc);
    gbc.gridx = 1;
    mainPanel.add(creditBox, gbc);

    gbc.gridx = 0;
    gbc.gridy = 4;
    mainPanel.add(maxStudentsLabel, gbc);
    gbc.gridx = 1;
    mainPanel.add(maxStudentsField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 5;
    mainPanel.add(priceLabel, gbc);
    gbc.gridx = 1;
    mainPanel.add(priceField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 6;
    mainPanel.add(professorLabel, gbc);
    gbc.gridx = 1;
    mainPanel.add(professorBox, gbc);

    gbc.gridx = 0;
    gbc.gridy = 7;
    mainPanel.add(descriptionLabel, gbc);
    gbc.gridx = 1;
    mainPanel.add(descriptionScroll, gbc);

    gbc.gridx = 0;
    gbc.gridy = 8;
    gbc.gridwidth = 2;
    gbc.anchor = GridBagConstraints.CENTER;
    mainPanel.add(saveButton, gbc);

    gbc.gridx = 0;
    gbc.gridy = 9;
    gbc.gridwidth = 2;
    gbc.anchor = GridBagConstraints.CENTER;
    mainPanel.add(backButton, gbc);  // 뒤로가기 버튼 추가

    frame.add(mainPanel);
    frame.pack(); // 크기 자동 조정
    frame.setLocationRelativeTo(null); // 화면 중앙에 표시
    frame.setVisible(true);
  }

  // 강의 정보를 엑셀 파일에 저장
  public void saveCourseToExcel(String fileName, String courseId, String courseName, String department, int credits, double price, String professorName, int maxStudents,  String description) throws IOException {
    File file = new File(fileName);
    Workbook workbook;
    Sheet sheet;

    if (file.exists()) {
      try (FileInputStream fis = new FileInputStream(file)) {
        workbook = new XSSFWorkbook(fis);
      }
      sheet = workbook.getSheet("Courses");
      if (sheet == null) {
        sheet = workbook.createSheet("Courses");
      }
    } else {
      workbook = new XSSFWorkbook();
      sheet = workbook.createSheet("Courses");
      // 헤더 작성
      Row header = sheet.createRow(0);
      header.createCell(0).setCellValue("강좌 번호");
      header.createCell(1).setCellValue("강의 이름");
      header.createCell(2).setCellValue("담당 학과");
      header.createCell(3).setCellValue("학점");
      header.createCell(4).setCellValue("강의 가격");
      header.createCell(5).setCellValue("담당 교수");
      header.createCell(6).setCellValue("최대 학생 수");
      header.createCell(7).setCellValue("강의 설명");
    }

    // 데이터 추가
    int rowCount = sheet.getLastRowNum() + 1;
    Row row = sheet.createRow(rowCount);
    row.createCell(0).setCellValue(courseId);
    row.createCell(1).setCellValue(courseName);
    row.createCell(2).setCellValue(department);
    row.createCell(3).setCellValue(credits);
    row.createCell(4).setCellValue(price);
    row.createCell(5).setCellValue(professorName);
    row.createCell(6).setCellValue(maxStudents);
    row.createCell(7).setCellValue(description);

    // 파일 저장
    try (FileOutputStream fos = new FileOutputStream(file)) {
      workbook.write(fos);
    }
    workbook.close();
  }

  // 교수 데이터를 엑셀에서 로드
  public List<Professor> loadProfessorsFromExcel(String fileName) throws IOException {
    List<Professor> professors = new ArrayList<>();
    try (FileInputStream fis = new FileInputStream(fileName);
         Workbook workbook = new XSSFWorkbook(fis)) {

      Sheet sheet = workbook.getSheet("Users");
      if (sheet == null) {
        throw new IOException("Users 시트를 찾을 수 없습니다.");
      }

      for (Row row : sheet) {
        if (row.getRowNum() == 0) continue; // 헤더 스킵

        String role = getCellValue(row, 3);  // 직업을 체크하여 교수만 추가
        if ("Professor".equalsIgnoreCase(role)) {
          String name = getCellValue(row, 0);  // 이름
          String userID = getCellValue(row, 1);  // 아이디
          String password = getCellValue(row, 2);  // 비밀번호
          String department = getCellValue(row, 5);  // 학과
          String professorID = getCellValue(row, 6);  // 교수 번호

          professors.add(new Professor(userID, password, "Professor", name, department, professorID, ""));  // 교수 추가
        }
      }
    }
    return professors;
  }

  // 셀 값 가져오기
  private static String getCellValue(Row row, int cellIndex) {
    Cell cell = row.getCell(cellIndex);
    if (cell == null) {
      return ""; // 빈 셀 처리
    }
    return cell.toString(); // 셀 값이 있으면 해당 값을 반환
  }
}
