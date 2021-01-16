package com.brodacki.security.config;

import com.brodacki.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@Configuration
public class webSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder getPasswordEncoder(){
    return new BCryptPasswordEncoder();
    }

    private UserDetailsService userDetailsService;

    @Autowired
    public webSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    User userAdmin =
//        new User(
//            "Jan",
//            getPasswordEncoder().encode("Jan123"),
//            Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN")));
//
//        User userUser =
//                new User(
//                        "Karol",
//                        getPasswordEncoder().encode("Karol123"),
//                        Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
//
//        auth.inMemoryAuthentication().withUser(userUser);
//        auth.inMemoryAuthentication().withUser(userAdmin);

        auth.userDetailsService(userDetailsService);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/forAdmin").hasRole("ADMIN")
                .antMatchers("/forUser").hasRole("USER")
                .antMatchers("/forAll").permitAll()
                .and()
                .csrf().disable()
                //.and()
                //.formLogin().permitAll()
               // .and()
                .logout().logoutSuccessUrl("/forAll");

    }
}