package SIS.AdminUserFunction;

import SIS.loginFunction.User;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DeleteUserPage extends JFrame {

  private JTextField userIDField;
  private JPasswordField passwordField;
  private JButton deleteUserButton;

  public DeleteUserPage(List<User> users) { // 관리자 사용자 삭제 페이지
    setTitle("사용자 삭제");
    setSize(400, 200);  // 창 크기 설정
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    initComponents(users);
  }

  private void initComponents(List<User> users) {
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(3, 2, 10, 10));  // 3행 2열 레이아웃 설정

    // ID 입력 필드
    JLabel userIDLabel = new JLabel("아이디:");
    userIDField = new JTextField();

    // 비밀번호 입력 필드
    JLabel passwordLabel = new JLabel("비밀번호:");
    passwordField = new JPasswordField();

    // 삭제 버튼
    deleteUserButton = new JButton("사용자 삭제");
    deleteUserButton.addActionListener(e -> deleteUserAction(users));

    // 컴포넌트들 추가
    panel.add(userIDLabel);
    panel.add(userIDField);
    panel.add(passwordLabel);
    panel.add(passwordField);
    panel.add(new JLabel());  // 빈 공간
    panel.add(deleteUserButton);

    add(panel);
  }

  private void deleteUserAction(List<User> users) {
    String userID = userIDField.getText().trim();
    String password = new String(passwordField.getPassword()).trim();

    if (userID.isEmpty() || password.isEmpty()) {
      JOptionPane.showMessageDialog(this, "아이디와 비밀번호를 입력하세요.");
      return;
    }

    // 사용자 삭제
    boolean deleted = users.removeIf(user -> user.getUserID().equals(userID) && user.getPassword().equals(password));
    if (deleted) {
      JOptionPane.showMessageDialog(this, "사용자가 삭제되었습니다.");
      // 엑셀에 반영하는 로직 추가 가능
      dispose();  // 창 닫기
    } else {
      JOptionPane.showMessageDialog(this, "삭제할 사용자 정보가 일치하지 않습니다.");
    }
  }

  public static void main(String[] args) {
    EventQueue.invokeLater(() -> new DeleteUserPage(null).setVisible(true));
  }
}
