package thowl.wiprojekt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//Edward Siebert
@SpringBootApplication
public class WiProjektApplication {

	public static void main(String[] args) {
		SpringApplication.run(WiProjektApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/*")
						.allowedOrigins("*")
						.allowedMethods("*")
						.allowedHeaders("*");
			}
		};
	}

}
