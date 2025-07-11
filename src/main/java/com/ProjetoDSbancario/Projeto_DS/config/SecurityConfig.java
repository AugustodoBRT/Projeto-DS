package com.ProjetoDSbancario.Projeto_DS.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ProjetoDSbancario.Projeto_DS.security.JwtAuthenticationFilter;
import com.ProjetoDSbancario.Projeto_DS.services.UsuarioDetailsService;

@Configuration
public class SecurityConfig {
	@Autowired
	private UsuarioDetailsService usuarioDetailsService;

	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
//Desabilita verificação CSRF para permitir POST com token JWT
				.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> auth.requestMatchers("/h2-console/**").permitAll() // Acesso ao H2
																									// Console
						.requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll() // Acesso
																												// ao
																												// Swagger
																												// UI
						.requestMatchers(HttpMethod.POST, "/usuarios").permitAll() // Permitir criação de usuário
						.requestMatchers(HttpMethod.POST, "/auth/login").permitAll() // Permitir endpoint de login
						.requestMatchers(HttpMethod.GET, "/usuarios").hasAnyRole("CLIENTE")
						.requestMatchers(HttpMethod.GET, "/clientes").hasAnyRole("CLIENTE") // Regras de Autorização para
																							// Clientes
						.requestMatchers(HttpMethod.GET, "/clientes/{id}").hasAnyRole("CLIENTE")
						.requestMatchers(HttpMethod.GET, "/api/conta/listar").hasAnyRole("CLIENTE")
						.requestMatchers(HttpMethod.GET, "/api/conta/listar/{id}").hasAnyRole("CLIENTE")
						.requestMatchers(HttpMethod.POST, "/api/conta").hasAnyRole("CLIENTE")
						.requestMatchers(HttpMethod.POST, "/api/conta/{id}/limite").hasAnyRole("CLIENTE")
						.requestMatchers(HttpMethod.POST, "/api/conta/{id}/chave-pix").hasAnyRole("CLIENTE")
						.requestMatchers(HttpMethod.POST, "/lancamento/pix").hasAnyRole("CLIENTE")
						.requestMatchers(HttpMethod.POST, "/lancamento/saque").hasAnyRole("CLIENTE")
						.requestMatchers(HttpMethod.POST, "/lancamento/transferencia").hasAnyRole("CLIENTE")
						.requestMatchers(HttpMethod.POST, "/lancamento/deposito").hasAnyRole("CLIENTE")
						.requestMatchers(HttpMethod.GET, "/lancamento/{id}").hasAnyRole("CLIENTE")
						.requestMatchers(HttpMethod.GET, "/historico/{id}").hasAnyRole("CLIENTE")
						.anyRequest()
						.authenticated() // Todos os outros endpoints exigem autenticação
				).headers(headers -> headers.frameOptions().disable()) // Para H2 Console
				.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(usuarioDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
}