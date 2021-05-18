package com.example.LaboBiochimie;

import com.example.LaboBiochimie.Entities.Parametre;
import com.example.LaboBiochimie.Service.ParametreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class LaboBiochimieTestApplication implements CommandLineRunner {
	public static void main(String[] args) {

		SpringApplication.run(LaboBiochimieTestApplication.class, args);
	}
	@Autowired
	private ParametreService parametreService;
	@Override
	public void run(String... args) throws Exception {
		Parametre p = parametreService.getParametre();
		if(p == null){
			parametreService.SaveParametre(new Parametre());
		}
	}

}
