package com.example.demo;

import com.example.demo.dto.SignupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service    // Indique que cette classe est un service Spring (logique métier)
public class UserService {

    @Autowired
    private UserRepository userRepository;  // Pour l'accès aux données

    @Autowired
    private PasswordEncoder passwordEncoder;    // Pour le hachage sécurisé des mdp


    /**
     * Créer un nouvel utilisateur avec mot de passe haché
     * @param request Contient les infos d'inscription
     * @return L'utilisateur créé
     */
    public User createUser(SignupRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setNom(request.getNom());
        return userRepository.save(user);
    }


    /**
     * Authentifie un utilisateur
     * @param email Email de l'utilisateur
     * @param password Mot de passe en clair
     * @return L'utilisateur si l'authentification réussie, null sinon
     */
    public User authenticate(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }


    /**
     * Trouve un utilisateur par email
     * @param email Email à rechercher
     * @return L'utilisateur ou null si non trouvé
     */
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    /**
     * Verifie l'existence d'un email en base
     * @param email Email à vérifier
     * @return true si l'email existe déjà
     */
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}