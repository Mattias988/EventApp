package com.fullstack.fullstack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @file FullstackApplication.java
 * @brief Główna klasa aplikacji Spring Boot.
 * @details Klasa FullstackApplication służy jako punkt wejścia dla aplikacji Spring Boot.
 * Używa SpringBootApplication do konfiguracji i uruchomienia kontekstu aplikacji Spring.
 */

@SpringBootApplication
public class FullstackApplication {
	public static void main(String[] args) {
		SpringApplication.run(FullstackApplication.class, args);
	}
}

