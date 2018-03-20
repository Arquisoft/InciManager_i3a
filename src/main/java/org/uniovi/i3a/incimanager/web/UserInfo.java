package org.uniovi.i3a.incimanager.web;

public class UserInfo {
	private String login;
	private String password;
	private String kind;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public UserInfo(String login, String password, String kind) {
		super();
		this.login = login;
		this.password = password;
		this.kind = kind;
	}
	
	public static String toJsonFormat(UserInfo userInfo) {
		return "{\"login\": \""+userInfo.getLogin()+"\", "+
				"\"password\": \""+userInfo.getPassword()+"\", "+
				"\"kind\": \""+userInfo.getKind()+"\", }";
	}
}
