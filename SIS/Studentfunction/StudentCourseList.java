package SIS.Studentfunction;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentCourseList extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private JButton backButton;

    public StudentCourseList(Student student) {
        setTitle("수강 신청 내역");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // 테이블 설정
        String[] columnNames = {"이름", "신청 학점", "평균 학점", "신청 강의", "획득 학점"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        backButton = new JButton("뒤로가기");
        backButton.addActionListener(e -> {
            // 뒤로가기 버튼 클릭 시 이전 화면(StudentCourseRequest)으로 돌아가기
            dispose();  // 현재 화면 닫기
        });

        // 버튼을 패널에 추가
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        add(buttonPanel, "South");

        // 수강 신청 내역을 로드하여 테이블에 추가
        loadStudentCourseList(student.getStudentName());

        setLocationRelativeTo(null); // 화면 중앙에 띄우기
    }

    // 학생 이름을 기준으로 수강 신청 내역을 불러오는 메소드
    private void loadStudentCourseList(String studentName) {
        List<String[]> studentCourses = getStudentCoursesFromExcel(studentName);

        for (String[] course : studentCourses) {
            tableModel.addRow(course);
        }
    }

    // 학생 이름으로 수강 신청 내역을 Excel에서 가져오는 메소드
    private List<String[]> getStudentCoursesFromExcel(String studentName) {
        List<String[]> courses = new ArrayList<>();

        File file = new File("student.xlsx");

        if (!file.exists()) {
            System.out.println("파일이 존재하지 않습니다.");
            return courses;
        }
        if (file.length() == 0) {
            System.out.println("파일이 비어있습니다.");
            return courses;
        }

        try (FileInputStream fis = new FileInputStream("student.xlsx"); Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                // 첫 번째 행은 헤더이므로 건너뜁니다
                if (row.getRowNum() > 0) {
                    String name = getCellValue(row, 0);
                    if (name.equals(studentName)) {
                        String[] courseData = new String[5];
                        courseData[0] = getCellValue(row, 0);  // 이름
                        courseData[1] = getCellValue(row, 1);  // 신청 학점
                        courseData[2] = getCellValue(row, 2);  // 평균 학점
                        courseData[3] = getCellValue(row, 3);  // 신청 강의
                        courseData[4] = getCellValue(row, 4);  // 획득 학점
                        courses.add(courseData);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return courses;
    }

    // 엑셀 셀 값을 가져오는 메소드
    private String getCellValue(Row row, int cellIndex) {
        Cell cell = row.getCell(cellIndex);
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            default:
                return "";
        }
    }

    public static void main(String[] args) {
        //new StudentCourseList(null);
        /*
        Student student = new Student("홍길동");
        SwingUtilities.invokeLater(() -> {
            new StudentCourseList(student).setVisible(true);
        });*/
    }
}
