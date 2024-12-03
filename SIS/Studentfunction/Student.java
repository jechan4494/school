package SIS.Studentfunction;

import SIS.loginFunction.User;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class Student extends User {

    private String studentName;      // 학생 이름
    private int appCredits;          // 신청 학점
    private double aveGrade;         // 평균 학점
    private String courseNames; // 신청 강의
    private int acquireCredits;      // 획득 학점
    private String ssn;              // 학생 주민등록번호
    private String department;       // 학생 전공

    // 생성자
    public Student(String studentName,int appCredits, double aveGrade,
            String courseNames, int acquireCredits, String ssn, String department,
            String userID, String password, String role, String name, String Ssn) {
        super(userID, password, role, name, Ssn);
        this.studentName = studentName;
        this.appCredits = appCredits;
        this.aveGrade = aveGrade;
        this.courseNames = courseNames;
        this.acquireCredits = acquireCredits;
        this.ssn = ssn;
        this.department = department;
    }

    // Getter 및 Setter
    public String getStudentName() {
        return studentName;
    }


    public int getAppCredits() {
        return appCredits;
    }

    public double getAveGrade() {
        return aveGrade;
    }

    public String getCourseNames() {
        return courseNames;
    }

    public int getAcquireCredits() {
        return acquireCredits;
    }

    public String getSsn() {
        return ssn;
    }

    public String getDepartment() {
        return department;
    }

    // 비밀번호 비교 메서드
    public int passwordCheck(String password) {
        if (password.equals(this.getPassword())) {
            return 1;
        } else {
            return 0;
        }
    }

    // 엑셀 파일에 학생 정보 추가
    public static void addStudentToExcel(String Student_file, String Course_File, Student newStudent) throws IOException {
        File file = new File(Student_file);
        if (!file.exists() || file.length() == 0) {
        System.out.println("파일이 비어 있습니다: " + Student_file);
        return;  // 더 이상 진행하지 않음
}
        Workbook workbook;
        Sheet studentSheet;

        // 파일이 없으면 새로 생성하고 헤더 추가
        if (!file.exists()) {
            workbook = new XSSFWorkbook();
            studentSheet = workbook.createSheet("student");
            // 헤더 추가
            Row headerRow = studentSheet.createRow(0);
            headerRow.createCell(0).setCellValue("이름");
            headerRow.createCell(1).setCellValue("신청 학점");
            headerRow.createCell(2).setCellValue("평균 학점");
            headerRow.createCell(3).setCellValue("신청 강의");
            headerRow.createCell(4).setCellValue("획득 학점");

        } else {
            try (FileInputStream fis = new FileInputStream(Student_file)) {
                workbook = new XSSFWorkbook(fis);
                studentSheet = workbook.getSheetAt(0);
                if (studentSheet == null) {
                    studentSheet = workbook.createSheet("student");
                }
            }
        }

        // 새 행을 추가
        int rowIndex = studentSheet.getPhysicalNumberOfRows();
        Row newRow = studentSheet.createRow(rowIndex);
        // 사용자 데이터를 행에 추가
        newRow.createCell(0).setCellValue(newStudent.getStudentName());  // 이름
        newRow.createCell(1).setCellValue(newStudent.getAppCredits());   // 신청 학점
        newRow.createCell(2).setCellValue(newStudent.getAveGrade());     // 평균 학점
        newRow.createCell(3).setCellValue(newStudent.getCourseNames());  // 신청 강의
        newRow.createCell(4).setCellValue(newStudent.getAcquireCredits()); // 획득 학점
        try {
            // 엑셀 파일 읽기
            FileInputStream fis = new FileInputStream(new File(Course_File));
            Workbook workbook2 = new XSSFWorkbook(fis);
            Sheet sheet = workbook2.getSheetAt(0);
            // 강좌를 찾았는지 여부
             // 첫 번째 시트 선택

            for (Row row : sheet) {
                // 첫 번째 열 (인덱스 0)에 강좌 이름이 있다고 가정
                Cell cell = row.getCell(1);  // 첫 번째 열의 셀

                if (cell != null) {
                    String cellValue = cell.getStringCellValue();
                    String Coursesname = newStudent.getCourseNames();

                    // 강좌명이 일치하면
                    if (cellValue.equals(Coursesname)) {
                        // 듣는 학생 수 1 증가
                        Cell updateCell = row.getCell(8);  // (수정할 열)
                        double currentvalue = updateCell.getNumericCellValue();
                        updateCell.setCellValue(currentvalue + 1);  // 값 수정

                        // 수정한 엑셀 파일을 저장
                        try (FileOutputStream fos = new FileOutputStream(new File(Course_File))) {
                            workbook2.write(fos);
                        }

                        break;  // 강좌명이 일치하면 더 이상 반복할 필요 없음
                    }
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        // 엑셀 파일 저장
        try (FileOutputStream fos = new FileOutputStream(Student_file)) {
            workbook.write(fos);
        }

            workbook.close();

    }

    // 엑셀 파일에서 학생 정보 불러오기
    public static List<Student> loadStudentFromExcel(String fileName, String studentName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("엑셀 파일이 존재하지 않습니다: " + fileName);
            return null;
        }

        List<Student> studentList = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(file); Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet studentSheet = workbook.getSheetAt(0);
            if (studentSheet == null) {
                System.out.println("'Student' 시트를 찾을 수 없습니다.");
                return null;
            }

            // 엑셀 데이터 읽기
            for (Row row : studentSheet) {
                if (row.getRowNum() == 0) {
                    continue;  // 헤더 건너뜀
                }
                String studentNameInFile = getCellValue(row, 0);  // 이름
                if (studentNameInFile.equals(studentName)) {
                    String appCredits = getCellValue(row, 1);  // 신청 학점
                    String aveGrade = getCellValue(row, 2);  // 평균 학점
                    String courseName = getCellValue(row, 3);  // 신청 강의
                    String acquireCredits = getCellValue(row, 4);  // 획득 학점

                    studentList.add(new Student(studentNameInFile, null, Integer.parseInt(appCredits),
                            Double.parseDouble(aveGrade), courseName, Integer.parseInt(acquireCredits),
                            null, null, null, null, null, null, null));
                    break;  // 해당 학생을 찾으면 루프 종료
                }
            }
        }
        return studentList;  // 학생 리스트 반환
    }

    // 엑셀 셀의 값을 읽어오는 메서드
    private static String getCellValue(Row row, int cellIndex) {
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
}
