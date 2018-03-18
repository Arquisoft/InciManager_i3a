package org.uniovi.asw.inci_manager.reactor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@ComponentScan(basePackages= {"org.uniovi.asw.inci_manager.rest",
							 "org.uniovi.asw.inci_manager.web"})
public class Startup {
	public static void main( String[] args ) {
		SpringApplication.run(Startup.class, args);
	}
}
