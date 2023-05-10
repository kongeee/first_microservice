package com.kitaplik.libraryservice;

import com.kitaplik.libraryservice.client.RetreiveMessageErrorDecoder;
import feign.Logger;
import feign.codec.ErrorDecoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class LibraryServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(LibraryServiceApplication.class, args);
	}

	/* FEIGN CLIENT ERROR HANDLING = fault tolarance den vzgecilirse bu acilmalidir
	* Eger bu tekrar calisitirlmak istenirse BookService clienttaki @CircuitBreaker ve fallbackMethod kalidirilmali yoksa ezer bunu

	@Bean
	public ErrorDecoder errorDecoder() {
		return new RetreiveMessageErrorDecoder(); //olusturulan custom error decoder beani
	}
	@Bean
	Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	}
	*/

}
