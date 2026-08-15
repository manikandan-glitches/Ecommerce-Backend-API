package com.example.ecommerceapi.config;


import com.example.ecommerceapi.custumFilter.jwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    jwtFilter jwtFilter;

    @Autowired
    AccessDeniedHandler acc1;

    @Autowired
    AuthenticationEntryPoint acc2;

    @Bean
    public SecurityFilterChain customSecurity(HttpSecurity http){
        http
                .csrf(new Customizer<CsrfConfigurer<HttpSecurity>>() {
                    @Override
                    public void customize(CsrfConfigurer<HttpSecurity> http) {
                        http.disable();
                    }
                })

                .httpBasic(Customizer.withDefaults())

                .authorizeHttpRequests(new Customizer<AuthorizeHttpRequestsConfigurer<org.springframework.security.config.annotation.web.builders.HttpSecurity>.AuthorizationManagerRequestMatcherRegistry>() {
                    @Override
                    public void customize(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry auth) {
                        auth.requestMatchers("/register","/error","/login").permitAll();
//                        auth.requestMatchers(HttpMethod.GET,"/prod/**").hasAuthority("product:read");
//                        auth.requestMatchers(HttpMethod.DELETE,"/prod/**").hasAuthority("product:delete");
//                        auth.requestMatchers(HttpMethod.PUT,"/prod/**").hasAuthority("product:update");
//                        auth.requestMatchers(HttpMethod.POST,"/prod/**").hasAuthority("product:write");
                        auth.anyRequest().authenticated();

                    }
                })

                .exceptionHandling(Exception -> Exception.accessDeniedHandler(acc1))
                .exceptionHandling(Exception->Exception.authenticationEntryPoint(acc2))

                .sessionManagement(new Customizer<SessionManagementConfigurer<HttpSecurity>>() {
                    @Override
                    public void customize(SessionManagementConfigurer<HttpSecurity> http) {
                        http.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                    }
                })

                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);



        return http.build();

    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserDetailsService userDetailsService){
        DaoAuthenticationProvider dau = new DaoAuthenticationProvider(userDetailsService);
        dau.setPasswordEncoder(new BCryptPasswordEncoder());
        return dau;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration auth){
        return auth.getAuthenticationManager();
    }
}
