package myWeb;

import java.util.List;
import myWeb.*;

//유저 객체에 접근하기위한 클래스
public class User {
	private String e_mail;
	private String password;
	private String name;
	private String in_date;
	private int point;
	private String phonnum;
	
	public String getE_mail() {
		return e_mail;
	}
	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getPhonnum() {
		return phonnum;
	}
	public void setPhonnum(String phonnum) {
		this.phonnum = phonnum;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return e_mail + " " + name + " " + password + " " + in_date
				+ " " + point + " " + phonnum;
	}

	public static void main(String[] args) {

		UserDao userDao = null;
		userDao = UserDao.getInstance();

		List<User> list = userDao.selectAllUsers();
		System.out.println(list);

	}
	
}
