package com.bank.credit.card.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CreditCardProcessingApplication {
	public static final Logger LOGGER = LoggerFactory.getLogger(CreditCardProcessingApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CreditCardProcessingApplication.class, args);
		LOGGER.info("Credit Card Processing Application Started");
	}

}
