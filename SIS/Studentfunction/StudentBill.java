package SIS.Studentfunction;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentBill extends JFrame {

    private static final String STUDENT_FILE = "Student.xlsx";  // 학생 정보가 담긴 파일 경로
    private static final String COURSES_FILE = "Courses.xlsx";  // 강의 정보가 담긴 파일 경로

    private JTextArea billTextArea;

    public StudentBill(String student) {
        // JFrame 설정
        setTitle("학생 청구서 생성");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // 화면 중앙에 배치

        // UI 구성
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // 청구서 내용 출력 영역
        billTextArea = new JTextArea();
        billTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(billTextArea);

        // 청구서 생성 버튼
        JButton generateBillButton = new JButton("청구서 생성");
        generateBillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String studentName = student;
                    generateStudentBill(studentName);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton backButton = new JButton("뒤로가기");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
         // 버튼을 buttonPanel에 추가
        buttonPanel.add(generateBillButton);
        buttonPanel.add(backButton);

        // buttonPanel을 panel의 SOUTH에 배치
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // 메인 프레임에 패널 추가
        add(panel);

        // 패널에 컴포넌트 추가
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // JFrame에 패널 추가
        add(panel);
    }

    // 학생 이름을 받아와서 청구서를 출력하는 메소드
    private void generateStudentBill(String studentName) throws IOException {
        // Student.xlsx 파일에서 해당 학생의 수강 신청 정보를 읽어옴
        List<CourseEnrollment> studentCourses = getStudentCourses(studentName);

        // 수강 신청 정보가 없다면 메시지 출력
        if (studentCourses.isEmpty()) {
            billTextArea.setText(studentName + "의 수강 신청 정보가 없습니다.");
            return;
        }

        double totalCredits = 0;  // 총 신청 학점
        double totalCost = 0;     // 총 강의 가격
        double totalGPA = 0;      // 총 GPA (평균 학점 계산을 위한 합)

        // 청구서 헤더 출력
        StringBuilder bill = new StringBuilder();
        bill.append("청구서\n");
        bill.append("학생 이름: ").append(studentName).append("\n");
        bill.append("강의\t").append("학점\t").append("가격\n");

        // 수강 신청한 강의별로 정보를 출력
        for (CourseEnrollment enrollment : studentCourses) {
            bill
                    .append(enrollment.courseName).append("\t")
                    .append(enrollment.credits).append("\t")
                    .append(enrollment.price).append("\n");
            totalCredits += enrollment.credits;  // 학점 합산
            totalCost += enrollment.price;      // 강의 가격 합산
            totalGPA += enrollment.gpa;         // GPA 합산
        }

        // 평균 GPA 계산
        double averageGPA = totalGPA / studentCourses.size();

        // 총 학점, 평균 학점, 총 강의 가격 합계 출력
        bill.append("총 신청 학점: ").append(totalCredits).append("\n");
        bill.append("평균 학점: ").append(averageGPA).append("\n");
        bill.append("총 강의 가격의 합: ").append(totalCost).append("원\n");

        // 청구서 내용 표시
        billTextArea.setText(bill.toString());
    }

    // Student.xlsx 파일에서 학생이 신청한 강의를 가져오는 메소드
    private List<CourseEnrollment> getStudentCourses(String studentName) throws IOException {
        FileInputStream fis = new FileInputStream(new File(STUDENT_FILE));
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);

        List<CourseEnrollment> studentCourses = new ArrayList<>();

        for (Row row : sheet) {
            String name = row.getCell(0).getStringCellValue();  // 첫 번째 열에서 이름을 가져옴
            if (name.equals(studentName)) {
                String courseName = row.getCell(3).getStringCellValue();  // 신청 강의
                double credits = row.getCell(4).getNumericCellValue();   // 신청 학점
                double gpa = row.getCell(2).getNumericCellValue();       // 평균 학점
                studentCourses.add(new CourseEnrollment(courseName, credits, gpa));
            }
        }

        workbook.close();
        fis.close();

        return studentCourses;
    }

    // Courses.xlsx 파일에서 강의 가격을 가져오는 메소드
    private double getCoursePrice(String courseName) throws IOException {
        FileInputStream fis = new FileInputStream(new File(COURSES_FILE));
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);

        double price = 0;

        for (Row row : sheet) {
            String name = row.getCell(1).getStringCellValue();
            if (name.equals(courseName)) {
                price = row.getCell(6).getNumericCellValue();
                break;
            }
        }

        workbook.close();
        fis.close();

        return price;
    }

    // 수강 신청 강좌 정보를 담는 클래스
    private class CourseEnrollment {

        String courseName;
        double credits;
        double gpa;
        double price;

        public CourseEnrollment(String courseName, double credits, double gpa) throws IOException {
            this.courseName = courseName;
            this.credits = credits;
            this.gpa = gpa;
            this.price = getCoursePrice(courseName);
        }
    }

    public static void main(String[] args) {
        String a = "홍길동";
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                StudentBill frame = new StudentBill(a);
                frame.setVisible(true);
            }
        });
    }
}
