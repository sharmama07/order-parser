package com.unlimint.orderparser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.unlimint.orderparser.handlers.OrderHandler;

@SpringBootTest(args = "D:\\work\\workspace\\order-parser\\src\\main\\resources\\order1.csv")
@AutoConfigureMockMvc
@TestPropertySource(
  locations = "classpath:application.properties")
class OrderParserApplicationTests {

	@Autowired
	private OrderHandler orderHandler;
	@Test
	void contextLoads() {
	}

	@Test
	public void testSubmitTest() throws InterruptedException {
		orderHandler.submitFile("D:\\work\\workspace\\order-parser\\src\\main\\resources\\order1.csv");
		Thread.currentThread().sleep(5000);
		assertEquals(true, orderHandler.isOrderCompleted());
	}

}
