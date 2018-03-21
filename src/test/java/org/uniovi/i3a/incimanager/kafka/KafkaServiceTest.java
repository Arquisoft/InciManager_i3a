package org.uniovi.i3a.incimanager.kafka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.uniovi.i3a.incimanager.Application;
import org.uniovi.i3a.incimanager.kafka.wrongkafkadummy.WrongKafkaService;

import TestKit.IntegrationTest;

@SpringBootTest(classes = { Application.class})
@RunWith(SpringJUnit4ClassRunner.class)
@Category(IntegrationTest.class)
@ActiveProfiles("test")
public class KafkaServiceTest {

	@Autowired
	KafkaService kafka;

	@Autowired
	WrongKafkaService wrongKafka;
	
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
		payload.put("login", username);
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
	public void testKafkaEnqueue()
	{   
		Assert.assertTrue( kafka.sendIncidence(payload) );
	}
	
	@Test
	public void testKafkaNotEnqueue()
	{   
		Assert.assertFalse( wrongKafka.sendIncidence(payload) );
	}
	
	

}
