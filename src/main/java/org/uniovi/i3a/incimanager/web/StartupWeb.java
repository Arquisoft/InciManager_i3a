package org.uniovi.i3a.incimanager.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@ComponentScan(basePackages= {"org.uniovi.asw.inci_manager.web"})
public class StartupWeb {
	public static void main( String[] args ) {
		SpringApplication.run(StartupWeb.class, args);
	}
}
