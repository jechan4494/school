package SIS.AdminUserFunction;

import SIS.AdminCoursefunction.AdminCourse;
import SIS.Professorfunction.Professor;
import SIS.loginFunction.StudentRol;
import SIS.loginFunction.User;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AddUserPage extends JFrame {

  private JTextField userIDField, userNameField, userSSNField, userProfessorIDField, userStudentIDField;
  private JPasswordField passwordField;
  private JComboBox<String> roleComboBox, departmentComboBox;
  private JButton addUserButton;

  public AddUserPage(List<User> users) { // 관리자로 인해 추가로 들어가는 사용자 페이지 이건 비밀번호 제액 없음
    if (users == null) {
      throw new IllegalArgumentException("사용자 목록은 null일 수 없습니다.");
    }
    setTitle("사용자 추가");
    setSize(400, 450); // 창 크기 설정
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    initComponents(users);
  }

  private void initComponents(List<User> users) {
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(10, 2, 10, 10)); // 10행 2열 레이아웃 설정

    // ID 입력 필드
    JLabel userIDLabel = new JLabel("아이디:");
    userIDField = new JTextField();

    // 비밀번호 입력 필드
    JLabel passwordLabel = new JLabel("비밀번호:");
    passwordField = new JPasswordField();

    // 이름 입력 필드
    JLabel userNameLabel = new JLabel("이름:");
    userNameField = new JTextField();

    // 주민등록번호 입력 필드
    JLabel userSSNLabel = new JLabel("주민등록번호:");
    userSSNField = new JTextField();

    // 교수 번호 입력 필드 (교수일 경우에만 사용)
    JLabel userProfessorIDLabel = new JLabel("교수번호:");
    userProfessorIDField = new JTextField();

    // 학번 입력 필드 (학생일 경우에만 사용)
    JLabel userStudentIDLabel = new JLabel("학번:");
    userStudentIDField = new JTextField();

    // 역할 선택 필드
    JLabel roleLabel = new JLabel("역할:");
    roleComboBox = new JComboBox<>(new String[]{"Student", "Professor", "Admin", "AdminUser"});
    roleComboBox.addActionListener(e -> updateFieldsBasedOnRole());

    // 학과 선택 필드 (학생/교수에게만 해당)
    JLabel departmentLabel = new JLabel("학과:");
    departmentComboBox = new JComboBox<>(new String[]{
      "전산학과", "전자공학과", "화학공학과", "기계공학과", "항공우주공학과"
    });

    // 추가 버튼
    addUserButton = new JButton("사용자 추가");
    addUserButton.addActionListener(e -> addUserAction(users));

    // 컴포넌트들 추가
    panel.add(userIDLabel);
    panel.add(userIDField);
    panel.add(passwordLabel);
    panel.add(passwordField);
    panel.add(userNameLabel);
    panel.add(userNameField);
    panel.add(userSSNLabel);
    panel.add(userSSNField);
    panel.add(roleLabel);
    panel.add(roleComboBox);
    panel.add(departmentLabel);
    panel.add(departmentComboBox);
    panel.add(userProfessorIDLabel);
    panel.add(userProfessorIDField);
    panel.add(userStudentIDLabel);
    panel.add(userStudentIDField);
    panel.add(new JLabel()); // 빈 공간
    panel.add(addUserButton);

    add(panel);

    updateFieldsBasedOnRole(); // 초기 역할에 맞게 필드 업데이트
  }

  // 역할에 맞게 입력 필드를 동적으로 업데이트
  private void updateFieldsBasedOnRole() {
    String role = (String) roleComboBox.getSelectedItem();
    boolean isStudent = "Student".equalsIgnoreCase(role);
    boolean isProfessor = "Professor".equalsIgnoreCase(role);
    boolean isAdmin = "Admin".equalsIgnoreCase(role);
    boolean isAdminUser = "AdminUser".equalsIgnoreCase(role);

    userStudentIDField.setEnabled(isStudent);
    userProfessorIDField.setEnabled(isProfessor);
    departmentComboBox.setEnabled(isStudent || isProfessor); // 학생/교수에게만 학과 선택 가능
  }

  private void addUserAction(List<User> users) {
    String userID = userIDField.getText().trim();
    String password = new String(passwordField.getPassword()).trim();
    String name = userNameField.getText().trim();
    String ssn = userSSNField.getText().trim();
    String professorID = userProfessorIDField.getText().trim();
    String studentID = userStudentIDField.getText().trim();
    String department = (String) departmentComboBox.getSelectedItem();
    String role = (String) roleComboBox.getSelectedItem();

    // 필수 입력 값 체크
    if (userID.isEmpty() || password.isEmpty() || name.isEmpty() || ssn.isEmpty() || role == null) {
      JOptionPane.showMessageDialog(this, "모든 필드를 입력하세요.");
      return;
    }

    // 새 사용자 객체 생성
    User newUser = null;
    if ("Student".equalsIgnoreCase(role)) {
      newUser = new StudentRol(name, userID, password, role, studentID, department, ssn);
    } else if ("Professor".equalsIgnoreCase(role)) {
      newUser = new Professor(userID, password, role, name, professorID, department, ssn);
    } else if ("Admin".equalsIgnoreCase(role)) {
      newUser = new AdminCourse(userID, password, role, name, ssn, department);
    } else if ("AdminUser".equalsIgnoreCase(role)) {
      newUser = new AdminUser(userID, password, role, name, ssn, department);
    }

    // 사용자 목록에 추가
    if (newUser != null) {
      users.add(newUser);
      JOptionPane.showMessageDialog(this, "사용자가 추가되었습니다.");

      // 엑셀에 저장하는 로직
      try {
        addUserToExcel("user.xlsx", newUser); // 엑셀에 사용자 데이터 저장
      } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "사용자 데이터를 엑셀에 저장하는 중 오류가 발생했습니다.");
      }

      dispose(); // 창 닫기
    }
  }

  public static void addUserToExcel(String fileName, User newUser) throws IOException {
    File file = new File(fileName);
    Workbook workbook;
    Sheet sheet;

    // 파일이 존재하면 기존 워크북을 불러옴
    if (file.exists()) {
      try (FileInputStream fis = new FileInputStream(file)) {
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet("Users");
        if (sheet == null) {
          sheet = workbook.createSheet("Users");
          createHeaderRow(sheet); // 헤더가 없으면 추가
        }
      }
    } else {
      workbook = new XSSFWorkbook();
      sheet = workbook.createSheet("Users");
      createHeaderRow(sheet); // 새로 생성할 때 헤더 추가
    }

    // 새로운 사용자 데이터 추가
    int lastRow = sheet.getLastRowNum() + 1;
    Row newRow = sheet.createRow(lastRow);

    // 사용자 정보 작성
    newRow.createCell(0).setCellValue(newUser.getName());         // 이름
    newRow.createCell(1).setCellValue(newUser.getUserID());       // 아이디
    newRow.createCell(2).setCellValue(newUser.getPassword());     // 비밀번호
    newRow.createCell(3).setCellValue(newUser.getRole());         // 직책
    newRow.createCell(4).setCellValue(newUser.getSsn());          // 주민번호

    // 역할별 데이터 작성
    if (newUser instanceof Professor) {
      Professor professor = (Professor) newUser;
      newRow.createCell(5).setCellValue(professor.getDepartment());  // 학과
      newRow.createCell(6).setCellValue(professor.getProfessorID()); // 교수번호
    } else if (newUser instanceof StudentRol) {
      StudentRol student = (StudentRol) newUser;
      newRow.createCell(5).setCellValue(student.getDepartment());  // 학과
      newRow.createCell(6).setCellValue(student.getStudentID());   // 학번
    } else if (newUser instanceof AdminCourse) {
      AdminCourse adminCourse = (AdminCourse) newUser;
      newRow.createCell(5).setCellValue("");  // 학과 없음
      newRow.createCell(6).setCellValue(adminCourse.getAdminID()); // 관리자번호
    }

    // 파일에 저장
    try (FileOutputStream fos = new FileOutputStream(file)) {
      workbook.write(fos);
    }

    workbook.close();
  }

  private static void createHeaderRow(Sheet sheet) {
    Row header = sheet.createRow(0);
    header.createCell(0).setCellValue("이름");
    header.createCell(1).setCellValue("아이디");
    header.createCell(2).setCellValue("비밀번호");
    header.createCell(3).setCellValue("직업");
    header.createCell(4).setCellValue("주민번호");
    header.createCell(5).setCellValue("학과");
    header.createCell(6).setCellValue("번호");
  }

  public static void main(String[] args) {
    List<User> users = new ArrayList<>();
    AddUserPage addUserPage = new AddUserPage(users);
    addUserPage.setVisible(true);
  }
}
