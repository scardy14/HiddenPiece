package org.goodomen.hiddenpiece;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HiddenPieceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HiddenPieceApplication.class, args);
	}

}
