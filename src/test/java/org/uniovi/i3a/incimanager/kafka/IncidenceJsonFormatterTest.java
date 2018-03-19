package org.uniovi.i3a.incimanager.kafka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IncidenceJsonFormatterTest {

	HashMap<String, Object> payload;
	
	List<String> tags, additionalInfo;
	Map<String, String> properties;
	String username, password, incidenceName, description, asignee, state, location;
	Long expiration;
	
	@Before
	public void setUp() {
		
		username = "Juan";
		password = "mostsecureis123";
		incidenceName = "test1";
		description = "testing the format of json";
		asignee = "Pablo";
		state = "OPEN";
		expiration = 9231312L;
		location = "12N30W";
		
		tags = new ArrayList<String>();
		tags.add( "IMPORTANTE");
		tags.add( "PELIGROSO");
		
		additionalInfo = new ArrayList<String>();
		additionalInfo.add( "bin/img1.png");
		additionalInfo.add( "fragile");
		
		properties = new HashMap<String, String>();
		properties.put("prioridad", "alta");
		
		payload = new HashMap<String, Object>();
		payload.put("username", username);
		payload.put("password", password);
		payload.put("incidenceName", incidenceName);
		payload.put("description", description);
		payload.put("asignee", asignee);
		payload.put("state", state);
		payload.put("expiration", expiration);
		payload.put("tags", tags);
		payload.put("additional_information", additionalInfo);
		payload.put("properties", properties);
		payload.put("location", location);
	}
	
	@Test
	public void formatTest() {
						
		String expected = "{'username':'Juan',"
				+ "'password':'mostsecureis123',"
				+ "'incidence_name':'test1',"
				+ "'description':'testing the format of json',"
				+ "'asignee':'Pablo',"
				+ "'expiration':'9231312',"
				+ "'state':'OPEN',"
				+ "'location':'12N30W',"
				+ "'tags':['IMPORTANTE','PELIGROSO'],"
				+ "'additional_information':['bin/img1.png','fragile'],"
				+ "'properties':{"
					+ "'prioridad':'alta'"
					+ "}"
				+ "}";
		
		Assert.assertEquals(expected, IncidenceJsonFormatter.jsonFormatter(payload));
	}

	
}
