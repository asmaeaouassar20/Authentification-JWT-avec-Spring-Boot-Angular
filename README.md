Ce projet met en œuvre  la base de l'authentification dans une application web, en combinant la puissance de Spring Boot pour le backend, Spring Security pour la sécurité, JWT (JSON Web Tokens) pour l’authentification sans état,  et Angular pour une interface frontend moderne et réactive..

Le backend expose des API REST sécurisées avec JWT, tandis que le frontend Angular consomme ces API via des services HTTP.


### Technologies utilisées
- Spring Boot
- Spring Security
- Spring Data JPA
- MySQL 
- Angular
- Bootstrap
- Tailwind CSS


### Test à l’aide de Postman pour les requêtes backend
#### Inscription d'un nouvel utilisateur
<img width="1605" height="505" alt="image" src="https://github.com/user-attachments/assets/137d64b2-389f-47b0-8471-dac65740b2fe" />

<br><br><br><br>

#### L'utilisateur est ajouté dans la base de données
<img width="602" height="151" alt="image" src="https://github.com/user-attachments/assets/6ec99f0a-45f5-486c-bb9e-245840916a9d" />

<br><br><br><br>


#### Connexion de l'utilisateur avec l'email et le mot de passe, génère un token
<img width="1608" height="526" alt="image" src="https://github.com/user-attachments/assets/65899963-0e67-4d10-96f4-a8f3db226d00" />

<br><br><br><br>

#### Utilisation du token pour accéder au profil
<img width="1692" height="465" alt="image" src="https://github.com/user-attachments/assets/78c79f43-152d-4251-bafb-3135d0a8d477" />

<br><br><br><br>

### Test avec un navigateur pour l’interface utilisateur
#### Lors du lancement de l'application, la première page affichée est celle de connexion, accessible à l'adresse suivante : http://localhost:4200/login.

##### L’inscription d’un nouvel utilisateur se fait via l’interface disponible à l’adresse : http://localhost:4200/signup
<img width="1387" height="707" alt="image" src="https://github.com/user-attachments/assets/309e3bfb-317c-4de6-92f2-1cfb5b702177" />

<br><br><br><br>
##### L’utilisateur *algostyle* est ajouté dans la base de données
<img width="620" height="173" alt="image" src="https://github.com/user-attachments/assets/d3a2de37-5982-4cf6-8a64-3a7d67e769d6" />

<br><br><br><br>

##### L'utilisateur *algostyle* peut se connecter maintenant, et il sera redirégé vers cette page
<img width="1167" height="627" alt="image" src="https://github.com/user-attachments/assets/3734baa0-955a-4248-a56c-ecb3b49c8f22" />




