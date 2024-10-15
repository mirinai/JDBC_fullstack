package jdbc_practice1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UptData {
    public static void main(String[] args) {

        Connection conn = null;

        try {
            // MySQL JDBC 드라이버 클래스 로드
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 데이터베이스 연결 정보 설정
            String url = "jdbc:mysql://localhost:3306/scott"; // 데이터베이스 URL (호스트:포트/데이터베이스명)
            String user = "root"; // 데이터베이스 사용자명
            String pw = "mbc1234"; // 데이터베이스 비밀번호

            // 데이터베이스에 연결
            conn = DriverManager.getConnection(url, user, pw);
            System.out.println("데이터베이스에 성공적으로 연결되었습니다.");

            // SQL UPDATE 문 작성
            String sql = "UPDATE EMP_TEMP SET COMM=? WHERE EMPNO=?";

            // PreparedStatement 객체 생성 및 SQL 문 설정
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // SQL 문에 사용할 파라미터 값 설정
            pstmt.setString(1, "1000"); // COMM 컬럼에 설정할 값
            pstmt.setString(2, "9999");  // WHERE 절의 DEPTNO 값

            // SQL 문 실행 및 영향받은 행의 수 반환
            int reCnt = pstmt.executeUpdate();
            System.out.println("처리결과 n =" + reCnt);

            // PreparedStatement 자원 해제
            pstmt.close();
        } catch (ClassNotFoundException e) {
            // JDBC 드라이버 로드 실패 시 예외 처리
            System.out.println("[JDBC Connector Driver 오류 : " + e.getMessage() + "]");
            e.printStackTrace();

        } catch (SQLException e) {
            // SQL 관련 예외 처리
            System.out.println("[SQL Error : " + e.getMessage() + "]");
            e.printStackTrace();

        } finally {
            // 데이터베이스 연결이 존재할 경우 연결 종료
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("연결 끊어짐");

                } catch (Exception e) { // e3 대신 e 사용
                    // 연결 종료 시 예외 처리
                    e.printStackTrace();
                }
            }
        } // finally

    }
}
