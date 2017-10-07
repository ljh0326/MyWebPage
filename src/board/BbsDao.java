package board;

import java.sql.*;
import dao.*;
import java.util.ArrayList;
import java.util.List;

/*
 * 회원정보 데이터베이스에 접근하기 위한 클래스 
 */


//Dao를 상속받은 보드Dao 메서드
public class BbsDao extends Dao {

	private PreparedStatement pstmt;
	private ResultSet rs;

	//싱글턴을 하기위해 미리 객체를 생성해 준다.
	private static BbsDao bbsDao = new BbsDao();

	// 생성자에 자동으로 커넥션이 이루어질 수 있도록 한다.
	private BbsDao() {
		super("BBS");
	}

	private BbsDao(Connection conn) {
		super(conn, "BBS");
	}

	// 미리 만들어둔 객체를 반환하는 메서드 객체 없이 접근해야 함으로 스태틱이다.
	public static BbsDao getInstance() {
		if (bbsDao == null)
			bbsDao = new BbsDao();

		return bbsDao;
	}

	// 모든 게시글을 조회하는 메서드 
	public List<Bbs> selectAllBbs() {

		List<Bbs> list = new ArrayList<Bbs>();

		//SQL문 생성
		String SQL = " SELECT * FROM " + tableName;

		try {
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();

			//1. ResultSet에 대이터가 있는동안 계속 반복
			while (rs.next()) {
				//1.2데이터를 저장할 유저 객체를 생성
				Bbs bbs = new Bbs();

				//1.3 유저에 값을 대입한다.
				
				bbs.setId(rs.getString(1));			
				bbs.setTitle(rs.getString(2));
				bbs.setDate(rs.getString(3));
				bbs.setContent(rs.getString(4));
				bbs.setAvailable(rs.getInt(5));
				bbs.setCount(rs.getInt(6));
				bbs.setUserId(rs.getString(7));
				
				//1.4 값이 저장된 유저객체를 리스트에 추가한다.
				list.add(bbs);
			}

			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt, rs);
		}

		//데이터 베이스 오류 시 null값 반환
		return null;
	}
	
	// 데이터베이스에 유저를 생성하는 메서드
	int createBbs(Bbs bbs) {

		// 1. 쿼리문을 설정한다.
		String SQL = " INSERT INTO " + tableName + " (ID, TITLE, CONTENT, USERID) VALUES(BOARDID.NEXTVAL, ?, ?, ?) ";

		try {
			// 2. 해당 쿼리문을 가진 connection객체 생성
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, bbs.getTitle());
			pstmt.setString(2, bbs.getContent());
			pstmt.setString(3, bbs.getUserId());

			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		//등록 실패 
		return -1;
	}
	
//	// 게시판 조회를 처리하는 메서드
//	public int select(Bbs bbs) {
//
//		// 1. 쿼리문을 설정한다.
//		String SQL = " SELECT ID, TITLE, CDATE, COUNT, USERID FROM " + tableName + "  WHERE E_MAIL = ? ";
//		try {
//			
//			// 2. 해당 쿼리문을 가진 connection객체 생성
//			pstmt = conn.prepareStatement(SQL);
//			pstmt.setString(1, bbs.getId());
//			rs = pstmt.executeQuery();
//
//			// 3.1 결과가 존재하는지 확인
//			if (rs.next()) {
//				// 3.1.1 패스워드가 서로 일치하면 1 반환(로그인 성공)
//				if (rs.getString(1).equals(user.getPassword())) {
//					return 1;
//				}
//				// 3.1.2 불일치하면 0 반환(비밀번호 불일치)
//				else {
//					return 0;
//				}
//			}
//
//			// 3.2 결과(아이디)가 없으면 -1 반환
//			return -1;
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			close(pstmt, rs);
//		}
//
//		// 4.데이터 베이스 오류
//		return -2;
//	}


//
//	// 유저 정보를 수정해서 DB에 저장하는 메서드
//	int updateUser(User u) {
//
//		// 1. 쿼리문을 설정한다.
//		String SQL = " UPDATE " + tableName + " SET NAME = ?, PASSWORD = ?, PHONNUM = ? WHERE E_MAIL = ? ";
//
//		// 2.1 위 쿼리문을 실행해 준다. 성공시 1반환
//		try {
//			pstmt = conn.prepareStatement(SQL);
//			pstmt.setString(1, u.getName());
//			pstmt.setString(2, u.getPassword());
//			pstmt.setString(3, u.getPhonnum());
//			pstmt.setString(4, u.getE_mail());
//
//			return pstmt.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			close(pstmt);
//		}
//
//		//2.2 데이터베이스 에러 
//		return -1;
//	}
//
//	// 유저 정보를 받아오는 메서드
//	public User getUserInfo(String email) {
//		
//		//1. 쿼리문을 생성한다.
//		String SQL = " SELECT * FROM " + tableName + " WHERE E_MAIL = ? ";
//		try {
//			// 2. 해당 쿼리문을 가진 connection객체 생성
//			pstmt = conn.prepareStatement(SQL);
//			pstmt.setString(1, email);
//			rs = pstmt.executeQuery();
//			
//			// 3. 쿼리문으로 나온 결과를 받기위한 user객체 생성
//			User user = new User();
//
//			// 4.1 결과가 있다면 나온 값을 user객체에 대입 후 반환한다.
//			if (rs.next()) {
//				user.setE_mail(rs.getString(1));
//				user.setPassword(rs.getString(2));
//				user.setName(rs.getString(3));
//				user.setIn_date(rs.getString(4));
//				user.setPoint(rs.getInt(5));
//				user.setPhonnum(rs.getString(6));
//
//				return user;
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			close(rs, pstmt);
//		}
//
//		//4.2 결과가 없다면 null반환
//		return null;
//	}
//
//	// DB에 저장된 User정보를 삭제하는 메서드
//	int deleteUser(String userId) {
//		
//		// 1. 쿼리문을 설정한다.
//		String SQL = " DELETE FROM " + tableName + " WHERE E_MAIL = ? ";
//
//		try {
//			//2.1 쿼리문을 실행한다. 성공하면 1반환
//			pstmt = conn.prepareStatement(SQL);
//			pstmt.setString(1, userId);
//			return pstmt.executeUpdate();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			close(pstmt);
//		}
//
//		//2.2 실패시 -1 반환
//		return -1;
//	}
}
