package org.uniovi.i3a.incimanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Hello world!
 *
 */
@EnableDiscoveryClient
@SpringBootApplication
public class Service {
    public static void main(String[] args) {
	SpringApplication.run(Service.class, args);
    }
}
