package run;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class TicketsShopApp {
	
	public static void main(String[] args) {
		SpringApplication.run(TicketsShopApp.class, args);
	}

}