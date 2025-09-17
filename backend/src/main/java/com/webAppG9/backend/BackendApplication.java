package com.webAppG9.backend;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		// Solo intentar cargar .env si estamos en desarrollo local
		String env = System.getenv("ENV");
		if (env == null || env.equals("dev")) {
			try {
				Dotenv dotenv = Dotenv.load();
				dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
				System.out.println(".env cargado correctamente");
			} catch (Exception e) {
				System.out.println("No se encontró .env, se usará variables de entorno del sistema");
			}
		} else {
			System.out.println("Producción: usando variables de entorno del sistema");
		}

		SpringApplication.run(BackendApplication.class, args);
	}
}
