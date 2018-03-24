package org.uniovi.i3a.incimanager.kafka;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.annotation.ManagedBean;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.jboss.logging.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@ManagedBean
@EnableAsync
public class KafkaService implements IKafkaService{

	@Value("${kafka.topic}")
	private String TOPIC;
	@Value("${incidence.defaults.state}")
	private String state;

	private static final Logger logger = Logger.getLogger(KafkaProducer.class);

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	private MongoTemplate mongoTemplate;

	@SuppressWarnings({ "unchecked" })
	public boolean sendIncidence(Map<String, Object> payload) {
		Map<String, Object> map;
		map = new HashMap<String, Object>();
		
		//Check non null mandatory fields.
		if( payload.get("login") == null )
			return false;
		if( payload.get("incidenceName") == null )
			return false;
		
		//Set default state if none.
		if( payload.get("state") != null )
			state = payload.get("state").toString();
		
		//Store relevant information only
		map.put("username", payload.get("login") );
		map.put("password", payload.get("password") );
		map.put("name", payload.get("incidenceName") );
		map.put("description", payload.get("description"));
		map.put("asignee", payload.get("asignee") );
		map.put("state", state );
		map.put("expiration", payload.get("expiration"));
		map.put("tags", payload.get("location"));
		map.put("multimedia", payload.get("additional_information"));
		map.put("property_value", payload.get("properties"));
		map.put("location", payload.get("location"));
		
		
		return send(TOPIC, new JSONObject(map).toString());
		
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
