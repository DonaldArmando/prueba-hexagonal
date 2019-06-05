package co.com.parqueadero.validaciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "co.com.parqueadero")
@SpringBootApplication
public class ParqueaderoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParqueaderoApplication.class, args);
	}

}
