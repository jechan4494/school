package SIS.Studentfunction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.util.List;

import SIS.Studentfunction.Student;
import SIS.Studentfunction.StudentPage;
import SIS.loginFunction.User;
import SIS.loginFunction.login;

public class StudentPage extends javax.swing.JFrame {

    private final Student student;

    // 생성자에서 student 객체를 받아옵니다.
    public StudentPage(Student student) {
        this.student = student;
        setTitle("학생 페이지");  // 타이틀 추가
        initComponents();
    }

    private void initComponents() {
        JLabel jLabel1 = new JLabel();
        JPanel jPanel1 = new JPanel();
        JButton jButtonCourseList = new JButton(); // 수강관리 버튼 추가
        JButton jButtonCourseRequest = new JButton(); // 수강신청 버튼 추가
        JButton jButtonChangePassword = new JButton(); // 암호 변경 버튼 추가
        JButton jButtonStudentBill = new JButton(); // 청구서 발급 버튼 추가
        JButton jButtonLogout = new JButton(); // 로그아웃 버튼 추가
        JLabel jLabelStudentName = new JLabel();

        jLabel1.setText("학생 페이지");
        // 학생 이름
        jLabelStudentName.setText("학생: " + student.getStudentName());
        jLabelStudentName.setFont(new java.awt.Font("맑은 고딕", 0, 20));
        jLabelStudentName.setForeground(Color.BLACK);

        // 버튼 설정
        jButtonCourseList.setText("수강 관리"); // 수강 관리 버튼 추가
        jButtonCourseRequest.setText("수강 신청"); // 수강 신청 버튼 추가
        jButtonChangePassword.setText("암호 변경"); // 암호 변경 버튼 추가
        jButtonStudentBill.setText("청구서 발급"); // 청구서 발급 버튼 추가
        jButtonLogout.setText("로그아웃"); // 로그아웃 버튼 텍스트 설정

        // 버튼 클릭 시 처리
        jButtonCourseList.addActionListener(evt -> openCourseList());
        jButtonCourseRequest.addActionListener(evt -> openCourseRequest()); // 과목 신청 버튼 동작 추가
        jButtonChangePassword.addActionListener(evt -> changePassword(student)); // 암호 변경 동작 추가
        jButtonStudentBill.addActionListener(evt -> openStudentBill());
        jButtonLogout.addActionListener(evt -> logout()); // 로그아웃 버튼 이벤트 추가

        // 레이아웃 설정
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createSequentialGroup()
                        .addContainerGap(50, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jButtonCourseList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonCourseRequest, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonStudentBill, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonChangePassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonLogout,javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(50, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jButtonCourseList)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonCourseRequest)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonStudentBill)
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
                                .addComponent(jLabelStudentName)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(14, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelStudentName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addContainerGap(14, Short.MAX_VALUE)
        );

        pack();
    }

    // 수강 관리 창을 열기 위한 메소드
    private void openCourseList() {
        new StudentCourseList(student).setVisible(true);
    }

    // 수강 신청 창을 열기 위한 메소드
    private void openCourseRequest() {
        new StudentCourseRequest(student).setVisible(true);
    }

    // 청구서 발급 메서드
    private void openStudentBill() {
        new StudentBill(student.getName()).setVisible(true);
    }

    private void logout() {
        new login().setVisible(true); // Login 페이지로 돌아감
        this.dispose(); // 현재 AdminPage 닫기
    }

    // 암호 변경 기능
    private void changePassword(Student student) {
        String userID = JOptionPane.showInputDialog(this, "암호를 변경할 사용자 ID를 입력하세요:");
        if (userID == null || userID.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "사용자 ID를 입력하지 않았습니다.");
            return;
        }

        String userPassword = JOptionPane.showInputDialog(this, "기존 암호를 입력하세요:");
        if (userPassword == null || userPassword.trim().isEmpty() || student.passwordCheck(userPassword) == 0) {
            JOptionPane.showMessageDialog(this, "암호가 틀렸습니다.");
            return;
        }

        String newPassword = JOptionPane.showInputDialog(this, "새로운 암호를 입력하세요:");
        if (newPassword == null || newPassword.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "새로운 암호를 입력하지 않았습니다.");
            return;
        }

        student.setPassword(newPassword);  // 암호를 새 암호로 변경

        JOptionPane.showMessageDialog(this, "암호가 성공적으로 변경되었습니다.");
    }
}
