package it.vizzarro.torganizer;

import java.util.Arrays;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import it.vizzarro.torganizer.models.GameFormula;
import it.vizzarro.torganizer.models.ModeTournament;
import it.vizzarro.torganizer.models.Tournament;
import it.vizzarro.torganizer.models.TypeTournament;
import it.vizzarro.torganizer.repository.TournamentRepo;

@SpringBootApplication
public class TorganizerApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(TorganizerApplication.class);
	
	@Autowired TournamentRepo tournamentRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(TorganizerApplication.class, args);
	}

	
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

		  logger.info("Let's inspect the beans provided by Spring Boot:");
			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				 logger.info(beanName);
			}
			itializeDatabase();

		};
	}
	
	private void itializeDatabase(){
	
		tournamentRepo.save(new Tournament(null, "313231", "Tournament 1", TypeTournament.Coppie, ModeTournament.DUPLICATO, GameFormula.NoveCarteConTallone, "Alessandro Vizzarro", "Napoli", 3, 6, true, new Date()));
		tournamentRepo.save(new Tournament(null, "2356463", "Tournament 2", TypeTournament.Coppie, ModeTournament.DUPLICATO, GameFormula.NoveCarteConTallone, "Alessandro Vizzarro", "Napoli", 3, 6, true, new Date()));

	}
}
