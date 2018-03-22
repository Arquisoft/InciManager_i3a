/*
 * This source file is part of the rest-service open source project.
 *
 * Copyright (c) 2018 willy and the rest-service project authors.
 * Licensed under GNU General Public License v3.0.
 *
 * See /LICENSE for license information.
 * 
 */
package org.uniovi.i3a.incimanager.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.uniovi.i3a.incimanager.Application;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import TestKit.IntegrationTest;

/**
 * Instance of RestControllerTest.java
 * 
 * @author
 * @version
 */
@SpringBootTest(classes = { Application.class })
@RunWith(SpringJUnit4ClassRunner.class)
@Category(IntegrationTest.class)
@ActiveProfiles("test")
public class WebControllerTest {

	@Autowired
	private WebApplicationContext context;
	private MockHttpSession session;
	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {

		this.mockMvc = MockMvcBuilders.webAppContextSetup( this.context ).build();

		session = new MockHttpSession();
	}

	@Test
	public void webTest() throws Exception {
		UserInfo userInfo = new UserInfo("45170000A","4[[j[frVCUMJ>hU","1");
		
		MockHttpServletRequestBuilder request = post("/login").session(session).param("login", userInfo.getLogin())
				.param("password", userInfo.getPassword()).param("kind", userInfo.getKind());
			mockMvc.perform(request).andExpect(status().isOk());
			
		IncidentInfo incidentInfo = new IncidentInfo("nombre","descripcion","localizacion",
				"asignado", "expiracion", "tag1, tag2, tag3", "www.imagen1.com, www.inagen2.com",
				"prop1 : val1, prop2 : val2, prop3 : val3", "estado");	
			
		request = post("/incident").session(session).param("name", incidentInfo.getName())
				.param("description", incidentInfo.getDescription()).param("location", incidentInfo.getLocation())
				.param("asignee", incidentInfo.getAsignee()).param("tags", incidentInfo.getTags())
				.param("multimedia", incidentInfo.getMultimedia()).param("properties", incidentInfo.getProperties())
				.param("state", incidentInfo.getState());
			mockMvc.perform(request).andExpect(status().isOk());
	}

}
