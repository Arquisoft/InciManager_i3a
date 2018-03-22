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
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.uniovi.i3a.incimanager.kafka.KafkaService;
import org.uniovi.i3a.incimanager.rest.AgentsConnection;
import org.uniovi.i3a.incimanager.rest.AgentsQueryFormatter;

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
public class WebController {

	@Autowired
	AgentsConnection agentsConnection;

	@RequestMapping(value = "/")
	public String index() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String setLogin(@ModelAttribute("UserInfo") UserInfo values, BindingResult result) {
		HttpResponse<JsonNode> authenticationResponse = agentsConnection
				.executeQuery(new AgentsQueryFormatter(
						values.getLogin(), values.getPassword(), values.getKind()).query());
		if (authenticationResponse.getStatus() == HttpStatus.OK.value()) {
			return "incidentForm";
		}
		return "login";
	}

	@RequestMapping(value = "/incident", method = RequestMethod.POST)
	public String setIncident(@ModelAttribute("IncidentInfo") IncidentInfo values, BindingResult result) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", values.getName());
		map.put("description", values.getDescription());
		map.put("location", values.getLocation());
		map.put("asignee", values.getAsignee());
		map.put("expiration", values.getExpiration());
		map.put("state", values.getState());
		
		List<String> tagsList = new LinkedList<String>();
		for (String tag : ((String) values.getTags()).split(",")) {
			tagsList.add(tag.trim());
		}
		map.put("tags", tagsList);
		
		List<String> infoList = new LinkedList<String>();
		for (String info : ((String) values.getMultimedia()).split(",")) {
			infoList.add(info.trim());
		}
		map.put("additional_information", infoList);
		
		Map<String, String> propsList = new HashMap<String, String>();
		for (String prop : ((String) values.getProperties()).split(",")) {
				propsList.put(prop.split(":")[0].trim(), prop.split(":")[1].trim());
		}
		map.put("properties", propsList);
		
		//Llamada a la interfaz de conexion a kafka
		System.out.println(values.toString());
		
		return "login";
	}
}
