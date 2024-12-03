package SIS.AdminCoursefunction;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.io.*;
import java.util.Iterator;

public class AdminCourseList extends JFrame {

  private static final String COURSE_FILE = "Courses.xlsx";
  private DefaultTableModel tableModel;
  private AdminCoursePage adminCoursePage;  // AdminPage 객체를 필드로 추가

  public AdminCourseList(AdminCoursePage adminCoursePage) { // 현재 강의 리스트 페이지
    this.adminCoursePage = adminCoursePage;  // AdminPage 객체를 받아서 초기화
    setTitle("강의 목록");
    setSize(800, 500);  // 창 크기 조정
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    initComponents();
  }

  private void initComponents() {
    JLabel titleLabel = new JLabel("강의 목록", SwingConstants.CENTER);
    titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 24));
    titleLabel.setForeground(new Color(35, 133, 232));

    JTable courseTable = new JTable();
    tableModel = new DefaultTableModel(
      new Object[][]{},
      new String[]{"강좌 번호", "강의 이름", "담당 학과", "학점", "강의 가격", "담당 교수", "최대 인원 수", "강의 소개"}
    );
    courseTable.setModel(tableModel);
    JScrollPane tableScroll = new JScrollPane(courseTable);
    tableScroll.setPreferredSize(new Dimension(750, 350));  // 테이블 스크롤 크기 조정

    JButton addButton = new JButton("강의 추가");
    JButton deleteButton = new JButton("강의 삭제");
    JButton refreshButton = new JButton("새로고침");
    JButton backButton = new JButton("뒤로 가기");

    addButton.addActionListener(e -> openAddCoursePage());
    deleteButton.addActionListener(e -> deleteCourse(courseTable));
    refreshButton.addActionListener(e -> loadCoursesFromExcel());
    backButton.addActionListener(e -> goBackToAdminPage());

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));  // 버튼 간격 조정
    buttonPanel.add(addButton);
    buttonPanel.add(deleteButton);
    buttonPanel.add(refreshButton);
    buttonPanel.add(backButton);

    setLayout(new BorderLayout());
    add(titleLabel, BorderLayout.NORTH);
    add(tableScroll, BorderLayout.CENTER);
    add(buttonPanel, BorderLayout.SOUTH);

    loadCoursesFromExcel();

    // 파일이 존재하지 않거나 비어 있으면 삭제 버튼 비활성화
    File file = new File(COURSE_FILE);
    if (!file.exists() || file.length() == 0) {
      deleteButton.setEnabled(false);
    }

    setVisible(true);
  }

  private void goBackToAdminPage() {
    this.dispose(); // 현재 창 닫기
    adminCoursePage.setVisible(true); // AdminPage 다시 표시
  }

  void loadCoursesFromExcel() {
    File file = new File(COURSE_FILE);
    if (!file.exists() || file.length() == 0) {
      createNewExcelFile();
      JOptionPane.showMessageDialog(this, "새로운 엑셀 파일이 생성되었습니다.", "정보", JOptionPane.INFORMATION_MESSAGE);
      return;
    }

    try (FileInputStream fis = new FileInputStream(file);
         Workbook workbook = new XSSFWorkbook(fis)) {

      Sheet sheet = workbook.getSheet("Courses");
      if (sheet == null) {
        System.out.println("시트 'Courses'를 찾을 수 없습니다.");
        return;
      }

      tableModel.setRowCount(0);

      Iterator<Row> rowIterator = sheet.iterator();
      if (rowIterator.hasNext()) rowIterator.next(); // 헤더 셀 스킵

      while (rowIterator.hasNext()) {
        Row row = rowIterator.next();
        Object[] rowData = new Object[row.getLastCellNum()];

        for (int i = 0; i < row.getLastCellNum(); i++) {
          Cell cell = row.getCell(i);
          rowData[i] = cell != null ? cell.toString() : "";
        }

        tableModel.addRow(rowData);
      }
    } catch (IOException e) {
      JOptionPane.showMessageDialog(this, "엑셀 파일 읽기 오류: " + e.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
    }
  }

  private void createNewExcelFile() {
    try (Workbook workbook = new XSSFWorkbook()) {
      Sheet sheet = workbook.createSheet("Courses");
      Row header = sheet.createRow(0);
      header.createCell(0).setCellValue("강좌 번호");
      header.createCell(1).setCellValue("강의 이름");
      header.createCell(2).setCellValue("담당 학과");
      header.createCell(3).setCellValue("학점");
      header.createCell(4).setCellValue("최대 인원 수");
      header.createCell(5).setCellValue("담당 교수");
      header.createCell(6).setCellValue("강의 가격");
      header.createCell(7).setCellValue("강의 소개");

      try (FileOutputStream fos = new FileOutputStream(COURSE_FILE)) {
        workbook.write(fos);
      }
    } catch (IOException e) {
      JOptionPane.showMessageDialog(this, "새 엑셀 파일 생성 오류: " + e.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
    }
  }

  private void openAddCoursePage() {
    SwingUtilities.invokeLater(() -> {
      try {
        AdminCourseSYS adminCourseSYS = new AdminCourseSYS();
        adminCourseSYS.createGUI();
      } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "강의 추가 페이지 열기 오류: " + e.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
      }
    });
  }

  private void deleteCourse(JTable courseTable) {
    int selectedRow = courseTable.getSelectedRow();
    if (selectedRow != -1) {
      String courseId = (String) courseTable.getValueAt(selectedRow, 0);

      int confirm = JOptionPane.showConfirmDialog(this, "강의 번호 " + courseId + "을/를 삭제하시겠습니까?", "삭제 확인", JOptionPane.YES_NO_OPTION);
      if (confirm == JOptionPane.YES_OPTION) {
        deleteCourseFromExcel(courseId);
        tableModel.removeRow(selectedRow);
      }
    } else {
      JOptionPane.showMessageDialog(this, "삭제할 강의를 선택하세요.", "오류", JOptionPane.ERROR_MESSAGE);
    }
  }

  private void deleteCourseFromExcel(String courseId) {
    try (FileInputStream fis = new FileInputStream(COURSE_FILE);
         Workbook workbook = new XSSFWorkbook(fis);
         FileOutputStream fos = new FileOutputStream(COURSE_FILE)) {

      Sheet sheet = workbook.getSheet("Courses");
      if (sheet == null) return;

      Iterator<Row> rowIterator = sheet.iterator();
      while (rowIterator.hasNext()) {
        Row row = rowIterator.next();
        if (courseId.equals(getCellValue(row, 0))) {
          int rowIndex = row.getRowNum();
          sheet.removeRow(row);
          if (rowIndex < sheet.getLastRowNum()) {
            sheet.shiftRows(rowIndex + 1, sheet.getLastRowNum(), -1);
          }
          break;
        }
      }

      workbook.write(fos);
    } catch (IOException e) {
      JOptionPane.showMessageDialog(this, "엑셀 파일 삭제 오류: " + e.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
    }
  }

  private static String getCellValue(Row row, int cellIndex) {
    Cell cell = row.getCell(cellIndex);
    return cell != null ? cell.toString() : "";
  }

  public static void main(String[] args) {
    new AdminCourseList(null); // AdminPage 객체를 전달해 AdminCourseList 생성
  }
}
