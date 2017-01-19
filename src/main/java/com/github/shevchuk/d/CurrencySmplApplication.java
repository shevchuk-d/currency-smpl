package com.github.shevchuk.d;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class CurrencySmplApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(CurrencySmplApplication.class, args);
	}
}
