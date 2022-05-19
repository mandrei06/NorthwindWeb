package com.sparta.northwindweb.controller;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@EnableWebSecurity
@Configuration
public class Config extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("andy").password("{noop}andy").authorities("ADMIN");
        auth.inMemoryAuthentication().withUser("callum").password("{noop}callum").authorities("ADMIN");
        auth.inMemoryAuthentication().withUser("reman").password("{noop}reman").authorities("ADMIN");
        auth.inMemoryAuthentication().withUser("neil").password("{noop}neil").authorities("ADMIN");
        auth.inMemoryAuthentication().withUser("tina").password("{noop}tina").authorities("USER");
        auth.inMemoryAuthentication().withUser("mauro").password("{noop}mauro").authorities("USER");
        auth.inMemoryAuthentication().withUser("michel").password("{noop}michel").authorities("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("customer/add/").hasAuthority("ADMIN")
                .antMatchers("/addCustomer").hasAuthority("ADMIN")
                .antMatchers("/updateCustomer").permitAll()
                .antMatchers("/customer/edit/{id}").permitAll()
                .antMatchers("/customer/delete/{id}").permitAll()
                .antMatchers("/customer").permitAll()
                .antMatchers("/customer/{id}").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").defaultSuccessUrl("/index",true).permitAll()
                .and().exceptionHandling().accessDeniedPage("/accessDenied");
    }


}
