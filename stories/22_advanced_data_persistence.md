### Story 22: Persistance Avancée des Données (Base de Données Externe)

**En tant qu'** administrateur de serveur,
**Je veux** que les données complexes de mon plugin (par exemple, les statistiques détaillées des joueurs, les configurations de zones, les quêtes en cours) soient stockées dans une base de données externe (comme MySQL ou SQLite),
**Afin de** garantir une meilleure scalabilité, des requêtes plus flexibles et une gestion plus robuste que de simples fichiers JSON.

---

### Critères d'Acceptation

- **CA-22.1** : Le plugin peut se connecter à une base de données MySQL ou SQLite configurée.
- **CA-22.2** : Au démarrage du plugin, les tables nécessaires à la persistance des données sont créées ou vérifiées.
- **CA-22.3** : Les données des joueurs (ex: solde économique de la Story 15, grade de la Story 16) sont sauvegardées dans la base de données à la déconnexion et chargées à la connexion.
- **CA-22.4** : Les données peuvent être modifiées directement dans la base de données et ces changements sont reflétés en jeu après rechargement ou redémarrage du plugin/serveur.
- **CA-22.5** : Le plugin gère les erreurs de connexion à la base de données de manière robuste et informe l'administrateur.

---

### Implémentation Technique

*   **Fichiers Suggérés** :
    *   `src/main/java/com/jjeanniard/plugin/database/DatabaseManager.java` : Gère la connexion à la BD, l'exécution des requêtes et la gestion des transactions.
    *   `src/main/java/com/jjeanniard/plugin/database/PlayerRepository.java` : Interface ou classe pour l'accès aux données spécifiques des joueurs (CRUD - Create, Read, Update, Delete).
    *   `src/main/java/com/jjeanniard/plugin/database/config/DatabaseConfig.java` : Classe pour les informations de connexion à la BD (hôte, port, utilisateur, mot de passe, nom de la BD).
    *   `src/main/java/com/jjeanniard/plugin/database/listeners/PlayerConnectionListener.java` : Écouteur pour charger/sauvegarder les données à la connexion/déconnexion.
*   **Concepts Clés** :
    *   **JDBC (Java Database Connectivity)** : L'API standard de Java pour se connecter et interagir avec des bases de données relationnelles.
    *   **Drivers de Base de Données** : Nécessité d'inclure le driver approprié (ex: `mysql-connector-java`, `sqlite-jdbc`) comme dépendance Gradle.
    *   **SQL (Structured Query Language)** : Pour créer les tables et manipuler les données.
    *   **`DataSource` et `Connection Pooling`** : Pour gérer efficacement les connexions à la base de données et optimiser les performances (ex: HikariCP, BoneCP).
    *   **Transactions SQL** : S'assurer de l'intégrité des données lors d'opérations multiples.
    *   **Patterns Repository/DAO (Data Access Object)** : Pour isoler la logique d'accès à la base de données du reste du code métier.
*   **Logique** :
    1.  **`DatabaseConfig`** : Stockée dans le fichier de configuration principal du plugin (Story 4).
    2.  **`DatabaseManager`** :
        - Initialisé au démarrage du plugin (`onLoad`).
        - Utilise `DatabaseConfig` pour établir une connexion (via `HikariCP` par exemple).
        - Méthodes pour exécuter des requêtes (ex: `executeQuery(String sql)`, `executeUpdate(String sql)`).
        - Au démarrage, exécuter des requêtes `CREATE TABLE IF NOT EXISTS` pour s'assurer que la structure de la base est prête.
    3.  **`PlayerRepository`** :
        - Dépend de `DatabaseManager`.
        - Méthodes : `createPlayer(UUID playerId, String playerName)`, `getPlayerBalance(UUID playerId)`, `updatePlayerBalance(UUID playerId, int balance)`.
        - Utilise des `PreparedStatement` pour prévenir les injections SQL.
    4.  **`PlayerConnectionListener`** :
        - Enregistrer cet écouteur.
        - À `PlayerJoinEvent` : Charger les données du joueur depuis `PlayerRepository`.
        - À `PlayerQuitEvent` : Sauvegarder les données du joueur via `PlayerRepository`.
*   **Références Utiles :**
    *   [Tutorial JDBC de base](https://www.tutorialspoint.com/jdbc/index.htm) (en anglais)
    *   [Documentation officielle MySQL Connector/J](https://dev.mysql.com/doc/connector-j/8.0/en/) (en anglais)
    *   [Documentation SQLite JDBC](https://github.com/xerial/sqlite-jdbc) (en anglais)
    *   [Explication des Patterns Repository et DAO](https://www.baeldung.com/java-dao-pattern) (en anglais, concepts transposables)
    *   [HikariCP (Connection Pooling)](https://github.com/brettwooldridge/HikariCP) (en anglais)
    *   **Ajout à `build.gradle` (exemple pour SQLite) :**
        ```groovy
        dependencies {
            // ... autres dépendances
            implementation 'org.xerial:sqlite-jdbc:3.44.1.0' // Remplacez par la dernière version
        }
        ```
    *   **Ajout à `build.gradle` (exemple pour MySQL) :**
        ```groovy
        dependencies {
            // ... autres dépendances
            implementation 'mysql:mysql-connector-java:8.0.33' // Remplacez par la dernière version
            implementation 'com.zaxxer:HikariCP:5.0.1' // Pour le connection pooling
        }
        ```