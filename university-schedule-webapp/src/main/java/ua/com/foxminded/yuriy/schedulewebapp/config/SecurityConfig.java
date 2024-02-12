package ua.com.foxminded.yuriy.schedulewebapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	protected PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(registry ->
		registry
		.requestMatchers(permitAllMatchers())
		.permitAll()
		.requestMatchers(adminMathcers())
		.hasRole(Role.ADMIN.name())
		.anyRequest()
		.authenticated())
				.formLogin(httpSecutiryFormLoginConfigurer -> httpSecutiryFormLoginConfigurer.loginPage("/login").defaultSuccessUrl("/lessons"), true).build();
		
			
	}
}
