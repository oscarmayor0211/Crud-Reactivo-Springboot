package com.digitalthinking.bank;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableAutoConfiguration//tells spring start various properties settings on start
//@EnableWebFlux //if it's active, we tells to spring that we wish have full controll of webflux configuration
public class BankCustomerApplication implements WebMvcConfigurer{  
    
    
	public static void main(String[] args) {
		SpringApplication.run(BankCustomerApplication.class, args);             
	}     
        
}

