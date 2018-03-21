package org.uniovi.i3a.incimanager.kafka.wrongkafkadummy;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

public class WrongKafkaProduceFactory {

	public ProducerFactory<String, String> WrongProducerFactory() {
		return new DefaultKafkaProducerFactory<>(producerConfigs());
	}

	public Map<String, Object> producerConfigs() {
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9090");
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
		return props;
	}

	public KafkaTemplate<String, String> wrongkafkaTemplate() {
		return new KafkaTemplate<String, String>(WrongProducerFactory());
	}

}
