package com.balobanov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
@EnableScheduling
public class Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}

//		SpringApplication.run(Application.class, args);
//
//		String unformatted = "/home/user/Desktop/unformatted/";
//		String formatted = "/home/user/Desktop/formatted/";
//
//		Files.walk(Paths.get(unformatted)).forEach(path -> {
//			if(!Files.isDirectory(path)){
//				File file = path.toFile();
//				try {
//					Path directory = Files.createDirectory(Paths.get(formatted + "/" + path.getFileName()));
//					Files.copy(new FileInputStream(file), Paths.get(directory+"/" + path.getFileName()));
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});