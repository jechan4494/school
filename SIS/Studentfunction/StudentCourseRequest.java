package SIS.Studentfunction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentCourseRequest extends JFrame {

    private static final String Student_FILE = "student.xlsx";
    private static final String Course_FILE = "Courses.xlsx";
    private JTable courseTable;  // 강좌 정보를 표시할 JTable
    private JButton registerButton;  // 수강 신청 버튼
    private JButton backButton;
    private List<Course> courses;  // 강좌 정보를 저장할 리스트

    public StudentCourseRequest(Student student) {
        setTitle("수강 신청 시스템");  // 창 제목 설정
        setSize(800, 600);  // 창 크기 설정
        setLocationRelativeTo(null);  // 창을 화면 가운데에 위치시키기
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // 창 닫기 버튼 클릭 시 프로그램 종료

        // Excel 파일에서 강좌 정보를 로드
        courses = loadCoursesFromExcel("Courses.xlsx");

        // 컴포넌트 생성
        courseTable = new JTable();  // 강좌 목록을 표시할 테이블
        populateCourseTable();  // 테이블에 데이터를 채우는 함수 호출
        JScrollPane tableScrollPane = new JScrollPane(courseTable);  // 테이블을 스크롤 가능한 영역에 넣기

        registerButton = new JButton("수강 신청");  // 수강 신청 버튼
        backButton = new JButton("뒤로가기");  // 뒤로가기 버튼
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 창을 닫고 이전 화면으로 돌아가기
                dispose();  // 현재 창 닫기 (이전 화면으로 돌아감)
            }
        });

        // 수강 신청 버튼 클릭 시 이벤트 처리
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    registerCourse(student);  // 수강 신청 함수 호출
                } catch (IOException ex) {
                    Logger.getLogger(StudentCourseRequest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        // 레이아웃 설정
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));  // 컴포넌트를 세로로 배치
        panel.add(registerButton);  // 수강 신청 버튼 추가
        panel.add(backButton);
        panel.add(tableScrollPane);  // 강좌 목록 테이블 추가

        getContentPane().add(panel);  // 프레임에 패널을 추가
    }

    // Excel 파일에서 강좌 정보를 읽어오는 함수
    private List<Course> loadCoursesFromExcel(String Student_FILE) {
        List<Course> coursesList = new ArrayList<>();  // 강좌 정보를 저장할 리스트
        try {
            FileInputStream fis = new FileInputStream(new File(Student_FILE));  // Excel 파일을 읽기 위한 입력 스트림
            XSSFWorkbook workbook = new XSSFWorkbook(fis);  // Excel 워크북 객체 생성
            Sheet sheet = workbook.getSheetAt(0);  // 첫 번째 시트를 가져옴

            // 시트에서 데이터를 읽어오기
            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue;  // 첫 번째 행은 헤더이므로 건너뜀
                }
                // 각 셀에서 데이터를 읽어 강좌 객체 생성
                String courseId = row.getCell(0).getStringCellValue();  // 강좌 번호
                String courseName = row.getCell(1).getStringCellValue();  // 강좌 이름
                String department = row.getCell(2).getStringCellValue();  // 담당 학과
                int credits = (int) row.getCell(3).getNumericCellValue();  // 학점
                int maxStudents = (int) row.getCell(4).getNumericCellValue();  // 최대 인원
                String professor = row.getCell(5).getStringCellValue();  // 담당 교수
                double price = row.getCell(6).getNumericCellValue();  // 강의 가격
                String description = row.getCell(7).getStringCellValue();  // 강의 소개

                // 강좌 객체를 리스트에 추가
                coursesList.add(new Course(courseId, courseName, department, credits, maxStudents, professor, price, description));
            }
            workbook.close();  // 워크북을 닫음
        } catch (Exception e) {
            e.printStackTrace();  // 오류 발생 시 출력
        }
        return coursesList;  // 강좌 리스트 반환
    }

    // 강좌 목록 테이블에 데이터를 채우는 함수
    private void populateCourseTable() {
        // 테이블 컬럼 제목 설정
        String[] columns = {"강좌 번호", "강좌 이름", "담당 학과", "학점", "최대인원", "담당 교수", "강의 가격", "강의 소개"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);  // 테이블 모델 생성

        // 강좌 정보를 테이블에 추가
        for (Course course : courses) {
            Object[] row = {
                course.getCourseId(),
                course.getCourseName(),
                course.getDepartment(),
                course.getCredits(),
                course.getMaxStudents(),
                course.getProfessor(),
                course.getPrice(),
                course.getDescription()

            };
            model.addRow(row);  // 각 강좌 정보를 테이블에 행으로 추가
        }

        courseTable.setModel(model);  // 테이블에 모델 설정
    }

    // 수강 신청을 처리하는 함수
    private void registerCourse(Student student) throws IOException {
        String studentName = student.getName();  // 입력된 학생 이름 가져오기
        int selectedRow = courseTable.getSelectedRow();  // 선택된 강좌의 행 인덱스 가져오기

        // 강좌를 선택하지 않은 경우
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "신청할 수업을 선택하세요");  // 경고 메시지
            return;
        }

        Course selectedCourse = courses.get(selectedRow);  // 선택된 강좌 객체 가져오기

        // 강좌가 마감된 경우
        if (CheckMaxStudent(selectedCourse.getCourseName()) == 0) {
            JOptionPane.showMessageDialog(this, "수강인원이 가득 찼습니다.");  // 경고 메시지
            return;
        }

        double totalEarnedCredits = 0;

        try {
            // Excel 파일을 읽기 위한 FileInputStream
            File file = new File(Student_FILE);

            if (!file.exists()) {
                // 파일이 존재하지 않으면 경고 메시지 출력
                System.out.println("경고: 파일이 존재하지 않습니다: " + Student_FILE);
                return;
            } else if (file.length() == 0) {
                // 파일이 존재하지만 비어있으면 경고 메시지 출력
                System.out.println("경고: 파일이 비어 있습니다: " + Student_FILE);
                return;
            }

            FileInputStream fis = new FileInputStream(new File(Student_FILE));

            // XSSFWorkbook 객체를 생성하여 Excel 파일을 읽음
            Workbook workbook = new XSSFWorkbook(fis);

            // 첫 번째 시트 가져오기
            Sheet sheet = workbook.getSheetAt(0);

            // 획득 학점 합산 변수
            // 첫 번째 행(헤더)을 제외하고 각 행을 순차적으로 읽기
            for (Row row : sheet) {
                // 첫 번째 열(이름)에서 학생 이름을 읽어옴
                String name = row.getCell(0) != null ? row.getCell(0).getStringCellValue() : "";

                // 만약 이름이 일치하면 획득 학점 합산
                if (name.equals(student.getName())) {
                    // 획득 학점 (5번 열)
                    double earnedCredits = row.getCell(4) != null ? row.getCell(4).getNumericCellValue() : 0;
                    totalEarnedCredits += earnedCredits;
                }
            }
            workbook.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //최대학점을 넘길 경우 
        if ((totalEarnedCredits + selectedCourse.getCredits()) > 18) {
            JOptionPane.showMessageDialog(this, "18학점 이상의 강의는 신청할수 없습니다.");  // 경고 메시지
            return;
        }

        // 중복검사
        if (CheckCourse(selectedCourse, studentName) == 1) {
            JOptionPane.showMessageDialog(this, "이미 신청한 강의는 신청할수 없습니다.");  // 경고 메시지
            return;
        }

        // 수강 신청 처리: 수강을 듣는 인원수 추가
        student.addStudentToExcel(Student_FILE, Course_FILE, student);
        JOptionPane.showMessageDialog(this, studentName + " 님은 " + selectedCourse.getCourseName() + " 강좌를 성공적으로 수강 완료하셨습니다");  // 성공 메시지
        //populateCourseTable();  // 테이블 갱신
    }

    //중복검사 메서드
    private int CheckCourse(Course selectedCourse, String studentName) throws IOException {
        String courseName = selectedCourse.getCourseName();  // 수강할 강좌 이름

        File file = new File(Student_FILE);  // student.xlsx 파일

        if (!file.exists()) {
            System.out.println("경고: 파일이 존재하지 않습니다: " + Student_FILE);
            return 1;  // 파일이 없으면 0 반환
        } else if (file.length() == 0) {
            System.out.println("경고: 파일이 비어 있습니다: " + Student_FILE);
            return 1;  // 파일이 비어있으면 0 반환
        }

        try (FileInputStream fis = new FileInputStream(file); Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);  // 첫 번째 시트 가져오기

            // 각 행을 순차적으로 확인
            for (Row row : sheet) {
                // 첫 번째 열(이름)에서 학생 이름을 읽어옴
                String name = row.getCell(0) != null ? row.getCell(0).getStringCellValue() : "";

                // 학생 이름이 일치하는 경우
                if (name.equals(studentName)) {
                    // 신청 강좌 (4번 열)에서 강좌 이름을 읽어옴
                    String registeredCourse = row.getCell(3) != null ? row.getCell(3).getStringCellValue() : "";

                    // 선택된 강좌가 이미 수강한 강좌와 일치하는지 확인
                    if (courseName.equals(registeredCourse)) {
                        // 이미 수강한 강좌이면 1을 반환 (중복 수강)
                        return 1;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return 1;  // 예외 발생 시 0 반환
        }

        return 0;  // 중복된 강좌가 없으면 0 반환
    }

    // 특정강좌의 수업을 듣는 인원수 파악 함수
    private int CheckMaxStudent(String ClassName) {
        int result = -1;  // 기본적으로 -1를 반환 (기본값 설정)

        try {
            FileInputStream fis = new FileInputStream(new File(Course_FILE));
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);  // 첫 번째 시트 선택

            for (Row row : sheet) {
                // 강좌명 찾기 (예: 강좌명이 8번째 열에 있다고 가정)
                Cell classNameCell = row.getCell(1);  // 강좌명 열 (인덱스 7)

                if (classNameCell != null) {
                    String className = classNameCell.getStringCellValue();

                    // 주어진 ClassName과 일치하는 강좌 찾기
                    if (className.equals(ClassName)) {
                        // "최대인원"과 "듣는 인원수" 열 값 가져오기
                        Cell maxStudentsCell = row.getCell(6);  // 최대인원 열
                        Cell currentStudentsCell = row.getCell(8);  // 듣는 인원수 열 

                        if (maxStudentsCell != null && currentStudentsCell != null) {
                            try {
                                // 최대인원과 듣는 인원수를 숫자로 받아옴
                                double maxStudents = maxStudentsCell.getNumericCellValue();
                                double currentStudents = currentStudentsCell.getNumericCellValue();

                                // 듣는 인원수가 최대인원보다 적으면 1, 아니면 0
                                if (currentStudents < maxStudents) {
                                    result = 1;
                                } else {
                                    result = 0;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        break;  // ClassName이 일치하면 더 이상 반복할 필요 없음
                    }
                }
            }
            workbook.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;  // 1 또는 0을 반환
    }

    public static void main(String[] args) {
        Student student = null;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StudentCourseRequest(student).setVisible(true);  // 프로그램 실행
            }
        });
    }

    // Course 클래스: 강좌 정보를 저장하는 클래스
    public static class Course {

        private String courseId;  // 강좌 번호
        private String courseName;  // 강좌 이름
        private String department;  // 담당 학과
        private int credits;  // 학점
        private int maxStudents;  // 최대 인원
        private String professor;  // 담당 교수
        private double price;  // 강의 가격
        private String description;  // 강의 소개

        // 생성자: 강좌 정보를 초기화
        public Course(String courseId, String courseName, String department, int credits, int maxStudents,
                String professor, double price, String description) {
            this.courseId = courseId;
            this.courseName = courseName;
            this.department = department;
            this.credits = credits;
            this.maxStudents = maxStudents;
            this.professor = professor;
            this.price = price;
            this.description = description;
        }

        // 각 필드에 대한 Getter 메서드
        public String getCourseId() {
            return courseId;
        }

        public String getCourseName() {
            return courseName;
        }

        public String getDepartment() {
            return department;
        }

        public int getCredits() {
            return credits;
        }

        public int getMaxStudents() {
            return maxStudents;
        }

        public String getProfessor() {
            return professor;
        }

        public double getPrice() {
            return price;
        }

        public String getDescription() {
            return description;
        }
    }
}
