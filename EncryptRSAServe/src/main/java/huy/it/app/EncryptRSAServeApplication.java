package huy.it.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 
 * @author HUYLUU
 *
 */
@SpringBootApplication
@ComponentScan({ "huy.it" })
public class EncryptRSAServeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EncryptRSAServeApplication.class, args);
	}
}
