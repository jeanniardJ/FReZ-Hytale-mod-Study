### Story 16: Système de Grades/Rôles

**En tant que** administrateur,
**Je veux** pouvoir assigner des grades (ex: Membre, VIP, Modérateur) aux joueurs,
**Afin de** gérer les permissions et le statut des joueurs sur le serveur.

---

### Critères d'Acceptation

- **CA-16.1** : Un grade par défaut (ex: "Membre") est assigné à tout nouveau joueur qui se connecte.
- **CA-16.2** : Une commande `/setrank <joueur> <grade>` permet à un administrateur d'assigner un grade spécifique à un joueur.
- **CA-16.3** : Une commande `/getrank <joueur>` permet de connaître le grade actuel d'un joueur.
- **CA-16.4** : Une commande `/ranks` affiche la liste des grades disponibles.
- **CA-16.5** : Les grades sont persistants (sauvegardés et chargés à la déconnexion/connexion du joueur).
- **CA-16.6** : Seuls les administrateurs (ou les joueurs avec une permission spécifique) peuvent utiliser la commande `/setrank`.
- **CA-16.7** : Si un grade non existant est spécifié dans `/setrank`, un message d'erreur est affiché.

---

### Implémentation Technique

*   **Fichiers Suggérés** :
    *   `src/main/java/com/jjeanniard/plugin/ranks/RankManager.java` : Gère la logique des grades (assigner, récupérer, lister).
    *   `src/main/java/com/jjeanniard/plugin/ranks/PlayerRankData.java` : Classe pour stocker le grade d'un joueur.
    *   `src/main/java/com/jjeanniard/plugin/ranks/Rank.java` : Enum ou classe pour définir les grades disponibles et leurs permissions associées.
    *   `src/main/java/com/jjeanniard/plugin/ranks/commands/SetRankCommand.java` : Implémentation de la commande `/setrank`.
    *   `src/main/java/com/jjeanniard/plugin/ranks/commands/GetRankCommand.java` : Implémentation de la commande `/getrank`.
    *   `src/main/java/com/jjeanniard/plugin/ranks/commands/RanksCommand.java` : Implémentation de la commande `/ranks`.
    *   Modifier `player_data/PlayerDataStorage.java` ou créer un nouveau listener pour sauvegarder/charger les données de grade à la connexion/déconnexion (similaire à l'économie).
*   **Concepts Clés** :
    *   **Gestion des Données Persistantes** : Utiliser l'API de stockage de Hytale pour sauvegarder et charger les grades des joueurs.
    *   **Permissions** : Utiliser le système de permissions de Hytale (possiblement en lien avec la Story 11) pour restreindre l'accès à certaines commandes.
    *   **Enums/Classes pour les Grades** : Définir les grades de manière structurée pour pouvoir y associer des propriétés (nom affiché, permissions implicites).
    *   **Commandes avec Arguments** : Les commandes nécessitent de parser les arguments joueur et grade.
    *   **Validation** : S'assurer que le grade spécifié existe et que le joueur cible est valide.
*   **Logique** :
    1.  **`Rank.java`** : Définir les grades (ex: `MEMBER`, `VIP`, `MODERATOR`).
    2.  **`RankManager`** :
        - Maintenir une `Map<UUID, PlayerRankData>` pour les grades des joueurs connectés.
        - Méthodes `setPlayerRank(Player, Rank)`, `getPlayerRank(Player)`, `getAllRanks()`.
        - Gérer le grade par défaut pour les nouveaux joueurs.
    3.  **Commandes** :
        - Enregistrer les commandes dans `Study.java`.
        - `SetRankCommand` : Vérifier les permissions de l'expéditeur, parser les arguments, valider le grade, puis utiliser `RankManager` pour assigner le grade.
        - `GetRankCommand` : Parser l'argument joueur, utiliser `RankManager` pour récupérer le grade et l'afficher.
        - `RanksCommand` : Lister tous les grades disponibles via `RankManager`.
    4.  **Persistance** :
        - Intégrer la sauvegarde/chargement des grades dans le système de gestion des données joueur existant (ou en créer un nouveau).

*Pour une meilleure organisation du code et la gestion des permissions, suivez le `DEVELOPER_GUIDE.md`.*