package sn.mapenda.annonces;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import sn.mapenda.annonces.Entity.Annonce;









@SpringBootApplication
public class ProjetannonceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetannonceApplication.class, args);
	}
	
	 
	@Bean
	CommandLineRunner start(RepositoryRestConfiguration restConfiguration) {
	     return args -> {
			restConfiguration.exposeIdsFor(Annonce.class);
			
			
			
			};
	}
	
	
	
	
	
	
	  

}
