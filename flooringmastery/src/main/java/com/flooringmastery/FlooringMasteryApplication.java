package com.flooringmastery;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.flooringmastery.controller.FlooringMasteryController;

@SpringBootApplication
public class FlooringMasteryApplication {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
		appContext.scan("com.flooringmastery");
		appContext.refresh();
		
		FlooringMasteryController controller = appContext.getBean("flooringMasteryController", FlooringMasteryController.class);
		controller.run();
	}

}
