package com.github.shevchuk.d;

import com.github.shevchuk.d.currency.service.CurrencyRESTGetter;
import org.joda.time.DateTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class CurrencySmplApplication {

	public static void main(String[] args) throws IOException {
		new CurrencyRESTGetter().readJsonForParameters("http://api.fixer.io/", new DateTime("2016-11-11"), "USD", new String[]{"EUR"});
		SpringApplication.run(CurrencySmplApplication.class, args);
	}
}
