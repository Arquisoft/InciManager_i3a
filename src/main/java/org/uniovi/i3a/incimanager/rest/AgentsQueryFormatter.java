/*
 * This source file is part of the rest-service open source project.
 *
 * Copyright (c) 2018 willy and the rest-service project authors.
 * Licensed under GNU General Public License v3.0.
 *
 * See /LICENSE for license information.
 * 
 */
package org.uniovi.i3a.incimanager.rest;

import java.util.Map;

/**
 * Instance of AgentsQueryFormatter.java
 * 
 * @author
 * @version
 */
public class AgentsQueryFormatter {

	private static final String BASE_QUERY = "{\"login\":\"%s\", \"password\":\"%s\", \"kind\":\"%s\"}";

	private String userName, password, kind;

	public AgentsQueryFormatter( String userName, String password, String kind ) {
		this.userName = userName;
		this.password = password;
		this.kind = kind;
	}

	public AgentsQueryFormatter( Map<String, Object> payload ) {
		this( (String) payload.get( "login" ),
			  (String) payload.get( "password" ),
			  Integer.toString( (int) payload.get( "kind" ))
			);
	}

	public String query() {
		return String.format( BASE_QUERY, this.userName, this.password, this.kind );
	}

}
