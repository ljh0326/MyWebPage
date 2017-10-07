package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//모든 Dao의 조상
public class Dao {
	
	protected Connection conn;
	protected String tableName = "";

	protected Dao() {
		this(null, "");
	}

	protected Dao(String tableName) {
		this(null, tableName);
	}
	
	protected Dao(Connection conn, String tableName) {
		//1. 커넥션이 null이면 커넥션을 생성해준다.
		if (conn == null) {
			try {
				String DB_URL = "jdbc:oracle:thin:@127.0.0.1:1522:XE";
				String DB_USER = "student";
				String DB_PASSWORD = "1234";

				this.conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		//2. 아니면 매개변수로 들어온 conn을 커넥션으로 만들어 준다.
		else {
			this.conn = conn;
		}
		this.tableName = tableName;
		/* 메서드 완성 */
	}

	protected void rollback() {
		if (conn != null) {
			try {
				conn.rollback();
			} catch (SQLException e) {

			}
		}
	}

	//AutoCloseable이 구현된 모든 객체가 들어올 수 있으면 들어온 모든객체를 닫아준다.
	protected void close(AutoCloseable... acs) {
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
