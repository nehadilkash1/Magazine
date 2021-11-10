package com.deloitte.magazine;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.deloitte.magazine.model.Magazine;
import com.deloitte.magazine.service.MagazineService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication

public class MagazineApplication {

	public static void main(String[] args) {
		SpringApplication.run(MagazineApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(MagazineService magazineService) {
		return args -> {
			// read json and write to db
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Magazine>> typeReference = new TypeReference<List<Magazine>>() {
			};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/magazines.json");
			try {
				List<Magazine> magazines = mapper.readValue(inputStream, typeReference);
				magazineService.saveAllMagazines(magazines);
				System.out.println("Magazines Saved!");
			} catch (IOException e) {
				System.out.println("Unable to save Magazines: " + e.getMessage());
			}
		};
	}

}
