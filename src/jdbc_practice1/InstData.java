package jdbc_practice1;

// JDBC 관련 클래스를 임포트
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InstData {
    public static void main(String[] args) {

        // 데이터베이스 연결을 위한 Connection 객체 초기화
        Connection conn = null;

        try {
            // MySQL JDBC 드라이버 클래스 로드
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 데이터베이스에 연결 (URL, 사용자명, 비밀번호 사용)
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/scott", "root", "mbc1234");

            // 실행할 SQL INSERT 문 작성 (파라미터는 ?로 표시)
            String sql = "INSERT INTO EMP_TEMP (EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            // PreparedStatement 객체 생성 및 SQL 문 설정
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            // SQL 문에 사용할 파라미터 값 설정
            pstmt.setString(1, "3001"); // EMPNO
            pstmt.setString(2, "EUGENE"); // ENAME
            pstmt.setString(3, "SECRETARY"); // JOB
            pstmt.setString(4, "7902"); // MGR
            pstmt.setString(5, "2020-04-22 10:16:23"); // HIREDATE
            pstmt.setString(6, "3300"); // SAL
            pstmt.setString(7, "50"); // COMM
            pstmt.setString(8, "10"); // DEPTNO

            // SQL 문 실행 및 영향받은 행의 수 반환
            int reCnt = pstmt.executeUpdate();
            System.out.println("처리결과 n =:" + reCnt);

            // PreparedStatement 자원 해제
            pstmt.close();
        } catch (ClassNotFoundException e) {
            // TODO: handle exception
            System.out.println("ClassNotFoundException");
            e.printStackTrace();

        } catch (SQLException e) {
            // SQL 관련 예외 처리
            e.printStackTrace();
        } finally {
            // 데이터베이스 연결이 존재할 경우 연결 종료
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("연결 끊어짐");

                } catch (SQLException e) {
                    // 연결 종료 시 예외 처리
                    e.printStackTrace();
                }
            }
        } // finally

    }// main
} // InstData