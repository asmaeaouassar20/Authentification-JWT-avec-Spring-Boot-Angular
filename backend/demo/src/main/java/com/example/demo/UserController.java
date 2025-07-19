package com.example.demo;

import com.example.demo.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Déclarer un contrôleur REST qui renvoie des réponses au format JSON

@RequestMapping("/api/user")  // Toutes les routes de ce contrôleur commencent par "/api/user"

@CrossOrigin(origins = "http://localhost:4200") // Autorise les requêtes cross-origin depuis le frontend Angular en développement
public class UserController {

    @Autowired
    private UserService userService;    // Service pour la gestion des utilisateurs


    /**
     * Endpoint pour récupérer le profil de l'utilisateur connecté
     * @param authentication Objet Spring Security contenant les infos d'authentification
     * @return ResponseEntity contenant les données du profil ou une erreur
     */

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(Authentication authentication) {

        // Récupérer l'email depuis le contexte de sécurité
        String email = authentication.getName();

        // Recherche de l'utilisateur en base
        User user = userService.findByEmail(email);

        // Vérification de l'existence de l'utilisateur
        if (user != null) {
            // Construction de la réponse
            return ResponseEntity.ok(new UserResponse(user.getId(), user.getEmail(), user.getNom()));
        }
        return ResponseEntity.notFound().build();
    }
}