package com.example.demo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	//https://ti-tomo-knowledge.hatenablog.com/entry/2018/06/11/205652
	
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/css/**", "/img/**", "/js/**", "/font/**","/webjars/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//http.authorizeRequests().antMatchers("/teacher/**").hasRole("TEACHER").antMatchers("/student/**").hasRole("STUDENT").antMatchers("/signup").permitAll().anyRequest().authenticated();
		http.authorizeRequests().antMatchers("/signup","/endpoint","/topic","/login").permitAll().anyRequest().authenticated();

		http.formLogin().loginProcessingUrl("/auth").loginPage("/login").failureUrl("/login?error")
				.defaultSuccessUrl("/default", true).usernameParameter("userId").passwordParameter("password").and()
				.logout().logoutRequestMatcher(new AntPathRequestMatcher("/signout")).logoutSuccessUrl("/login")
				.deleteCookies("JSESSIONID").invalidateHttpSession(true).permitAll();
		http.sessionManagement().invalidSessionUrl("/login");
	}

	// ポイント3
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select user_id, password, enable from users where user_id = ?")
				.authoritiesByUsernameQuery(
	                       "select user_id, role from users where user_id = ?");
	}

	@Bean
    PasswordEncoder passwordEncoder() {
        //return new BCryptPasswordEncoder();
		return NoOpPasswordEncoder.getInstance();

    }
}
