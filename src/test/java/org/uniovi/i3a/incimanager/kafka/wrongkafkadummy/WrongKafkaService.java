package org.uniovi.i3a.incimanager.kafka.wrongkafkadummy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.annotation.ManagedBean;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.jboss.logging.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@ManagedBean
@EnableAsync
public class WrongKafkaService {

	private final String TOPIC = "INCIDENCES";

	private static final Logger logger = Logger.getLogger(KafkaProducer.class);

	private KafkaTemplate<String, String> kafkaTemplate = new WrongKafkaProduceFactory().wrongkafkaTemplate();

	@Autowired
	private MongoTemplate mongoTemplate;

	@SuppressWarnings({ "unchecked" })
	public boolean sendIncidence(Map<String, Object> payload) {

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

		Map<String, Object> map;
		map = new HashMap<String, Object>();
		map.put("username", username);
		map.put("password", password);
		map.put("incidenceName", incidenceName);
		map.put("description", description);
		map.put("asignee", asignee);
		map.put("state", state);
		map.put("expiration", expiration);
		map.put("tags", tags);
		map.put("additional_information", additionalInfo);
		map.put("properties", properties);
		map.put("location", location);

		boolean success = send(TOPIC, new JSONObject(map).toString());
		return success;
	}
	private boolean send(String topic, String data) {
		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, data);
		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
			@Override
			public void onSuccess(SendResult<String, String> result) {
				logger.info("SUCCESS on sending message \"" + data + "\" to topic " + topic);
				mongoTemplate.insert(data, "incidences");
			}

			@Override
			public void onFailure(Throwable ex) {
				logger.error("ERROR on sending message \"" + data + "\", stacktrace " + ex.getMessage());
			}
		});
		
		try {
			SendResult<String, String> result = future.get();
			future.completable().complete(result);
			if(future.completable().isCompletedExceptionally())
			{
				return false;
			}
		} catch (InterruptedException | ExecutionException e) {
			return false;
		}

		
		return !future.isCancelled() && future.isDone();
	}
}
