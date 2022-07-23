package vttp.revision;

import java.util.Collections;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@SpringBootApplication
public class RevisionApplication {

	public static void main(String[] args) {
		// SpringApplication.run(RevisionApplication.class, args);

		SpringApplication app = new SpringApplication(RevisionApplication.class);
		
		String port = "8085";
		ApplicationArguments input = new DefaultApplicationArguments(args);

		if (input.containsOption("port")) {
			port = input.getOptionValues("port").get(0);
		}

		app.setDefaultProperties(Collections.singletonMap("server.port", port));
		app.run(args);
	}

	@Bean
	public CommonsRequestLoggingFilter log() {
		CommonsRequestLoggingFilter logger = new CommonsRequestLoggingFilter();
		logger.setIncludeClientInfo(true);
		logger.setIncludeQueryString(true);
		return logger;
		
	}

}
