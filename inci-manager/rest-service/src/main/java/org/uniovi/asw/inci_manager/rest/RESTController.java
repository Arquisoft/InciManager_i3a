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

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

/**
 * Instance of RESTController.java
 * 
 * @author Guillermo Facundo Colunga
 * @version 201803152243
 */
@RestController
public class RESTController {

	@RequestMapping(value = "/sensor-feed", method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> processSensorRequest( @RequestBody Map<String, Object> payload ) {

		// Authentication of the agent that made the request.
		HttpResponse<JsonNode> authenticationResponse = 
				new AgentsConnection()
				.executeQuery( new AgentsQueryFormatter( payload ).query() );
		
		if(authenticationResponse.getStatus() != HttpStatus.OK.value()) {
			return new ResponseEntity<>( HttpStatus.UNAUTHORIZED );
		}
		
		// Process the message in the request.
		@SuppressWarnings({ "unchecked" })
		LinkedHashMap<String, Object> message = (LinkedHashMap<String, Object>) payload.get( "message" );
		message.put( "name", authenticationResponse.getBody().getObject().get( "name" ) );
		message.put( "location", authenticationResponse.getBody().getObject().get( "location" ) );
		message.put( "email", authenticationResponse.getBody().getObject().get( "email" ) );
		message.put( "id", authenticationResponse.getBody().getObject().get( "id" ) );
		message.put( "kind", authenticationResponse.getBody().getObject().get( "kind" ) );
		message.put( "kindCode", authenticationResponse.getBody().getObject().get( "kindCode" ) );
		
		System.out.println( message );
		
		
		// Send the message to Apache Kafka | Database
		
		// If all went OK return OK status.
		return new ResponseEntity<>( HttpStatus.OK );
	}
}
