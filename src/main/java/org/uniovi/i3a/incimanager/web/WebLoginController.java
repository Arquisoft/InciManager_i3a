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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.uniovi.i3a.incimanager.rest.AgentsQueryFormatter;
import org.uniovi.i3a.incimanager.rest.AgentsConnection;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

import net.minidev.json.JSONObject;

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

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String setLogin(String login, String password, String kind) {
		HttpResponse<JsonNode> authenticationResponse = agentsConnection
				.executeQuery(new AgentsQueryFormatter(login, password, kind).query());
		if (authenticationResponse.getStatus() == HttpStatus.OK.value()) {
			return "incidentForm";
		}
		return "login";
	}

	@RequestMapping(value = "/incident", method = RequestMethod.POST)
	public String setIncident(String IncidenceName, String description, String asignee, String expiration, String state,
			String tags, String additional_information, String properties) {
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("IncidenceName", IncidenceName);
		values.put("description", description);
		values.put("asignee", asignee);
		values.put("expiration", expiration);
		values.put("state", state);
		
		List<String> tagsList = new LinkedList<String>();
		for (String tag : tags.split(",")) {
			tagsList.add(tag);
		}
		values.put("tags", tagsList);
		
		List<String> infoList = new LinkedList<String>();
		for (String info : additional_information.split(",")) {
			infoList.add(info);
		}
		values.put("additional_information", infoList);
		
		Map<String, String> propsList = new HashMap<String, String>();
		for (String prop : properties.split(",")) {
			propsList.put(prop.split(":")[0], prop.split(":")[1]);
		}
		values.put("properties", propsList);
		
		System.out.println(values.toString());
		return "login";
	}
}
