package org.uniovi.i3a.incimanager.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserInfo {
	private String login;
	private String password;
	private String kind;
	
	public static String toJsonFormat(UserInfo userInfo) {
		return "{\"login\": \""+userInfo.getLogin()+"\", "+
				"\"password\": \""+userInfo.getPassword()+"\", "+
				"\"kind\": \""+userInfo.getKind()+"\", }";
	}
}
