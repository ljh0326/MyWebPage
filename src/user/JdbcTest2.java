package user;
/*
 * 오류 체크용  에러가 있을때는 작게작게 짤라서 알아보자 
 */
import java.sql.*;

public class JdbcTest2 {
    public static void main(String[] args) {
        String DB_URL = "jdbc:oracle:thin:@127.0.0.1:1522:XE";
        String DB_USER = "student";  // DB�� userid�� pwd�� �˸°� ���� 
        String DB_PASSWORD = "1234";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = "SELECT sysdate FROM dual"; // �ý����� ��¥�� ����Ѵ�.
        try {
            // 데이터베이스 연결
            Class.forName("oracle.jdbc.driver.OracleDriver");  //드라이브 설정 
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); //DB객체생성
            pstmt = conn.prepareStatement(query);       // Statement�� �����´�.
            rs = pstmt.executeQuery(); //
            while (rs.next()) { 
                String curDate = rs.getString(1);
                System.out.println(curDate); 
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                pstmt.close();
                conn.close();
            } catch ( SQLException e ) {}
        }
    } // main()�� ��
} // Ŭ������ ��
