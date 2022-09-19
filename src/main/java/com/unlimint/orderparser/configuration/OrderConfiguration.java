package com.unlimint.orderparser.configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class OrderConfiguration {
    
    @Value("${workers.count}")
    private String noOfWorkers;

    @Bean("orderWorkerExecutor")
    public ExecutorService fixedThreadPool() {
		  return Executors.newFixedThreadPool(Integer.valueOf(noOfWorkers));
	  }

}
