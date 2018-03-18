/*
 * This source file is part of the web-service open source project.
 *
 * Copyright (c) 2018 willy and the web-service project authors.
 * Licensed under GNU General Public License v3.0.
 *
 * See /LICENSE for license information.
 * 
 */
package org.uniovi.asw.inci_manager.web;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Instance of WebLoginController.java
 * 
 * @author 
 * @version 
 */
@EntityScan
@Controller
public class WebLoginController {
	
	@RequestMapping(value = "/")
	public String index() {
		return "login";
	}

}
