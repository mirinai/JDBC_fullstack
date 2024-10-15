package jdbc_practice1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UptData2 {
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

            String sql = new StringBuilder()
                    .append("UPDATE EMP_TEMP SET ") // EMP_TEMP 테이블의 레코드 수정 시작
                    .append("EMPNO=?, ")            // EMPNO 컬럼을 업데이트할 값 설정 (파라미터 1)
                    .append("ENAME=?, ")            // ENAME 컬럼을 업데이트할 값 설정 (파라미터 2)
                    .append("JOB=?, ")              // JOB 컬럼을 업데이트할 값 설정 (파라미터 3)
                    .append("MGR=?, ")              // MGR 컬럼을 업데이트할 값 설정 (파라미터 4)
                    .append("HIREDATE=?, ")         // HIREDATE 컬럼을 업데이트할 값 설정 (파라미터 5)
                    .append("SAL=?, ")              // SAL 컬럼을 업데이트할 값 설정 (파라미터 6)
                    .append("COMM=? ")              // COMM 컬럼을 업데이트할 값 설정 (파라미터 7)
                    .append("WHERE DEPTNO=?")       // 특정 DEPTNO를 가진 레코드 선택 (파라미터 8)
                    .toString();                    // 최종 SQL 문자열로 변환
            

            // PreparedStatement 객체 생성 및 SQL 문 설정
            PreparedStatement pstmt = conn.prepareStatement(sql);

         // SQL 문에 사용할 파라미터 값 설정
            pstmt.setInt(1, 9998); // EMPNO: 직원 번호 (정수형)
            pstmt.setString(2, "ALISA"); // ENAME: 직원 이름 (문자열)
            pstmt.setString(3, "DEVELOPER"); // JOB: 직무 (문자열)
            pstmt.setInt(4, 7788); // MGR: 상사 번호 (정수형)
            pstmt.setString(5, "2001-01-01 00:00:00"); // HIREDATE: 입사 날짜 (문자열, DATE/DATETIME 형식)
            pstmt.setString(6, "6000"); // SAL: 급여 (문자열, double)
            pstmt.setDouble(7, 700); // COMM: 커미션 (double)
            pstmt.setInt(8, 10); // WHERE EMPNO=10: 조건에 맞는 레코드 선택 (정수형)

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
