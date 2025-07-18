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

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request) {
        if (userService.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body("Email déjà utilisé");
        }

        User user = userService.createUser(request);
        return ResponseEntity.ok(new MessageResponse("Utilisateur créé avec succès"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        User user = userService.authenticate(request.getEmail(), request.getPassword());
        if (user != null) {
            String token = jwtUtil.generateToken(user.getEmail());
            return ResponseEntity.ok(new JwtResponse(token));
        }
        return ResponseEntity.badRequest().body("Identifiants invalides");
    }



    @GetMapping("/me")
    public ResponseEntity<?> currentUserName(Authentication authentication) {
        // Vérifier si l'authentication est null
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

        if (authentication.getPrincipal() instanceof UserDetails userDetails) {
            return ResponseEntity.ok("Nom d'utilisateur: " + userDetails.getUsername());
        } else {
            return ResponseEntity.ok("Principal: " + authentication.getPrincipal().toString());
        }

    }



    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        // Côté client supprime le token
        return ResponseEntity.ok(new MessageResponse("Déconnecté avec succès"));
    }


}