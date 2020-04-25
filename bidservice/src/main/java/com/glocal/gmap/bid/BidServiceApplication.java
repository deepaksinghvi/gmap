package com.glocal.gmap.bid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories(basePackages={"com.glocal.gmap.bid"})
@EntityScan("com.glocal.gmap.bid.domain")
public class BidServiceApplication {


	public static void main(String[] args) {
		SpringApplication.run(BidServiceApplication.class, args);
	}

}