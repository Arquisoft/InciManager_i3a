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

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import TestKit.IntegrationTest;

/**
 * Instance of RestControllerTest.java
 * 
 * @author Guillermo Facundo Colunga
 * @version
 */
@SpringBootTest(classes = { Application.class })
@RunWith(SpringJUnit4ClassRunner.class)
@Category(IntegrationTest.class)
@ActiveProfiles("test")
public class RESTControllerTest {

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
	public void restTest() throws Exception {
		String payload = "{\"login\":\"45170000A\",\"password\":\"4[[j[frVCUMJ>hU\",\"kind\":1,\"message\":{\"name\":\"Fuego en coto carcedo\",\"description\":\"Hay un fuego que se ha iniciado cerca del monte. Peligro para la población cercana\",\"tags\":[\"la\",\"le\",\"li\",\"lo\"],\"multimedia\":[\"www.imagen1.com\",\"www.imagen2.com\",\"www.imagen3.com\",\"www.imagen4.com\"],\"property-val\":{\"prop1\":\"val1\",\"prop2\":\"val2\",\"prop3\":\"val3\",\"prop4\":\"val4\"},\"comments\":[\"Please help!\"]}}";

		// We send a POST request to that URI (from http:localhost...)
		MockHttpServletRequestBuilder request = post( "/sensor-feed" ).session( session )
				.contentType( MediaType.APPLICATION_JSON )
				.content( payload.getBytes() );
		mockMvc.perform( request ).andExpect( status().isOk() );
	}

}
