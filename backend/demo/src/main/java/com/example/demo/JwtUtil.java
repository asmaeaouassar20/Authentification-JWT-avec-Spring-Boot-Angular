package com.example.demo;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component  // déclarer cette classe comme un composant Spring (géré par le conteneur IoC)
public class JwtUtil {


    // clé secrète pour signer les tokens - RQ : En production, il faut la remplacer par une vraie clé sécurisé
    private String secret = "UneChaineSuperSecuriseeAvecAuMoins64CaracteresCar512Bits=64OctetsUneChaineSuperSecuriseeAvecAuMoins64CaracteresCar512Bits=64Octets";


    /**
     * Génère un token JWT pour un utilisateur
     * @param email  L'identifiant de l'utilisateur (sujet du token)
     * @return Le token JWT signé
     */
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)  // Identité de l'utilisateur
                .setIssuedAt(new Date())    // Date de création
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24h
                .signWith(SignatureAlgorithm.HS512, secret)     // Algorithme de signature
                .compact(); // Génère le token sous forme de chaîne
    }


    /**
     * Extrait l'email depuis un token JWT
     * @param token Le token JWT
     * @return L'email contenu dans le token
     */
    public String extractEmail(String token) {
        return Jwts.parser()
                .setSigningKey(secret)  // Configurer la clé de vérification
                .parseClaimsJws(token) // Parser et valider le token
                .getBody()  // récupérer le contenu (claims)
                .getSubject();  // Extraire le sujet (email)
    }


    /**
     * Vérifier si un token JWT est valide
     * @param token Le token à vérifier
     * @return true si le token est valide, false sinon
     */
    public boolean isTokenValid(String token) {
        try {
            Jwts.parser()
                 .setSigningKey(secret)
                 .parseClaimsJws(token);    // Lancer une exception si invalide
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}