package org.uniovi.i3a.incimanager.kafka;

import java.util.List;
import java.util.Map;

import javax.annotation.ManagedBean;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@ManagedBean
public class KafkaService {

	private final String TOPIC = "INCIDENCES";

	private static final Logger logger = Logger.getLogger(KafkaProducer.class);

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@SuppressWarnings({"unchecked"})
	public void sendIncidence(Map<String, Object> payload) {

		// Incidence info
		String username = payload.get("login").toString();
		String password = payload.get("password").toString();
		String incidenceName = payload.get("incidenceName").toString();
		String description = payload.get("description").toString();
		String asignee = payload.get("asignee").toString();
		Long expiration = (Long) payload.get("expiration");
		String state = payload.get("state").toString();
		String location = payload.get("location").toString();
		List<String> tags = (List<String>) payload.get("tags");
		List<String> additionalInfo = (List<String>) payload.get("additional_information");
		Map<String, String> properties = (Map<String, String>) payload.get("properties");
		
		String json = IncidenceJsonFormatter.jsonFormatter(payload);
	
		send(TOPIC, json);
	}


	private void send(String topic, String data) {
		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, data);
		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
			@Override
			public void onSuccess(SendResult<String, String> result) {
				logger.info("Success on sending message \"" + data + "\" to topic " + topic);
				mongoTemplate.insert(data, "incidences") ;
			}

			@Override
			public void onFailure(Throwable ex) {
				logger.error("Error on sending message \"" + data + "\", stacktrace " + ex.getMessage());
			}
		});
	}
}
