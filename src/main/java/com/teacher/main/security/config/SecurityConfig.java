package com.teacher.main.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//	@Autowired
//	private BCryptPasswordEncoder pwdEncoder;

//	@Autowired
//	private UserDetailsService userDeatilsDetailsService;

//	@Autowired
//	private IUserServiceImpl iUserService;

	@Autowired
	private InvalidUserAuthEntryPoint entryPoint;

	// this below bean is created because session is stateless otherwise it
	// automatically get generated.
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {

		return authConfig.getAuthenticationManager();

	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.csrf().disable();

		http.authorizeHttpRequests().antMatchers("/auth/**", "/teacher/**", "/email/**", "/upload/**").permitAll().anyRequest()
				.authenticated().and().exceptionHandling().authenticationEntryPoint(entryPoint).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		// here, we have created session stateless so for that we need to make
		// authenticationManager manually(created ^).
		// TODO:verify user for 2nd request onwards..

		// DefaultSecurityFilterChain dSecurity = http.build();
		// return dSecurity; or directly return build() method;

		/*
		 * To protect MVC applications, Spring adds a CSRF token to each generated view.
		 * This token must be submitted to the server on every HTTP request that
		 * modifies state (PATCH, POST, PUT and DELETE — not GET). This protects our
		 * application against CSRF attacks since an attacker can't get this token from
		 * their own page. http.csrf().disable();
		 */

		return http.build();
	}

	/*
	 * @Bean public void userDetailsService(AuthenticationManagerBuilder auth)
	 * throws Exception {
	 * 
	 * UserDetails user = User.withDefaultPasswordEncoder() .username("user")
	 * .password("password") .roles("USER") .build(); return new
	 * InMemoryUserDetailsManager(user);
	 * 
	 * 
	 * auth.userDetailsService(userDeatilsDetailsService).passwordEncoder(pwdEncoder
	 * );
	 * 
	 * }
	 */
}