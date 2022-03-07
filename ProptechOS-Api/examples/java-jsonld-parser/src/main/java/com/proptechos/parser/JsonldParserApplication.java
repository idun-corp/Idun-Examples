package com.proptechos.parser;

import com.proptechos.parser.service.JsonLdParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class JsonldParserApplication {

	public static void main(String[] args) {
		SpringApplication.run(JsonldParserApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(JsonLdParser parser) {
		return args -> {
			if (args.length != 2) {
				log.error("Root axiom path and axiom id should be provided");
				System.exit(1);
			}
			log.info("Started Axiom Parsing job");
			parser.parseAxiomHierarchy(args[0], args[1]);
			System.exit(0);
		};
	}
}
