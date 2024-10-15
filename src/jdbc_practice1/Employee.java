package jdbc_practice1;

public class Employee {
    private String EMPNO; // 직원 번호
    private String ENAME; // 직원 이름
    private String DEPTNO; // 부서 번호

    // 직원 번호에 대한 Getter 및 Setter 메서드
    public String getEMPNO() {
        return EMPNO;
    }
    public void setEMPNO(String eMPNO) {
        EMPNO = eMPNO;
    }

    // 직원 이름에 대한 Getter 및 Setter 메서드
    public String getENAME() {
        return ENAME;
    }
    public void setENAME(String eNAME) {
        ENAME = eNAME;
    }

    // 부서 번호에 대한 Getter 및 Setter 메서드
    public String getDEPTNO() {
        return DEPTNO;
    }
    public void setDEPTNO(String dEPTNO) {
        DEPTNO = dEPTNO;
    }

    // 객체의 필드 값을 보기 쉽게 출력하도록 `toString()` 메서드 오버라이딩
    @Override
    public String toString() {
        return "Employee [EMPNO=" + EMPNO + ", ENAME=" + ENAME + ", DEPTNO=" + DEPTNO + "]";
    }
}

