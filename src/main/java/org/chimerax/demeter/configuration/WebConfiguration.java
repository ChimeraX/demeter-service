package org.chimerax.demeter.configuration;

import lombok.AllArgsConstructor;
import org.chimerax.common.security.jwt.JWTFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 12-Apr-20
 * Time: 3:18 PM
 */

@Configuration
@AllArgsConstructor
public class WebConfiguration extends WebSecurityConfigurerAdapter {

    private JWTFilter jwtFilter;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors().and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**").permitAll()
                .antMatchers("/authenticate").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
