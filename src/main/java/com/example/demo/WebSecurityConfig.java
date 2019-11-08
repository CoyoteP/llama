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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	//https://ti-tomo-knowledge.hatenablog.com/entry/2018/06/11/205652
	
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/css/**", "/image/**", "/js/**", "/webjars/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/signup").permitAll().anyRequest().authenticated();
		http.formLogin().loginProcessingUrl("/auth").loginPage("/signin").failureUrl("/signin?error")
				.defaultSuccessUrl("/home", false).usernameParameter("userid").passwordParameter("password").and()
				.logout().logoutRequestMatcher(new AntPathRequestMatcher("/signout")).logoutSuccessUrl("/singin")
				.deleteCookies("JSESSIONID").invalidateHttpSession(true).permitAll();
		http.sessionManagement().invalidSessionUrl("/signin");
	}

	// ポイント3
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select userid, password, enable from users where userid = ?")
				.authoritiesByUsernameQuery(
	                       "select userid, role from users where userid = ?");
	}

	@Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
