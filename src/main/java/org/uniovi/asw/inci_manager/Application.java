package org.uniovi.asw.inci_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan({"org.uniovi.asw.inci_manager.kafka-service",
				"org.uniovi.asw.inci_manager.rest-service",
				"org.uniovi.asw.inci_manager.web-service"})
public class Application {
	public static void main( String[] args ) {
		SpringApplication.run( Application.class, args );
	}
}
