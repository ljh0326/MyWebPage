package myWeb;

import java.sql.*;

/*
 * 회원정보 데이터베이스에 접근하기 위한 클래스 
 */
public class UserDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	// 생성자에 자동으로 커넥션이 이루어질 수 있도록 한다.
	public UserDAO() {
		try {
			// 1. DB접속에 필요한 데이터들을 받아서
			String DB_URL = "jdbc:oracle:thin:@127.0.0.1:1522:XE";
			String DB_USER = "student";
			String DB_PASSWORD = "1234";
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. 해당정보를 이용해 Connection 객체를 생성한다.
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	// 로그인을 처리하는 메서드
	public int login(String userID, String userPassword) {

		// 1. 쿼리문을 설정한다.
		String SQL = "SELECT PASSWORD FROM USER_INFO WHERE E_MAIL = ?";
		try {
			// 2. 해당 쿼리문을 가진 connection객체 생성
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();

			// 3.1 결과가 존재하는지 확인
			if (rs.next()) {
				// 3.1.1 일치하면 1 반환 로그인 성공
				if (rs.getString(1).equals(userPassword)) {
					return 1;
				}
				// 3.1.2 불일치하면 0 반환 비밀번호 틀림
				else {
					return 0;
				}
			}

			// 3.2 결과(아이디)가 없으면 -1 반환
			return -1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//4.데이터 베이스 오류
		return -2;
	}
	
	//사용자 등록 메서드 
	public int regist(String email, String psw,  String name, String pnum){
		// 1. 쿼리문을 설정한다.
				System.out.println("진입");
				String SQL = "INSERT INTO USER_INFO (E_MAIL, PASSWORD, NAME ,PHONNUM) VALUES(?, ?, ?, ?)";
				try {
					// 2. 해당 쿼리문을 가진 connection객체 생성
					pstmt = conn.prepareStatement(SQL);
					pstmt.setString(1, email);
					pstmt.setString(2, psw);
					pstmt.setString(3, name);
					pstmt.setString(4, pnum);
					
					return pstmt.executeUpdate();
				}catch(Exception e){
					e.printStackTrace( );
				}
		return -1;
	}
}
