package it.vizzarro.torganizer.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TournamentOrganizerController {

	private static final Logger logger = LoggerFactory.getLogger(TournamentOrganizerController.class);

	@Value("${spring.application.name}")
	private String applicationName;
	
	@Value("${version}")
	private String applicationVersion;
	
	 @RequestMapping("/api/diagnostic")
	 public  String diagnostic() {
		try {
		logger.info("Diagnostic request -->"); 
		 
			return applicationName+ " v."+applicationVersion;
		}finally {
			logger.info("Diagnostic response <--"); 
		}
	 }
	
}
