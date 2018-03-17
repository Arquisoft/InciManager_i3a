package org.uniovi.asw.inci_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication(scanBasePackages = "org.uniovi.asw")
public class Application {
	public static void main( String[] args ) {
		SpringApplication.run( Application.class, args );
	}
}
