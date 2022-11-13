package org.example.spring_app.User;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

public class UserDetailsService {
    @Configuration
    public class SpringSecurityConfig {

        private static final String ROLE_USER = "USER";
        private static final String ROLE_ADMIN = "ADMIN";

        @Bean
        public InMemoryUserDetailsManager userDetailsService() {
            // We create a password encoder
            PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
            manager.createUser(
                    User.withUsername("user").password(encoder.encode("myPassword")).roles(ROLE_USER).build()
            );
            return manager;
        }

        @Bean
        public InMemoryUserDetailsManager adminDetailsService() {
            // We create a password encoder
            PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
            manager.createUser(
                    User.withUsername("admin").password(encoder.encode("ADMIN")).roles(ROLE_ADMIN).build()
            );
            return manager;
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            return http
                    .authorizeRequests(authorize -> authorize.anyRequest().authenticated())
                    .formLogin(withDefaults())
                    .httpBasic(withDefaults())
                    .build();
        }

        @Bean
        public SecurityFilterChain filterChain2(HttpSecurity http) throws Exception {
            return http
                    .antMatcher("/api/**")
                    .authorizeRequests(authorize -> authorize.anyRequest().hasRole(ROLE_ADMIN))
                    .formLogin(withDefaults())
                    .httpBasic(withDefaults())
                    .build();
        }
    }
}
