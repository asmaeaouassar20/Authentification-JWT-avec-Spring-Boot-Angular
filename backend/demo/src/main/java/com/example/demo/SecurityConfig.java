package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration  // Indique que cette classe contient la configuration de sécurité

@EnableWebSecurity  // Activer la configuration web de Spring Security


public class SecurityConfig {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthEntryPoint; // Gérer les erreurs d'authentification


    /**
     * Configuration de l'encodeur de mot de passe (BCrypt recommandé)
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCrypt est l'algorithme recommandé pour le hachage sécurisé des mots de passe
        return new BCryptPasswordEncoder();
        // Force factor 12 ( par défaut ) = 2^12 itérations (équilibre sécurité/performance)
    }


    /**
     * Configuration principale de la sécurité HTTP
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Désactiver CSRF (Cross-SIte Request Forgery) car on utilise JWT
                .csrf().disable()

                // Configuration des autorisations
                .authorizeHttpRequests(auth -> auth
                        // Permet l'accès libre aux endpoints d'authentification
                        .requestMatchers("/api/auth/**").permitAll()
                        // Toutes les autres requêtes nécessitent une authentification
                        .anyRequest().authenticated()
                )

                // Gestion des exceptions (erreurs 401)
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthEntryPoint)
                .and()

                // Politique de session sans état (stateless) pour REST API
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Ajoute notre filtre JWT personnalisé avant le filtre d'authentification standard
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    /**
     * Crée une instance du filtre JWT personnalisé
     */
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }
}