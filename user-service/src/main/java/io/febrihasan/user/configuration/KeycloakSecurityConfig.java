package io.febrihasan.user.configuration;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.session.HttpSessionEventPublisher;

/**
 * @author febrihasan
 */
@EnableWebSecurity
@KeycloakConfiguration
@Import(KeycloakSpringBootConfigResolver.class)
public class KeycloakSecurityConfig extends KeycloakWebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
        auth.authenticationProvider(keycloakAuthenticationProvider);
    }

    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(
                new SessionRegistryImpl()
        );
    }

    /**
     * Required to support Session invalidation on backchannel logout!
     */
    @Bean
    protected ServletListenerRegistrationBean<HttpSessionEventPublisher> servletListenerRegistrationBean() {
        return new ServletListenerRegistrationBean<>(httpSessionEventPublisher());
    }

    /**
     * This bean for session management. Specially if we want to control
     * the concurrent session-control support with spring security
     */
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http
                    .authorizeRequests()
                    .antMatchers("/user")
                    .permitAll()
                .and()
                    .authorizeRequests()
                    .anyRequest()
                    .authenticated();

        http.csrf().disable();
    }

    /*@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                    .authorizeRequests()
                    .antMatchers("/user")
                    .permitAll()
                .and()
                    .authorizeRequests()
                    .anyRequest()
                    .authenticated();

        http.csrf().disable();

        return http.build();
    }*/

}
