package co.istad.s4mbanking.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final PasswordEncoder passwordEncoder;

    // 1. Define Bean: SecurityFilterChain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // Disable CSRF
        http.csrf(token -> token.disable());

        // Configure HTTP mapping URL
        http.authorizeHttpRequests(auth -> {
            auth.anyRequest().authenticated();
        });

        // Configure security mechanism
        http.httpBasic();

        // Stateless
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        return http.build();
    }

    // 2. Define Bean: AuthenticationManager
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        // Create ADMIN user
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("9999"))
                .roles("ADMIN")
                .build();

        // Create EDITOR user
        UserDetails editor = User.builder()
                .username("editor")
                .password(passwordEncoder.encode("9999"))
                .roles("EDITOR")
                .build();

        // Create AUTHOR user
        UserDetails author = User.builder()
                .username("author")
                .password(passwordEncoder.encode("9999"))
                .roles("AUTHOR")
                .build();

        manager.createUser(admin);
        manager.createUser(editor);
        manager.createUser(author);

        return manager;
    }

}
