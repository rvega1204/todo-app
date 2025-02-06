package com.rvg.springboot.myfirstwebapp.security;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;;

/**
 * SpringSecurityConfiguration class configures the security settings for the application.
 * It defines beans for user details management, password encoding, and HTTP security.
 * 
 * - InMemoryUserDetailsManager: Manages user details in memory.
 * - PasswordEncoder: Encodes passwords using BCrypt.
 * - SecurityFilterChain: Configures HTTP security to require authentication for all requests.
 */
@Configuration
public class SpringSecurityConfiguration {
    
    // Define a bean for InMemoryUserDetailsManager
    @Bean
    public InMemoryUserDetailsManager createUserDetailsManager() {
        // Create a new user with username "rvg" and password "asdf"
        UserDetails userDetails = createNewUser("rvg", "asdf");
        // Create another new user with username "ric" and password "qwerty"
        UserDetails userDetails1 = createNewUser("ric", "qwerty");

        // Return an InMemoryUserDetailsManager initialized with the created users
        return new InMemoryUserDetailsManager(userDetails, userDetails1);
    }

    /**
     * Creates a new user with the specified username and password.
     *
     * @param username the username of the new user
     * @param password the password of the new user
     * @return UserDetails object containing the new user's details
     */
    private UserDetails createNewUser(String username, String password) {
        // Define a password encoder function that uses the getPasswordEncoder() method to encode the input password
        Function<String, String> passwordEncoder = input -> getPasswordEncoder().encode(input);

        // Build the UserDetails object with the provided username, encoded password, and roles
        UserDetails userDetails = User.builder()
                .passwordEncoder(passwordEncoder) // Set the password encoder
                .username(username) // Set the username
                .password(password) // Set the password
                .roles("USER", "ADMIN") // Assign roles to the user
                .build();

        // Return the created UserDetails object
        return userDetails;
    }

    /**
     * Creates a PasswordEncoder bean that uses the BCrypt hashing algorithm.
     * 
     * @return a PasswordEncoder that uses BCrypt
     */
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configures the security filter chain for the application.
     * 
     * @param http the HttpSecurity to modify
     * @return the configured SecurityFilterChain
     * @throws Exception if an error occurs while configuring the security filter chain
     * 
     * This configuration ensures that:
     * - All requests are authenticated.
     * - A default login form is used for authentication.
     * - CSRF protection is disabled.
     * - Frame options headers are disabled.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(auth -> auth
                .anyRequest().authenticated());
        http.formLogin(withDefaults());

        http.csrf().disable();
        http.headers().frameOptions().disable();

        return http.build();
    }

}
