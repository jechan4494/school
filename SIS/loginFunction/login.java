package SIS.loginFunction;

import SIS.AdminCoursefunction.AdminCourse;
import SIS.AdminCoursefunction.AdminCoursePage;
import SIS.AdminUserFunction.AdminUser;
import SIS.AdminUserFunction.AdminUserManagerPage;
import SIS.AdminUserFunction.AdminUserPage;
import SIS.Professorfunction.Professor;
import SIS.Professorfunction.ProfessorGradePage;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

import static SIS.AdminUserFunction.AdminUser.loadUsersFromExcel;

public class login extends javax.swing.JFrame {

  private javax.swing.JTextField jTextField1;
  private javax.swing.JPasswordField jPasswordField1;

  public login() { // 로그인 페이지 여기서 컴파일 하거나 메인에서 컴파일 진행
    initComponents();
    setTitle("로그인");
  }

  private void initComponents() {
    JPanel jPanel1 = new JPanel();
    JLabel jLabel1 = new JLabel();
    JLabel jLabel2 = new JLabel();
    jTextField1 = new javax.swing.JTextField();
    jPasswordField1 = new javax.swing.JPasswordField();
    JButton jButtonLogin = new JButton();
    JButton jButtonSignUp = new JButton(); // 회원가입 버튼 추가
    JLabel jLabel3 = new JLabel();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    jPanel1.setBackground(new java.awt.Color(35, 133, 232));

    jLabel1.setText("아이디: ");
    jLabel2.setText("비밀번호: ");
    jLabel3.setFont(new java.awt.Font("맑은 고딕", 0, 24));
    jLabel3.setText("학사 관리 시스템");

    jButtonLogin.setText("로그인");
    jButtonLogin.addActionListener(this::loginButtonActionPerformed); // 로그인 버튼 이벤트 추가

    jButtonSignUp.setText("회원가입"); // 회원가입 버튼 추가
    jButtonSignUp.addActionListener(this::signUpButtonActionPerformed); // 회원가입 버튼 이벤트 추가

    // Layout 설정
    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPasswordField1, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                                    .addComponent(jTextField1))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addContainerGap(33, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel3)
                                            .addGap(23, 23, 23))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(jButtonSignUp) // 회원가입 버튼 배치
                                            .addGap(18, 18, 18)
                                            .addComponent(jButtonLogin)
                                            .addGap(25, 25, 25)))));

    jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(19, 19, 19)
                            .addComponent(jLabel3)
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButtonLogin)
                                    .addComponent(jButtonSignUp)) // 회원가입 버튼 포함
                            .addContainerGap(17, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addContainerGap())
    );
    layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
    );

    pack();
  }

  private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {
    String userID = jTextField1.getText().trim();
    String password = new String(jPasswordField1.getPassword()).trim();

    try {
      List<User> users = loadUsersFromExcel("user.xlsx");
      User loggedInUser = authenticate(userID, password, users);

      if (loggedInUser != null) {
        System.out.println("Logged in user role: " + loggedInUser.getRole());  // 역할 로그 출력
        JOptionPane.showMessageDialog(this, "로그인 성공! 역할: " + loggedInUser.getRole());
        switch (loggedInUser.getRole().toLowerCase()) {
          case "adminuser":
            openAdminUserPanel(loggedInUser,users);
            break;
          case "admin":
            openAdminPanel(loggedInUser);
            break;
          case "professor":
            openProfessorPanel(loggedInUser);
            break;
          case "student":
            JOptionPane.showMessageDialog(this, "학생으로 로그인되었습니다.");
            break;
          default:
            JOptionPane.showMessageDialog(this, "알 수 없는 역할입니다.");
            break;
        }
      } else {
        JOptionPane.showMessageDialog(this, "로그인 실패: 아이디 또는 비밀번호가 일치하지 않습니다.");
      }
    } catch (IOException e) {
      JOptionPane.showMessageDialog(this, "데이터 로드 중 오류 발생");
      e.printStackTrace();
    }
  }

  // AdminUserManagerPage를 호출하는 메서드
  private void openAdminUserPanel(User loggedInUser, List<User> users) {
    if (loggedInUser instanceof AdminUser adminUser) {
      // AdminUserManagerPage로 adminUser와 사용자 목록(users)을 전달
      new AdminUserManagerPage(adminUser, users).setVisible(true);
      this.dispose();  // 현재 창 닫기
    } else {
      JOptionPane.showMessageDialog(this, "잘못된 역할입니다.");
    }
  }



  private void openProfessorPanel(User loggedInUser) {
    if (loggedInUser instanceof Professor professor) {
      Object courseName;
      new ProfessorGradePage(professor).setVisible(true); // 교수 페이지 띄우기
      this.dispose();  // 현재 로그인 창 닫기
    } else {
      JOptionPane.showMessageDialog(this, "교수 역할이 아닙니다.");
    }
  }

  private User authenticate(String userID, String password, List<User> users) {
    for (User user : users) {
      if (user.getUserID().equals(userID) && user.getPassword().equals(password)) {
        return user;
      }
    }
    return null;  // 해당하는 사용자 없으면 null 반환
  }

  private void signUpButtonActionPerformed(java.awt.event.ActionEvent evt) {
    new SignUpPage().setVisible(true);
    this.dispose();
  }

  private void openAdminPanel(User loggedInUser) {
    this.dispose();
    try {
      List<User> users = loadUsersFromExcel("user.xlsx");

      if (loggedInUser instanceof AdminCourse adminCourse) {
        new AdminCoursePage(adminCourse, users).setVisible(true);
      } else {
        JOptionPane.showMessageDialog(this, "관리자 역할이 아닙니다.");
      }
    } catch (IOException e) {
      JOptionPane.showMessageDialog(this, "관리자 페이지로 이동 중 오류 발생");
      e.printStackTrace();
    }
  }

  public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(() -> new login().setVisible(true));
  }
}
