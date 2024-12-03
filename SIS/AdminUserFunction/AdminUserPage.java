package SIS.AdminUserFunction;

import javax.swing.*;
import java.awt.*;

public class AdminUserPage extends JFrame {
  private static AdminUser adminUser;

  public AdminUserPage(AdminUser adminUser) {
    this.adminUser = adminUser;
    initComponents();
  }

  private void initComponents() {
    setTitle("Admin User Information");
    setSize(400, 300);  // 창 크기 설정
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(5, 1, 10, 10));  // 세로로 나열

    JLabel idLabel = new JLabel("Admin User ID: " + adminUser.getAdminUserID());
    JLabel nameLabel = new JLabel("Name: " + adminUser.getName());
    JLabel ssnLabel = new JLabel("SSN: " + adminUser.getSsn());

    panel.add(idLabel);
    panel.add(nameLabel);
    panel.add(ssnLabel);

    add(panel);
  }

  public static void main(String[] args) {
    new AdminUserPage(adminUser).setVisible(true);
  }
}
