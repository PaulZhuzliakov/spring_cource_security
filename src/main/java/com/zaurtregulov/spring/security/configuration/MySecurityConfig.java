package com.zaurtregulov.spring.security.configuration;

import com.zaurtregulov.spring.security.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

import javax.sql.DataSource;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);

//        UserBuilder userBuilder = User.withDefaultPasswordEncoder();
//        auth.inMemoryAuthentication()
//                .withUser(userBuilder.username("paul").password("paul").roles(Role.EMPLOYEE.name()))
//                .withUser(userBuilder.username("marina").password("marina").roles(Role.HR.name()))
//                .withUser(userBuilder.username("john").password("john").roles(Role.HR.name(), Role.MANAGER.name()));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").hasAnyRole(Role.EMPLOYEE.name(), Role.HR.name(), Role.MANAGER.name())
                .antMatchers("/hr-info").hasAnyRole(Role.HR.name())
                .antMatchers("/manager-info").hasAnyRole(Role.MANAGER.name())
                .and().formLogin().permitAll();
    }
}
