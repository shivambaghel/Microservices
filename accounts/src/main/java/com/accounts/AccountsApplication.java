package com.accounts;

import com.accounts.dto.AccountsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication

//in case of packages details are not same as current structure
/*@ComponentScans({ @ComponentScan("com.accounts.controller") })
@EnableJpaRepositories("com.accounts.repository")
@EntityScan("com.accounts.model")*/


@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl") // activating auditing-> utilize audit bean
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts microservice REST API Documentation",
				description = "Accounts microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Singh",
						email = "tutor@detail.com",
						url = "https://www.detail.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.detail.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description =  "Accounts microservice REST API Documentation",
				url = "https://www.detail.com/swagger-ui.html"
		)
)
@EnableConfigurationProperties(value = {AccountsContactInfoDto.class})
@EnableFeignClients
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
