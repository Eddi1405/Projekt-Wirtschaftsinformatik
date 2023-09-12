package thowl.wiprojekt.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * An {@link ApplicationListener} that interjects messages into logs to make
 * it easier to discern when an application was started.
 *
 * @author Michael Hartmann
 * @version 14.05.2023
 */
@Component
@Slf4j
public class StartLogger implements ApplicationListener<ApplicationReadyEvent> {

	/**
	 * Logs a simple but easily discernible message at the start of the
	 * application.
	 *
	 * @param event {@link ApplicationReadyEvent} published when the
	 * application has started
	 */
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		log.info("""
				  
				::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
				                    Application started
				::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
				  
				""");
	}
}
