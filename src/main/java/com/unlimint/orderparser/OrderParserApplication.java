package com.unlimint.orderparser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.unlimint.orderparser.handlers.OrderHandler;

@SpringBootApplication
public class OrderParserApplication implements CommandLineRunner{

	@Autowired
	ApplicationContext appContext;

	public static void main(String[] args) {
		SpringApplication.run(OrderParserApplication.class, args);	
	}

	@Override
    public void run(final String [] args) throws Exception {

		OrderHandler orderHandler = appContext.getBean(OrderHandler.class);

		for(String file : args) {
			// System.out.println("Processing file " + file);
			orderHandler.submitFile(file);
		}

		while(!orderHandler.isOrderCompleted()) {
			// System.out.println("Waiting for all task to be completed.....");
		}
		// System.out.println("All tasks completed.....");

    }

}
