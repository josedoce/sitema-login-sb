package com.springseguro.eumesmo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private BCryptPasswordEncoder bcryptencoder;
	
	@Autowired
	private AppUserDetailsService userDetailsService;
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(bcryptencoder)
		;
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/projetos").hasAuthority("PG_PROJETOS")
				.antMatchers("/relatorio-equipe").hasAuthority("PG_REL_EQUIPE")
				.antMatchers("/relatorio-custos").hasAuthority("PG_REL_CUSTOS")
				.anyRequest()
				.authenticated()
			.and()
			.formLogin()
				.loginPage("/entrar")
				.permitAll()
			.and()
			.logout()
				.logoutSuccessUrl("/entrar?logout")
				.permitAll()
			.and()
			.rememberMe()
				.userDetailsService(userDetailsService)
		;
	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web
           .ignoring()
           .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }
	
}
