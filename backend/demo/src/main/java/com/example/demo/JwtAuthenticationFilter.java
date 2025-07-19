package com.example.demo;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;


// Déclarer ce composant comme un filtre Spring qui ne s'exécute qu'une fois par requête
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;    // Utilitaire pour manipuler les JWT


    /**
     * Méthode principale qui intercepte chaque requête HTTP
     * @param request La requête entrante
     * @param response La réponse sortante
     * @param filterChain La chaîne de filtres à exécuter
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // 1. Extraction du token depuis l'en-tête Authorization
        String header = request.getHeader("Authorization");

        // 2. Vérification de la présence et du format du token
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7); // Supprime "Bearer" pour obtenir le token pur

            // 3. Validation du token
            if (jwtUtil.isTokenValid(token)) {
                // 4. Extraction de l'email depuis le token
                String email = jwtUtil.extractEmail(token);

                // 5. Création de l'objet d'authentification Spring Security
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                email,  // Principal (identité de l'utilisateur)
                                null,   // Credentials (mot de passe, non nécessaire ici)
                                new ArrayList<>()  // Authorities (rôles, vide par défaut )
                        );

                // 6. Stockage de l'authentification dans le contexte de sécurité
                SecurityContextHolder.getContext().setAuthentication(auth);


            }
        }

        // 7. Poursuite de la chaîne de filtres
        filterChain.doFilter(request, response);
    }
}