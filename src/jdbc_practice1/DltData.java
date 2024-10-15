package jdbc_practice1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DltData {
	public static void main(String[] args) {
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 데이터베이스 연결 정보 설정
			String url = "jdbc:mysql://localhost:3306/scott"; // 데이터베이스 URL (호스트:포트/데이터베이스명)
			String user = "root"; // 데이터베이스 사용자명
			String pw = "mbc1234"; // 데이터베이스 비밀번호

			conn = DriverManager.getConnection(url, user, pw);
			System.out.println("Successively linked to the Database");

			String sql = "DELETE FROM EMP_TEMP WHERE ENAME LIKE ?";

			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, "EUGENE");

			// SQL 문 실행 및 영향받은 행의 수 반환
			int reCnt = pstmt.executeUpdate();
			System.out.println("처리결과 n =" + reCnt);

			// PreparedStatement 자원 해제
			pstmt.close();

		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("[SQL Error : " + e.getMessage() + "]");
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
	}
}
