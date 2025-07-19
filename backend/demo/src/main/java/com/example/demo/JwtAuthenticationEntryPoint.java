package com.example.demo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;


// Marque cette classe comme un composant Spring (géré par le conteneur IoC)
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    /**
     * Méthode appelée lorsqu'un utilisateur non authentifié tente d'accéder à une ressource sécurisée
     * @param request La requête HTTP qui  a déclenche l'exception
     * @param response La réponse HTTP à modifier
     * @param authException L'exception qui a déclenché cette entrée (contient des détails sur l'échec)
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        // Envoyer une erreur HTTP 401 (Unauthorized) avec un message personnalisé
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Non autorisé");


        /**
         * On peut aller plus loin, et faire ce qui suit :
         * 1) Logger la tentative d'accès non autorisé
         * 2) Personnaliser le format de la réponse (JSON au lieu du texte)
         * 3) Ajouter des en-têtes CORS si nécessaire
         */
    }
}