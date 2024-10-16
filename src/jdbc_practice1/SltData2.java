package jdbc_practice1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SltData2 {
	public static void main(String[] args) {
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3306/scott";
			String user = "root";
			String pw = "mbc1234";

			conn = DriverManager.getConnection(url, user, pw);

			System.out.println("Successively linked to the Database.");

			String sql = new StringBuilder().append("select * from DEPT_TEMP ").append("where DEPTNO=?").toString();

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 30);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Department dp = new Department(); // Employee 객체 생성
				dp.setDeptno(rs.getInt("DEPTNO")); // 직원 번호 설정
				dp.setDname(rs.getString("DNAME")); // 직원 이름 설정
				dp.setLoc(rs.getString("LOC")); // 부서 번호 설정
				System.out.println(dp); // 직원 정보 출력
			}

			// 조회된 데이터가 없으면 "조회내용 없음" 메시지 출력
			System.out.println("조회내용 없음");

			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 데이터베이스 연결이 존재할 경우 연결 종료
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					// 연결 종료 시 예외 처리
				}
			} // if
		}

	}
}
