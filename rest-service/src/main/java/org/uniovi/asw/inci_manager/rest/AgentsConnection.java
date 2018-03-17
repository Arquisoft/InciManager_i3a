/*
 * This source file is part of the rest-service open source project.
 *
 * Copyright (c) 2018 willy and the rest-service project authors.
 * Licensed under GNU General Public License v3.0.
 *
 * See /LICENSE for license information.
 * 
 */
package org.uniovi.asw.inci_manager.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Instance of AgentsConnection.java
 * 
 * @author
 * @version
 */
@Service
public class AgentsConnection {

	@Value("${agents.url}")
	private String service_url;

	public HttpResponse<JsonNode> executeQuery(String query) {
		try {
			HttpResponse<JsonNode> jsonResponse = Unirest.post( service_url )
					.header( "Content-Type", "application/json" )
					.body( query )
					.asJson();
			return jsonResponse;
		} catch (UnirestException e) {
			e.printStackTrace();
		}
		return null;
	}

}
