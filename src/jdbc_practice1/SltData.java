package jdbc_practice1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SltData {
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
            System.out.println("Successively linked to the Database.");

            // 데이터 조회용 SQL 쿼리 작성
            String sql = "SELECT EMPNO, ENAME, DEPTNO FROM EMP_TEMP" + " " + "WHERE DEPTNO=?";
            // 특정 부서(DEPTNO)가 30인 직원들을 조회하는 쿼리

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "30"); // WHERE 절의 조건값으로 부서 번호 30 설정

            // 쿼리 실행 및 결과 받기
            ResultSet rs = pstmt.executeQuery();

            // 결과를 반복해서 읽어옴
            while (rs.next()) {
                Employee ep = new Employee(); // Employee 객체 생성
                ep.setEMPNO(rs.getString("EMPNO")); // 직원 번호 설정
                ep.setENAME(rs.getString("ENAME")); // 직원 이름 설정
                ep.setDEPTNO(rs.getString("DEPTNO")); // 부서 번호 설정
                System.out.println(ep); // 직원 정보 출력
            }

            // 조회된 데이터가 없으면 "조회내용 없음" 메시지 출력
            System.out.println("조회내용 없음");

            // ResultSet 자원 해제
            rs.close();

            // PreparedStatement 자원 해제
            pstmt.close();
        } catch (Exception e) {
            // 예외 발생 시 스택 트레이스 출력
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
        } // finally
    } // main
}
