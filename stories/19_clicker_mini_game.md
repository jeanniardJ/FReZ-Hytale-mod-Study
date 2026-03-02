### Story 19: Mini-jeu de Clic-Souris (Clicker Game)

**En tant que** joueur,
**Je veux** pouvoir participer à un mini-jeu simple basé sur le clic d'objets,
**Afin de** gagner des récompenses ou simplement me divertir.

---

### Critères d'Acceptation

- **CA-19.1** : Une commande `/clickgame start` lance une session de mini-jeu pour le joueur.
- **CA-19.2** : Le mini-jeu consiste à cliquer sur des blocs/entités spécifiques qui apparaissent pendant une durée limitée.
- **CA-19.3** : Chaque clic réussi sur un objet rapporte des points.
- **CA-19.4** : À la fin du temps imparti, le joueur reçoit une récompense basée sur son score (ex: argent virtuel, items).
- **CA-19.5** : Une commande `/clickgame stop` permet au joueur de quitter le mini-jeu.
- **CA-19.6** : Les objets du mini-jeu sont visibles uniquement par le joueur participant.

---

### Implémentation Technique

*   **Fichiers Suggérés** :
    *   `src/main/java/com/jjeanniard/plugin/clickergames/ClickerGameManager.java` : Gère les sessions de jeu, le score, les apparitions d'objets.
    *   `src/main/java/com/jjeanniard/plugin/clickergames/ClickerGameSession.java` : Représente une partie en cours pour un joueur.
    *   `src/main/java/com/jjeanniard/plugin/clickergames/ClickableObject.java` : Représente les objets sur lesquels les joueurs doivent cliquer.
    *   `src/main/java/com/jjeanniard/plugin/clickergames/commands/ClickGameCommand.java` : Implémentation des commandes `/clickgame`.
    *   `src/main/java/com/jjeanniard/plugin/clickergames/listeners/PlayerInteractListener.java` : Écouteur pour intercepter les clics du joueur.
*   **Concepts Clés** :
    *   **Gestion des Sessions de Joueurs** : Chaque joueur peut avoir sa propre session de jeu, avec son propre score et ses objets à cliquer.
    *   **Apparition Temporaire d'Entités/Blocs** : Hytale doit permettre de faire apparaître et disparaître des entités ou des blocs pour le mini-jeu. Ces objets peuvent être éphémères ou "virtuels" pour ne pas affecter le monde.
    *   **Programmation de Tâches** : Utiliser le scheduler de Hytale (voir Story 9) pour gérer le temps limité du mini-jeu et l'apparition des objets.
    *   **API des Joueurs/Inventaires** : Pour donner les récompenses.
    *   **Effets Visuels/Sonores** : (Optionnel) Ajouter des retours visuels ou sonores pour les clics réussis.
*   **Logique** :
    1.  **`ClickerGameManager`** :
        - `Map<UUID, ClickerGameSession>` pour suivre les parties des joueurs.
        - Méthodes `startGame(Player)`, `stopGame(Player)`, `handleClick(Player, ClickableObject)`.
    2.  **`ClickerGameSession`** :
        - Contient le `Player`, le `score`, le temps restant.
        - Gère l'apparition aléatoire de `ClickableObject` via des tâches planifiées.
    3.  **`ClickableObject`** :
        - Peut être une entité ou un bloc temporaire. A une position et un score.
    4.  **`ClickGameCommand`** :
        - Gère les sous-commandes `start` et `stop`.
        - Utilise `ClickerGameManager` pour démarrer/arrêter les sessions.
    5.  **`PlayerInteractListener`** :
        - Écoute les clics du joueur.
        - Si le joueur est en jeu et clique sur un `ClickableObject`, appelle `ClickerGameManager.handleClick()`.

*Pour une meilleure organisation du code et la gestion des tâches planifiées, consultez le `DEVELOPER_GUIDE.md`.*