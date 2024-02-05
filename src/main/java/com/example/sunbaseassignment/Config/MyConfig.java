package com.example.sunbaseassignment.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
class MyConfig {
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails userDetails = User.builder().username("PUNEETH").password(passwordEncoder().encode("abc")).roles("ADMIN").build();
        return new InMemoryUserDetailsManager(userDetails);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/auth/**")
                        .allowedOrigins("http://127.0.0.1:5500")
                        .allowedMethods("POST", "GET", "PUT", "DELETE")
                        .allowCredentials(true)
                        .maxAge(3600);

                registry.addMapping("/customer/**")
                        .allowedOrigins("http://127.0.0.1:5500")
                        .allowedMethods("POST", "GET", "PUT", "DELETE")
                        .allowCredentials(true)
                        .maxAge(3600);

//                registry.addMapping("/auth/**")
//                        .allowedOrigins("https://puneeth-gaikwad-l.github.io")
//                        .allowedMethods("POST", "GET", "PUT", "DELETE")
//                        .allowCredentials(true)
//                        .maxAge(3600);
//
//                registry.addMapping("/customer/**")
//                        .allowedOrigins("https://puneeth-gaikwad-l.github.io")
//                        .allowedMethods("POST", "GET", "PUT", "DELETE")
//                        .allowCredentials(true)
//                        .maxAge(3600);
            }
        };
    }
}
