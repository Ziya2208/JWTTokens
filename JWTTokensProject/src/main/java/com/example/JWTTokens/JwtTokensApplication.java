package com.example.JWTTokens;

import com.example.JWTTokens.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class JwtTokensApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtTokensApplication.class, args);
	}

}
