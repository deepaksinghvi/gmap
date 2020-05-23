package com.ms.gmap.bid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories(basePackages={"com.ms.gmap.bid"})
@EntityScan(basePackages= {"com.ms.gmap.common.domain","com.ms.gmap.bid.domain"})
@ComponentScan(basePackages={"com.ms.gmap.bid"})
public class BidServiceApplication {


	public static void main(String[] args) {
		SpringApplication.run(BidServiceApplication.class, args);
	}

}