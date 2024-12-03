package SIS.AdminCoursefunction;

import SIS.AdminUserFunction.AdminUser;
import SIS.loginFunction.User;
import SIS.Professorfunction.Professor;
import SIS.loginFunction.StudentRol;
import SIS.loginFunction.login;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class AdminCoursePage extends javax.swing.JFrame { // 관리자 페이지 사용자정보 테이블 보유

  private final AdminCourse admin;
  private final List<User> users; // 관리자에서 관리할 사용자 목록
  private DefaultTableModel tableModel;

  // AdminPage 생성자에서 Admin과 User 목록을 받아옵니다.
  public AdminCoursePage(AdminCourse adminCourse, List<User> users) {
    this.admin = adminCourse;
    this.users = users;
    setTitle("관리자 페이지");
    initComponents();
    updateTable(); // 사용자 목록을 테이블에 표시
  }

  private void initComponents() {
    JLabel jLabel1 = new JLabel();
    JPanel jPanel1 = new JPanel();
    JButton jButtonCourseSystem = new JButton();
    JButton jButtonChangePassword = new JButton();
    JButton jButtonLogout = new JButton(); // 로그아웃 버튼 추가
    JTextField jTextFieldSearch = new JTextField(15);
    JPanel jSearchPanel = new JPanel();
    JLabel jLabelAdminName = new JLabel();

    JTable jTable1 = new JTable();
    tableModel = new DefaultTableModel(
      new Object[][]{},
      new String[]{"이름", "아이디", "비밀번호", "직책", "학번/교수번호", "주민번호", "학과"}
    );
    jTable1.setModel(tableModel);
    JScrollPane jScrollPane1 = new JScrollPane(jTable1);
    jTable1.setFillsViewportHeight(true);

    jLabelAdminName.setText("관리자: " + admin.getName());
    jLabelAdminName.setFont(new java.awt.Font("맑은 고딕", 0, 20));
    jLabelAdminName.setForeground(Color.BLACK);

    jButtonCourseSystem.setText("과목 관리");
    jButtonChangePassword.setText("암호 변경");
    jButtonLogout.setText("로그아웃");

    // 버튼 클릭 이벤트
    jButtonCourseSystem.addActionListener(evt -> openCourseSystem());
    jButtonChangePassword.addActionListener(evt -> changePassword());
    jButtonLogout.addActionListener(evt -> logout()); // 로그아웃 버튼 이벤트 추가

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createSequentialGroup()
        .addContainerGap(50, Short.MAX_VALUE)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
          .addComponent(jButtonCourseSystem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(jButtonChangePassword, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
          .addComponent(jButtonLogout, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
        .addContainerGap(50, Short.MAX_VALUE)
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createSequentialGroup()
        .addGap(50, 50, 50)
        .addComponent(jButtonCourseSystem)
        .addGap(18, 18, 18)
        .addComponent(jButtonChangePassword)
        .addGap(18, 18, 18)
        .addComponent(jButtonLogout)
        .addContainerGap(50, Short.MAX_VALUE)
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jLabel1)
          .addComponent(jLabelAdminName)
          .addComponent(jSearchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap(14, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jLabel1)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jLabelAdminName)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jSearchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(18, 18, 18)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(14, Short.MAX_VALUE)
    );

    pack();
  }

  // 로그아웃 버튼 동작
  private void logout() {
    new login().setVisible(true); // Login 페이지로 돌아감
    this.dispose(); // 현재 AdminPage 닫기
  }

  // 사용자 목록을 테이블에 표시
  private void updateTable() {
    tableModel.setRowCount(0); // 기존 테이블 데이터 초기화
    for (User user : users) {
      if (user instanceof StudentRol student) {
        tableModel.addRow(new Object[] {
          student.getName(), student.getUserID(), student.getPassword(),
          "학생", student.getStudentID(), student.getSsn(), student.getDepartment()
        });
      } else if (user instanceof Professor professor) {
        tableModel.addRow(new Object[] {
          professor.getName(), professor.getUserID(), professor.getPassword(),
          "교수", professor.getProfessorID(), professor.getSsn(), professor.getDepartment()
        });
      } else if (user instanceof AdminCourse adminUser) {
        tableModel.addRow(new Object[] {
          adminUser.getName(), adminUser.getUserID(), adminUser.getPassword(),
          "수강 관리자", adminUser.getAdminID(), adminUser.getSsn(), "수강 관리자"
        });
      } else if (user instanceof AdminUser adminManager) {
        tableModel.addRow(new Object[] {
          adminManager.getName(), adminManager.getUserID(), adminManager.getPassword(),
          "학사 관리자", adminManager.getAdminUserID(), adminManager.getSsn(), "학사 관리자"
        });
      }
    }
  }

  private void openCourseSystem() {
    new AdminCourseList(this); // AdminPage 객체를 전달하여 AdminCourseList 열기
    this.dispose();
  }

  private void changePassword() {
    String userID = JOptionPane.showInputDialog(this, "암호를 변경할 사용자 ID를 입력하세요:");
    if (userID == null || userID.trim().isEmpty()) {
      JOptionPane.showMessageDialog(this, "사용자 ID를 입력하지 않았습니다.");
      return;
    }

    String newPassword = JOptionPane.showInputDialog(this, "새로운 암호를 입력하세요:");
    if (newPassword == null || newPassword.trim().isEmpty()) {
      JOptionPane.showMessageDialog(this, "새로운 암호를 입력하지 않았습니다.");
      return;
    }

    boolean userFound = false;

    // 사용자 목록에서 암호 변경
    for (User user : users) {
      if (user.getUserID() != null && user.getUserID().trim().equalsIgnoreCase(userID.trim())) {
        user.setPassword(newPassword);
        userFound = true;
        break;
      }
    }

    if (!userFound) {
      JOptionPane.showMessageDialog(this, "해당 ID를 가진 사용자를 찾을 수 없습니다.");
      return;
    }

    JOptionPane.showMessageDialog(this, "암호가 성공적으로 변경되었습니다.");
  }
}
