package service.aplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

import service.aplication.config.property.ApiProperty;

@SpringBootApplication(scanBasePackages = {"service.aplication"})
@EnableConfigurationProperties(ApiProperty.class)
@EnableAsync
public class Application {
	
	private static ApplicationContext APPLICATION_CONTEXT;

    public static void main(String[] args) throws Exception {
    	APPLICATION_CONTEXT = SpringApplication.run(Application.class);
    }
    
    public static <T> T getBean(Class<T> type) {
		return APPLICATION_CONTEXT.getBean(type);
	}
}