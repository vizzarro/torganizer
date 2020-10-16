package it.vizzarro.torganizer;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import it.vizzarro.torganizer.models.*;
import it.vizzarro.torganizer.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TorganizerApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(TorganizerApplication.class);
	@Autowired
	TournamentRepo tournamentRepo;
	@Autowired TeamRepo teamRepo;
	@Autowired GameFormulaRepo gameFormulaRepo;
	@Autowired ModeTournamentRepo modeTournamentRepo;
	@Autowired RoundMatchRepo roundMatchRepo;

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

		Image img1 = new Image(null, "Img test", null,null,null, null);
		GameFormula gf1 = new GameFormula(null, "GF001", "Nove Carte Con Tallone", "Nove Carte Con Tallone",Game.SCOPONE,img1);
		gf1 = gameFormulaRepo.save(gf1);
		GameFormula gf2 = new GameFormula(null, "GF002", "Dieci Carte", "Dieci Carte",Game.SCOPONE,null);
		gf2 = gameFormulaRepo.save(gf2);

		ModeTournament md1 = new ModeTournament(null, "MD001", "DUPLICATO", "DUPLICATO",Game.SCOPONE,null);
		md1 = modeTournamentRepo.save(md1);
		ModeTournament md2= new ModeTournament(null, "MD002", "TRIPLICATO", "TRIPLICATO",Game.SCOPONE,null);
		md2 =  modeTournamentRepo.save(md2);
		ModeTournament md3= new ModeTournament(null, "MD003", "QUADRUPLICATO", "QUADRUPLICATO",Game.SCOPONE,null);
		md3 = modeTournamentRepo.save(md3);
		ModeTournament md4= new ModeTournament(null, "MD004", "SVEDESE", "SVEDESE",Game.SCOPONE,null);
		md4 = modeTournamentRepo.save(md4);
		ModeTournament md5= new ModeTournament(null, "MD005", "DANESE", "DANESE",Game.SCOPONE,null);
		md5 = modeTournamentRepo.save(md5);
		ModeTournament md6= new ModeTournament(null, "MD006", "HOWELL", "HOWELL",Game.SCOPONE,null);
		md6 = modeTournamentRepo.save(md6);
		ModeTournament md7= new ModeTournament(null, "MD007", "MITCHELL", "MITCHELL",Game.SCOPONE,null);
		md7 = modeTournamentRepo.save(md7);
		ModeTournament md8= new ModeTournament(null, "MD008", "SCRAMBLED", "SCRAMBLED",Game.SCOPONE,null);
		md8 = modeTournamentRepo.save(md8);


		Tournament t1 = tournamentRepo.save(new Tournament(null, "313231", "Tournament 1", TypeTournament.Coppie, md1, gf1, "Alessandro Vizzarro", "Napoli", 3, 8, true, new Date(), Game.SCOPONE));
		Tournament t2 = tournamentRepo.save(new Tournament(null, "3266646", "Tournament 2", TypeTournament.Individuale, md4, gf2, "Alessandro Vizzarro", "Napoli", 3, 8, true, new Date(), Game.SCOPONE));
		Tournament t3 = tournamentRepo.save(new Tournament(null, "437747", "Tournament 3", TypeTournament.Individuale, md6, gf2, "Alessandro Vizzarro", "Napoli", 50, 8, true, new Date(), Game.SCOPONE));
		Tournament t4 = tournamentRepo.save(new Tournament(null, "743747", "Tournament 4", TypeTournament.Coppie, md5, gf2, "Alessandro Vizzarro", "Napoli", 3, 8, true, new Date(), Game.SCOPONE));
		Tournament t5 = tournamentRepo.save(new Tournament(null, "7474747", "Tournament 5", TypeTournament.Squadre, md1, gf2, "Alessandro Vizzarro", "Napoli", 25, 8, true, new Date(), Game.SCOPONE));
		Tournament t6 = tournamentRepo.save(new Tournament(null, "5778658568", "Tournament 6", TypeTournament.Squadre, md2, gf2, "Alessandro Vizzarro", "Napoli", 3, 10, true, new Date(), Game.SCOPONE));

		Team team1 = new Team();
		team1.setCode("TEAM001");
		team1.setTournament(t1);
		team1.setType(TeamType.COUPLE);
		team1.setPartecipants(new HashSet<>());
		team1.getPartecipants().add(new Partecipant(null, "Francesco Giordano", "f.g@sfd.com"));
		team1.getPartecipants().add(new Partecipant(null, "Fabrizio Giordano", "f.g@sfd.com"));
		teamRepo.save(team1);
		Team team2 = new Team();
		team2.setCode("TEAM002");
		team2.setTournament(t1);
		team2.setType(TeamType.COUPLE);
		team2.setPartecipants(new HashSet<>());
		team2.getPartecipants().add(new Partecipant(null, "Alessandro Vizzarro", "f.g@sfd.com"));
		team2.getPartecipants().add(new Partecipant(null, "Francesca Vizzarro", "f.g@sfd.com"));
		teamRepo.save(team2);
		Team team3 = new Team();
		team3.setCode("TEAM003");
		team3.setTournament(t1);
		team3.setType(TeamType.COUPLE);
		team3.setPartecipants(new HashSet<>());
		team3.getPartecipants().add(new Partecipant(null, "Anna Vizzarro", "f.g@sfd.com"));
		team3.getPartecipants().add(new Partecipant(null, "Fernando Giordano", "f.g@sfd.com"));
		teamRepo.save(team3);
		Team team4 = new Team();
		team4.setCode("TEAM003");
		team4.setTournament(t1);
		team4.setType(TeamType.COUPLE);
		team4.setPartecipants(new HashSet<>());
		team4.getPartecipants().add(new Partecipant(null, "Rino Vizzarro", "f.g@sfd.com"));
		team4.getPartecipants().add(new Partecipant(null, "Mara Adeante", "f.g@sfd.com"));
		teamRepo.save(team4);
		Set<Match> matches = new HashSet<>();
		RoundMatch rm1 = new RoundMatch(null, t1, matches);
		matches.add(new Match(null, rm1,team1,team3));
		matches.add(new Match(null, rm1,team2,team4));

		roundMatchRepo.save(rm1);
	}

}
