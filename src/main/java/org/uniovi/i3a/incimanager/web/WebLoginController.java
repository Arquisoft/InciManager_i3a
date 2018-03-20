/*
 * This source file is part of the web-service open source project.
 *
 * Copyright (c) 2018 willy and the web-service project authors.
 * Licensed under GNU General Public License v3.0.
 *
 * See /LICENSE for license information.
 * 
 */
package org.uniovi.i3a.incimanager.web;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.uniovi.i3a.incimanager.rest.AgentsQueryFormatter;
import org.uniovi.i3a.incimanager.rest.AgentsConnection;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

/**
 * Instance of WebLoginController.java
 * 
 * @author
 * @version
 */
@EntityScan
@Controller
public class WebLoginController {

	@Autowired
	AgentsConnection agentsConnection;

	@RequestMapping(value = "/")
	public String index() {
		return "login";
	}

	@RequestMapping(value = "/login")
	public String setLogin(@RequestBody Map<String, String> values) {
		HttpResponse<JsonNode> authenticationResponse = agentsConnection
				.executeQuery(new AgentsQueryFormatter(
						values.get("login"), values.get("password"), values.get("kind")).query());
		if (authenticationResponse.getStatus() == HttpStatus.OK.value()) {
			return "incidentForm";
		}
		return "login";
	}

	@RequestMapping(value = "/incident", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	/*public String setIncident(String name, String description, String asignee, String expiration, String state,
			String tags, String additional_information, String properties) {*/
	public String setIncident(@RequestBody JSONObject json) {
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("name", json.get("name"));
		values.put("description", json.get("name"));
		values.put("location", json.get("location"));
		values.put("asignee", json.get("name"));
		values.put("expiration", json.get("name"));
		values.put("state", json.get("name"));
		
		List<String> tagsList = new LinkedList<String>();
		for (String tag : ((String) json.get("tags")).split(",")) {
			tagsList.add(tag.trim());
		}
		values.put("tags", tagsList);
		
		List<String> infoList = new LinkedList<String>();
		for (String info : ((String) json.get("multimedia")).split(",")) {
			infoList.add(info.trim());
		}
		values.put("additional_information", infoList);
		
		Map<String, String> propsList = new HashMap<String, String>();
		for (String prop : ((String) json.get("properties")).split(",")) {
			propsList.put(prop.split(":")[0].trim(), prop.split(":")[1].trim());
		}
		values.put("properties", propsList);
		
		System.out.println(values.toString());
		return "login";
	}
}
