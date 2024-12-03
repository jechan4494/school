package SIS.loginFunction;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.io.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SignUpPage extends JFrame {
  private JTextField userIDField;
  private JComboBox<String> roleComboBox;
  private JTextField studentNameField, ssnField, studentIDField;
  private JComboBox<String> departmentComboBox;

  public SignUpPage() { // 회원가입 페이지
    setTitle("회원가입");
    setSize(500, 500); // 창 크기 조정
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null); // 화면 중앙에 위치
    initComponents();
  }

  private void initComponents() {
    JPanel mainPanel = new JPanel();
    mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    mainPanel.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 10, 10, 10); // 컴포넌트 간 여백 설정
    gbc.fill = GridBagConstraints.HORIZONTAL;

    JLabel titleLabel = new JLabel("회원가입", SwingConstants.CENTER);
    titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 24));
    titleLabel.setForeground(new Color(35, 133, 232));
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 2;
    mainPanel.add(titleLabel, gbc);

    // 이름 필드
    JLabel studentNameLabel = new JLabel("이름:");
    studentNameLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridwidth = 1;
    mainPanel.add(studentNameLabel, gbc);

    studentNameField = new JTextField();
    gbc.gridx = 1;
    mainPanel.add(studentNameField, gbc);

    // 아이디 필드
    JLabel userIDLabel = new JLabel("아이디:");
    userIDLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
    gbc.gridx = 0;
    gbc.gridy = 2;
    mainPanel.add(userIDLabel, gbc);

    userIDField = new JTextField();
    gbc.gridx = 1;
    mainPanel.add(userIDField, gbc);

    // 직업 필드
    JLabel roleLabel = new JLabel("직업:");
    roleLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
    gbc.gridx = 0;
    gbc.gridy = 3;
    mainPanel.add(roleLabel, gbc);

    String[] roles = {"Professor", "Student", "Admin","AdminUser"};
    roleComboBox = new JComboBox<>(roles);
    gbc.gridx = 1;
    mainPanel.add(roleComboBox, gbc);

    // 학번 / 교수번호 필드
    JLabel studentIDLabel = new JLabel("학번 / 교수번호:");
    studentIDLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
    gbc.gridx = 0;
    gbc.gridy = 4;
    mainPanel.add(studentIDLabel, gbc);

    studentIDField = new JTextField();
    gbc.gridx = 1;
    mainPanel.add(studentIDField, gbc);

    // 학과 필드
    JLabel departmentLabel = new JLabel("학과:");
    String[] departments = {"전산학과", "전자공학과", "화학공학과", "기계공학과", "항공우주공학과", "관리자"};
    departmentComboBox = new JComboBox<>(departments);
    gbc.gridx = 0;
    gbc.gridy = 5;
    mainPanel.add(departmentLabel, gbc);

    gbc.gridx = 1;
    mainPanel.add(departmentComboBox, gbc);

    // 주민등록번호 필드
    JLabel ssnLabel = new JLabel("주민등록번호:");
    ssnField = new JTextField();
    gbc.gridx = 0;
    gbc.gridy = 6;
    mainPanel.add(ssnLabel, gbc);

    gbc.gridx = 1;
    mainPanel.add(ssnField, gbc);

    // 회원가입 버튼
    JButton signUpButton = new JButton("회원가입");
    signUpButton.setBackground(new Color(35, 133, 232));
    signUpButton.setForeground(Color.WHITE);
    signUpButton.setFont(new Font("맑은 고딕", Font.BOLD, 14));
    signUpButton.addActionListener(e -> handleSignUp());
    gbc.gridx = 0;
    gbc.gridy = 7;
    gbc.gridwidth = 2;
    gbc.fill = GridBagConstraints.CENTER;
    mainPanel.add(signUpButton, gbc);

    // 로그인 페이지로 돌아가기 버튼
    JButton goToLoginButton = new JButton("로그인 페이지로 돌아가기");
    goToLoginButton.setBackground(new Color(211, 80, 3));
    goToLoginButton.setForeground(Color.WHITE);
    goToLoginButton.setFont(new Font("맑은 고딕", Font.BOLD, 14));
    goToLoginButton.addActionListener(e -> openLoginPage()); // 로그인 페이지로 돌아가기
    gbc.gridx = 0;
    gbc.gridy = 8;
    gbc.gridwidth = 2;
    mainPanel.add(goToLoginButton, gbc);

    add(mainPanel);
  }

  private void handleSignUp() {
    String userID = userIDField.getText().trim();
    String role = (String) roleComboBox.getSelectedItem();
    String name = studentNameField.getText().trim();
    String department = (String) departmentComboBox.getSelectedItem();
    String ssn = ssnField.getText().trim();  // 주민등록번호
    String studentID = studentIDField.getText().trim();

    // 아이디 규칙 체크
    if (!userID.matches(getIdPattern(role))) {
      JOptionPane.showMessageDialog(this, "아이디는 " + getRolePrefix(role) + "로 시작해야 합니다.");
      return;
    }

    // 주민등록번호 유효성 체크 (13자리 숫자)
    if (ssn.length() != 13 || !ssn.matches("\\d{13}")) {
      JOptionPane.showMessageDialog(this, "주민등록번호는 13자리 숫자여야 합니다.");
      return;
    }

    // 초기 비밀번호는 주민등록번호 뒷자리 7자리로 설정
    String initialPassword = ssn.substring(6); // 주민등록번호 뒷자리 7자리

    try {
      String userFile = "user.xlsx";

      // 엑셀 파일이 없는 경우 새로 생성
      File file = new File(userFile);
      if (!file.exists()) {
        try (Workbook workbook = new XSSFWorkbook(); FileOutputStream fos = new FileOutputStream(file)) {
          Sheet sheet = workbook.createSheet("Users");
          createHeader(sheet);
          workbook.write(fos);
        }
      }

      // 엑셀 파일 열기
      try (FileInputStream fis = new FileInputStream(userFile);
           Workbook workbook = new XSSFWorkbook(fis);
           FileOutputStream fos = new FileOutputStream(userFile)) {

        Sheet sheet = workbook.getSheet("Users");
        if (sheet == null) {
          sheet = workbook.createSheet("Users");
          createHeader(sheet);
        }

        // 새 행을 추가
        int lastRowNum = sheet.getPhysicalNumberOfRows();
        Row newRow = sheet.createRow(lastRowNum);

        newRow.createCell(0).setCellValue(name);  // 이름
        newRow.createCell(1).setCellValue(userID);  // 아이디
        newRow.createCell(2).setCellValue(initialPassword);  // 초기 비밀번호
        newRow.createCell(3).setCellValue(role);  // 직업
        newRow.createCell(4).setCellValue(ssn);  // 주민번호
        newRow.createCell(5).setCellValue(department);  // 학과
        newRow.createCell(6).setCellValue(studentID);  // 학번/교수번호

        workbook.write(fos);
      }

      JOptionPane.showMessageDialog(this, "회원가입이 완료되었습니다. 초기 비밀번호는 주민등록번호 뒷자리입니다.");
      clearFields();
      openLoginPage();

    } catch (IOException e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(this, "데이터 저장 중 오류가 발생했습니다.");
    }
  }


  private String getIdPattern(String role) {
    switch (role) {
      case "Professor":
        return "^P\\d{3}$";
      case "Student":
        return "^S\\d{3}$";
      case "Admin":
        return "^H\\d{3}$";
      case "AdminUser":
        return "^G\\d{3}$";
      default:
        return "";
    }
  }

  private String getRolePrefix(String role) {
    switch (role) {
      case "Professor":
        return "P";
      case "Student":
        return "S";
      case "Admin":
        return "H";
        case "AdminUser":
          return "G";
      default:
        return "";
    }
  }

  private void createHeader(Sheet sheet) {
    Row headerRow = sheet.createRow(0);
    headerRow.createCell(0).setCellValue("이름");
    headerRow.createCell(1).setCellValue("아이디");
    headerRow.createCell(2).setCellValue("비밀번호");
    headerRow.createCell(3).setCellValue("직업");
    headerRow.createCell(4).setCellValue("주민번호");
    headerRow.createCell(5).setCellValue("학과");
    headerRow.createCell(6).setCellValue("학번/교수번호");
  }

  private void openLoginPage() {
    this.dispose();
    new login().setVisible(true);
  }

  private void clearFields() {
    userIDField.setText("");
    studentNameField.setText("");
    studentIDField.setText("");
    ssnField.setText("");
    departmentComboBox.setSelectedIndex(0);
    roleComboBox.setSelectedIndex(0);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new SignUpPage().setVisible(true));
  }
}
