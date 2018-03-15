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

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Instance of RESTController.java
 * 
 * @author Guillermo Facundo Colunga
 * @version 201803152243
 */
@RestController
public class RESTController {
    
    @RequestMapping(value = "/sensor-feed",
	    method = RequestMethod.POST,
	    consumes = { MediaType.APPLICATION_JSON_VALUE },
	    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> processSensorRequest(@RequestBody Map<String, Object> payload) {
	System.out.println(payload);
	
	return new ResponseEntity<>(HttpStatus.OK);
    }
}
