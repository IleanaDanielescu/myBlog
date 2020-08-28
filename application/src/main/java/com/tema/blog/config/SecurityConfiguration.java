package com.tema.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	private static String REALM="MY_TEST_REALM";
	
	@Override 
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
        	.and().csrf().disable()
        	.authorizeRequests()
                .antMatchers("/swagger-ui.html",
                        "/myBlogApi", 
                        "/swagger-resources/**").permitAll()
                .antMatchers(HttpMethod.POST, "api/articles/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "api/articles/**").authenticated()
                .antMatchers(HttpMethod.PUT, "api/articles/**").authenticated()
                .antMatchers(HttpMethod.OPTIONS, "api/articles/**").permitAll()
                .antMatchers(HttpMethod.GET, "api/articles/**").permitAll()
                .and().sessionManagement()
                	.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().httpBasic()
                	.realmName(REALM)
                	.authenticationEntryPoint(getBasicAuthEntryPoint());
    }
	
	
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("blogger").password("{noop}1234").roles("ADMIN");
    }
     
      
    @Bean
    public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint(){
        return new CustomBasicAuthenticationEntryPoint();
    }
   
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }
}



