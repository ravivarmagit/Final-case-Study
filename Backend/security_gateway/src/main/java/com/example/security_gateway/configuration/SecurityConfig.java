package com.example.security_gateway.configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.security_gateway.filter.JWTFilter;
import com.example.security_gateway.service.UserService;
import com.example.security_gateway.utility.JWTUtility;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserService userService;
	
	@Autowired
	private JWTFilter JWTFilter;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.userDetailsService(userService);

	}
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
				.authorizeRequests()
				.antMatchers("/gateway/authenticate")
				.permitAll()
				.antMatchers(HttpMethod.GET,"/**").permitAll()
				.antMatchers(HttpMethod.POST,"/user/user/add").permitAll()
//				.antMatchers(HttpMethod.DELETE,"/**").permitAll()
//				.antMatchers(HttpMethod.PUT,"/**").permitAll()
				.anyRequest()
				.authenticated()
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
				

		http.addFilterBefore(JWTFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	
	
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
		return new BCryptPasswordEncoder();
	}
	
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
		/*
		authorizeRequests()
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.and()
		.httpBasic();
		*/
//		http.csrf().disable()
//				.authorizeRequests().anyRequest().authenticated()
//				.and().httpBasic();

//		http.cors().and().csrf().disable()
//				.authorizeRequests().antMatchers(HttpMethod.POST, "/**")
//				.hasRole("Admin")
//				.antMatchers(HttpMethod.GET,"/**").permitAll()
//				.and().httpBasic();

		//.formLogin()
//		.permitAll();

//		http.authorizeRequests().antMatchers("/**").fullyAuthenticated().and
//				().httpBasic();

//	}
	
	