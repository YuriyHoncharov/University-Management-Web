package ua.com.foxminded.yuriy.schedulewebapp.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(securedEnabled = true)

public class SecurityConfig {

	private final WizardService wizardService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf().disable().cors().disable()
				.authorizeRequests(
						request -> request.antMatchers("/profile/dashboard").hasAnyRole("STUDENT", "PROFESSOR", "HEADMASTER")

								.requestMatchers(adminMatchers()).hasRole("HEADMASTER").anyRequest().permitAll())
				.exceptionHandling(
						exception -> exception.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
				.formLogin(formLoginConfigurer -> formLoginConfigurer.loginPage("/login").permitAll()
						.successHandler(customAuthenticationSuccessHandler()).loginProcessingUrl("/login"))
				.logout(logoutConf -> logoutConf.logoutSuccessUrl("/login"));
		return http.build();
	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(wizardService);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	private AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
		return new AuthenticationSuccessHandler() {

			@Override
			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
					Authentication authentication) throws IOException, ServletException {
				if (authentication.getAuthorities().stream()
						.anyMatch(authority -> authority.getAuthority().equals("ROLE_HEADMASTER"))) {
					response.sendRedirect("/profile/dashboard");
				} else {
					response.sendRedirect("/profile/dashboard");
				}
			}
		};

	}

	private RequestMatcher adminMatchers() {
		return new OrRequestMatcher(new AntPathRequestMatcher("/profile/dashboard/houses/*"),
				new AntPathRequestMatcher("/profile/dashboard/students/*"),
				new AntPathRequestMatcher("/profile/dashboard/professors/*"),
				new AntPathRequestMatcher("/profile/dashboard/lessons/*"));
	}

	private RequestMatcher permitAllMatchers() {
		return new OrRequestMatcher(new AntPathRequestMatcher("/login"));

	}
}
