package myWeb;

import java.sql.*;

public class JdbcTest {
    public static void main(String[] args) {
        String DB_URL = "jdbc:oracle:thin:@127.0.0.1:1522:XE";
        String DB_USER = "student";  // DB�� userid�� pwd�� �˸°� ���� 
        String DB_PASSWORD = "1234";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String query = "SELECT sysdate FROM dual"; // �ý����� ��¥�� ����Ѵ�.
        try {
            // 데이터베이스 연결
            Class.forName("oracle.jdbc.driver.OracleDriver");  //드라이브 설정 
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); //DB객체생성
            stmt = conn.createStatement();             // Statement�� �����´�.
            rs = stmt.executeQuery(query); //
            while (rs.next()) { 
                String curDate = rs.getString(1);
                System.out.println(curDate); 
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                conn.close();
            } catch ( SQLException e ) {}
        }
    } // main()�� ��
} // Ŭ������ ��
