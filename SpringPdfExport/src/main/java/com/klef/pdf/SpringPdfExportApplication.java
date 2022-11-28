package com.klef.pdf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringPdfExportApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringPdfExportApplication.class, args);
		System.out.println("PDF is Exporting");
		System.out.println("Excel is exporting");
	}

}
