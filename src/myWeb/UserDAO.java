package myWeb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
 * 회원정보 데이터베이스에 접근하기 위한 클래스 
 */
class Dao {
	Connection conn;
	String tableName = "";

	Dao() {
		this(null, "");
	}

	Dao(String tableName) {
		this(null, tableName);
	}

	Dao(Connection conn, String tableName) {
		if (conn == null) {
			try {
				String DB_URL = "jdbc:oracle:thin:@127.0.0.1:1522:XE";
				String DB_USER = "student";
				String DB_PASSWORD = "1234";
//				Class.forName("oracle.jdbc.driver.OracleDriver");

				this.conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			} catch (SQLException e) {
				e.printStackTrace();
			
			} 
//			catch (ClassNotFoundException e) {
//				e.printStackTrace();
//			}
		} else {
			this.conn = conn;
		}
		this.tableName = tableName;
		/* 메서드 완성 */
	}

	void rollback() {
		if (conn != null) {
			try {
				conn.rollback();
			} catch (SQLException e) {

			}
		}
	}

	void close(AutoCloseable... acs) {
		for (AutoCloseable i : acs) {
			if (i == null)
				return;
			else
				try {
					i.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}

public class UserDao extends Dao {

	private PreparedStatement pstmt;
	private ResultSet rs;

	private static UserDao userDao = new UserDao();

	// 생성자에 자동으로 커넥션이 이루어질 수 있도록 한다.
	private UserDao() {
		super("USER_INFO");
	}

	private UserDao(Connection conn) {
		super(conn, "USER_INFO");
	}

	// 객체를 하나만 생성할 수 있게한다.
	public static UserDao getInstance() {
		if (userDao == null)
			userDao = new UserDao();

		return userDao;
	}

	// 모든 유저조회
	List<User> selectAllUsers() {

		List<User> list = new ArrayList<User>();

		String SQL = " SELECT * FROM " + tableName;

		System.out.println(SQL);

		try {
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				User u = new User();

				u.setE_mail(rs.getString(1));
				u.setPassword(rs.getString(2));
				u.setName(rs.getString(3));
				u.setIn_date(rs.getString(4));
				u.setPoint(rs.getInt(5));
				u.setPhonnum(rs.getString(6));
				list.add(u);
			}

			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt, rs);
		}

		return null;
	}

	// 로그인을 처리하는 메서드
	public int login(User user) {

		// 1. 쿼리문을 설정한다.
		String SQL = " SELECT PASSWORD FROM " + tableName + "  WHERE E_MAIL = ? ";
		try {
			// 2. 해당 쿼리문을 가진 connection객체 생성
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getE_mail());
			rs = pstmt.executeQuery();

			// 3.1 결과가 존재하는지 확인
			if (rs.next()) {
				// 3.1.1 일치하면 1 반환 로그인 성공
				if (rs.getString(1).equals(user.getPassword())) {
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
		} finally {
			close(pstmt, rs);
		}

		// 4.데이터 베이스 오류
		return -2;
	}

	// 삽입문장
	int insertUser(User u) {

		// 1. 쿼리문을 설정한다.
		String SQL = " INSERT INTO " + tableName + " (E_MAIL, PASSWORD, NAME ,PHONNUM) VALUES(?, ?, ?, ?) ";

		try {
			// 2. 해당 쿼리문을 가진 connection객체 생성
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, u.getE_mail());
			pstmt.setString(2, u.getPassword());
			pstmt.setString(3, u.getName());
			pstmt.setString(4, u.getPhonnum());

			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return -1;
	}

	// 업데이트 유저
	int updateUser(User u) {

		// 1. 쿼리문을 설정한다.
		String SQL = " UPDATE " + tableName + " SET NAME = ?, PASSWORD = ?, PHONNUM = ? WHERE E_MAIL = ? ";

		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, u.getName());
			pstmt.setString(2, u.getPassword());
			pstmt.setString(3, u.getPhonnum());
			pstmt.setString(4, u.getE_mail());

			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return -1;
	}

	// 유저 정보 받아오는 메서드
	public User getUserInfo(String email) {
		String SQL = " SELECT * FROM " + tableName + " WHERE E_MAIL = ? ";
		try {
			// 2. 해당 쿼리문을 가진 connection객체 생성
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			User user = new User();

			if (rs.next()) {
				user.setE_mail(rs.getString(1));
				user.setPassword(rs.getString(2));
				user.setName(rs.getString(3));
				user.setIn_date(rs.getString(4));
				user.setPoint(rs.getInt(5));
				user.setPhonnum(rs.getString(6));

				return user;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt);
		}

		return null;
	}

	// 딜리트유저
	int deleteUser(String userId) {
		// 1. 쿼리문을 설정한다.
		String SQL = " DELETE FROM " + tableName + " WHERE E_MAIL = ? ";

		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userId);
			return pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return -1;
	}
}
