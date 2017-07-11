package com.btpn.si.employee.main;
import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@RestController
@EnableSwagger2
@ComponentScan("com.btpn.si")
@EntityScan("com.btpn.si.employee.model")
@EnableJpaRepositories("com.btpn.si.employee.repository")
public class EmployeeMain {
	
	private static ApplicationContext applicationContext;
	
	public static void main(String[] args) throws Exception {
		applicationContext = SpringApplication.run(EmployeeMain.class, args);
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.US); // Set default Locale as US
		return slr;
	}

	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasenames("i18n/messages"); // name of the resource bundle
		source.setUseCodeAsDefaultMessage(true);
		return source;
	}

	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()
          .apis(RequestHandlerSelectors.basePackage("com.btpn.si.employee.controller"))
          .paths(PathSelectors.any())                          
          .build().apiInfo(apiInfo());                                           
    }
	
	private ApiInfo apiInfo() {
		Contact contact = new Contact("BTPN", "btpn.co.id", "cs@btpn.co.id");
		ApiInfo apiInfo = new ApiInfo("Scheduler REST API",
				"Scheduler description of API.", "API TOS",
				"Terms of service", contact, "License of API",
				"API license URL");
		return apiInfo;
	}
	
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
}
