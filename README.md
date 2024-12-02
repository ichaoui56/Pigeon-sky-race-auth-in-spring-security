# PigeonSkyRace - Basic Authentication avec Spring Security

Ce projet implémente une solution de sécurisation de l'application **PigeonSkyRace** en utilisant Spring Security avec Basic Authentication. L'objectif est de fournir une sécurité robuste tout en simplifiant la gestion des utilisateurs et des rôles.

---

## Table des Matières
- [Contexte du Projet](#contexte-du-projet)
- [Exigences Fonctionnelles](#exigences-fonctionnelles)
- [Architecture de la Sécurité](#architecture-de-la-sécurité)
- [Gestion des Exceptions](#gestion-des-exceptions)
- [Technologies et Concepts](#technologies-et-concepts)
- [Mise en Route](#mise-en-route)

---

## Contexte du Projet

L'application **PigeonSkyRace** vise à sécuriser ses fonctionnalités grâce à Spring Security en utilisant une authentification Basic. Les utilisateurs doivent s'authentifier pour accéder à la majorité des endpoints, à l'exception de l'enregistrement d'un nouveau compte.

---

## Exigences Fonctionnelles

### Sécurité des Endpoints
- Tous les endpoints sont sécurisés **sauf** celui de la création d’un nouvel utilisateur.
- L'accès aux endpoints est contrôlé selon le rôle utilisateur :
  - `ROLE_USER` : Accès aux fonctionnalités de base.
  - `ROLE_ADMIN` : Gestion des utilisateurs et des rôles.
  - `ROLE_ORGANIZER` : Gestion des compétitions.

### Gestion des Utilisateurs
- Les utilisateurs et leurs rôles sont stockés dans une base de données.
- Les rôles suivent le format `ROLE_` pour respecter les conventions de Spring Security.
- Par défaut, les utilisateurs reçoivent le rôle `ROLE_USER` lors de leur création.
- Un utilisateur ne peut avoir qu'un seul rôle, qui peut être modifié par un administrateur.

### Chargement des Utilisateurs
- Un `UserDetailsService` personnalisé est utilisé pour récupérer les informations utilisateur depuis la base de données.
- Un service dédié gère les utilisateurs de l'application.

---

## Architecture de la Sécurité

### Authentification et Autorisation
- Un `CustomAuthenticationProvider` gère l'authentification en appelant le `UserDetailsService` personnalisé.
- Les mots de passe sont encodés à l’aide de `BCryptPasswordEncoder`.
- Spring Security est configuré en mode **stateless** pour éviter la création de sessions (`JSESSIONID`).

### Profils Spring
- Un profil Spring nommé `TEST` permet une authentification simplifiée (acceptant n'importe quel mot de passe).

---

## Gestion des Exceptions

### Exceptions Personnalisées
- **`UsernameAlreadyExistsException`** : Levée lorsqu’un utilisateur avec le même nom existe déjà.

### Gestion Centralisée
- Un `@RestControllerAdvice` gère les exceptions de manière centralisée pour fournir des réponses cohérentes et expressives.

### DTO d’Erreurs
- Les erreurs sont encapsulées dans un DTO standardisé pour uniformiser les réponses.

### Gestion des Statuts
- Le `AccessDeniedHandler` est redéfini pour retourner des réponses expressives en cas d’erreur 403.

---

## Technologies et Concepts

### Technologies Utilisées
- **Spring Boot**
- **Spring Security** : Gestion de l’authentification et de l’autorisation.
- **BCryptPasswordEncoder** : Encodage des mots de passe.
- **PostgreSQL** : Base de données relationnelle pour stocker les utilisateurs et les rôles.

### Concepts Clés
- Basic Authentication
- Authentication Providers
- UserDetailsService
- PasswordEncoders
- Encodage, Chiffrement et Hashage
- Stateless Security avec `SecurityContext`

---

## Mise en Route

### Prérequis
- Java 21
- PostgreSQL
- Maven

### Installation
1. Clonez le dépôt :
    ```bash
    git clone https://github.com/votreutilisateur/pigeonskyrace-auth.git
    cd pigeonskyrace-auth
    ```

2. Configurez la base de données dans `application.yml` :
    ```yaml
    spring:
      datasource:
        url: jdbc:postgresql://localhost:5432/pigeonskyrace_db
        username: postgres
        password: motdepasse
      jpa:
        hibernate:
          ddl-auto: update
    ```

3. Installez les dépendances et démarrez l’application :
    ```bash
    mvn install
    mvn spring-boot:run
    ```

4. Accédez à l'application :
   - Endpoints sécurisés accessibles uniquement avec une authentification Basic.
   - Création de nouveaux utilisateurs via : `POST /api/auth/register`.

---

## Endpoints Principaux

### Authentification
- `POST /api/auth/register` : Inscription d'un nouvel utilisateur (accessible sans authentification).
- `POST /api/auth/login` : Connexion via Basic Authentication.

### Utilisateurs
- `GET /api/users` : Liste des utilisateurs (ROLE_ADMIN requis).
- `PUT /api/users/{userId}/role` : Modification du rôle d'un utilisateur (ROLE_ADMIN requis).

---

## Tests et Profil `TEST`
- Le profil `TEST` permet de bypasser la validation stricte des mots de passe pour faciliter les tests.
- Activez ce profil en ajoutant `--spring.profiles.active=TEST` lors du démarrage.

---

## Gestion des Exceptions

### Exemple de Réponse en Cas d'Erreur
- **403 Forbidden** :
    ```json
    {
      "timestamp": "2024-12-02T12:00:00",
      "status": 403,
      "error": "Access Denied",
      "message": "Vous n'avez pas les permissions nécessaires.",
      "path": "/api/users"
    }
    ```

- **400 Bad Request** :
    ```json
    {
      "timestamp": "2024-12-02T12:00:00",
      "status": 400,
      "error": "Bad Request",
      "message": "L'utilisateur existe déjà.",
      "path": "/api/auth/register"
    }
    ```

---

## Outils de Gestion de Projet

Ce projet est suivi et géré à l'aide de Jira. Vous pouvez consulter les tâches, fonctionnalités et bugs associés au projet en accédant au tableau Jira :

👉 [Tableau Jira PigeonSkyRace](https://ilyaschaoui2002-1713515714880.atlassian.net/jira/software/c/projects/PRS/boards/19)

---

