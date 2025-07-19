package com.example.demo;

import com.example.demo.dto.JwtResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.MessageResponse;
import com.example.demo.dto.SignupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

// Déclarer un contrôleur REST qui gère les requêtes HTTP et retourne des réponses au format JSON
@RestController

// Toutes les routes de ce contrôleur commencent par "/api/auth"
@RequestMapping("/api/auth")

// Autorise les requêtes CORS uniquement depuis http://localhost:4200 (pour Angular)
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    // Injection des dépendances (Spring gère l'instanciation)
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;    // Utilitaire pour la gestion des utilisateurs


    // endpoint POST pour l'inscription
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request) {
        // Vérifier si l'email existe déjà
        if (userService.existsByEmail(request.getEmail())) {
            // Retourner une erreur 400 si l'email est déjà utilisé
            return ResponseEntity.badRequest().body("Email déjà utilisé");
        }

        // Créer un nouvel utilisateur
        User user = userService.createUser(request);

        // Retourne une réponse 200avec un message de succès
        return ResponseEntity.ok(new MessageResponse("Utilisateur créé avec succès"));
    }


    // Endpoint POST pour la connexion
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // Authentification de l'utilisateur via le service
        User user = userService.authenticate(request.getEmail(), request.getPassword());

        if (user != null) {
            // Générer un JWT si l'authentification réussit
            String token = jwtUtil.generateToken(user.getEmail());

            // Retourner le token dans la réponse
            return ResponseEntity.ok(new JwtResponse(token));
        }

        // Retourner une erreur 400 si les identifiants sont invalides
        return ResponseEntity.badRequest().body("Identifiants invalides");
    }


    // endpoint GET pour récupérer les infos de l'utilisateur courant
    @GetMapping("/me")
    public ResponseEntity<?> currentUserName(Authentication authentication) {
        // Vérifier si l'authentication est null
        // Vérification de sécurité : l'utilisateur est-il authentifié ?
        if (authentication == null) {
            return ResponseEntity.badRequest().body("Utilisateur non authentifié");
        }

        /*try {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            System.out.println("===>===>===>  "+userDetails);
            return ResponseEntity.ok("Nom d'utilisateur: " + userDetails.getUsername());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur lors de la récupération des informations utilisateur");
        }*/

        // Vérifier si le principal est une instance de UserDetails (Spring Security)
        if (authentication.getPrincipal() instanceof UserDetails userDetails) {
            // Retourne le username (email généralement )
            return ResponseEntity.ok("Nom d'utilisateur: " + userDetails.getUsername());
        } else {
            // Fallback : afficher la représentation textuelle du principal
            return ResponseEntity.ok("Principal: " + authentication.getPrincipal().toString());
        }

    }


    // Endpoint POST pour la déconnexion (gestion côté client)
    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        // Remarque : la déconnexion réelle nécessiterait une gestion côté serveur du token
        // ici, on se contente d'une réponse positive, le client doit supprimer le token
        // Côté client supprime le token
        return ResponseEntity.ok(new MessageResponse("Déconnecté avec succès"));
    }


}