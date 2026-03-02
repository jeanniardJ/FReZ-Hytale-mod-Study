### Story 23: Système de Quêtes Dynamique

**En tant que** joueur,
**Je veux** pouvoir accepter des quêtes proposées par des PNJ (personnages non-joueurs) ou des panneaux, les compléter et recevoir des récompenses,
**Afin de** rendre le monde plus vivant et de me donner des objectifs structurés.

---

### Critères d'Acceptation

- **CA-23.1** : Un PNJ ou un bloc spécial (ex: panneau de quêtes) peut proposer une quête au joueur via une interaction (ex: clic droit).
- **CA-23.2** : Le joueur peut accepter la quête et celle-ci apparaît dans son journal de quêtes (conceptuel, pas forcément un GUI complet).
- **CA-23.3** : La quête a des objectifs spécifiques (ex: "Collecter 10 objets X", "Éliminer 5 créatures Y", "Parler à PNJ Z").
- **CA-23.4** : Le joueur peut suivre sa progression dans la quête.
- **CA-23.5** : Une fois tous les objectifs complétés, le joueur peut "rendre" la quête (à un PNJ, un bloc) et recevoir une récompense.
- **CA-23.6** : La progression des quêtes est sauvegardée et chargée avec les données du joueur.
- **CA-23.7** : Une commande `/quests` affiche les quêtes actives du joueur.

---

### Implémentation Technique

*   **Fichiers Suggérés** :
    *   `src/main/java/com/jjeanniard/plugin/quests/QuestManager.java` : Gère les quêtes disponibles, l'assignation, la progression et la complétion.
    *   `src/main/java/com/jjeanniard/plugin/quests/Quest.java` : Classe de base pour une quête, avec son ID, son titre, sa description, ses objectifs et ses récompenses.
    *   `src/main/java/com/jjeanniard/plugin/quests/QuestObjective.java` : Interface ou classe abstraite pour les objectifs de quête (ex: `CollectObjective`, `KillObjective`, `TalkObjective`).
    *   `src/main/java/com/jjeanniard/plugin/quests/PlayerQuestData.java` : Stocke l'état des quêtes d'un joueur.
    *   `src/main/java/com/jjeanniard/plugin/quests/commands/QuestCommand.java` : Implémentation de la commande `/quests`.
    *   `src/main/java/com/jjeanniard/plugin/quests/listeners/QuestProgressListener.java` : Écouteur pour suivre la progression des objectifs (interactions, kills, collectes).
*   **Concepts Clés** :
    *   **Gestion d'État Complexe par Joueur** : La progression des quêtes est une donnée dynamique et complexe par joueur, nécessitant une gestion robuste (potentiellement via la BD externe de la Story 22).
    *   **Patterns Stratégie/Commande** : Utiliser ces patterns pour définir différents types d'objectifs et de récompenses, permettant d'ajouter facilement de nouveaux types sans modifier le code existant.
    *   **Événements Multiples** : Écouter une grande variété d'événements du jeu (interaction avec PNJ/bloc, destruction de bloc, mort d'entité, etc.) pour détecter la complétion des objectifs.
    *   **API PNJ/Entités** : Interagir avec les PNJ pour accepter/rendre les quêtes.
    *   **Sérialisation/Désérialisation** : Les objets `Quest` et `PlayerQuestData` devront être sérialisés pour la persistance.
*   **Logique** :
    1.  **`Quest`** :
        - Contient une liste de `QuestObjective` et une liste de `Reward`.
    2.  **`QuestObjective`** :
        - Interface `isCompleted(Player player)` et `onEvent(Player player, Event event)`.
        - Implémentations concrètes : `CollectItemObjective(ItemType, quantity)`, `KillMobObjective(MobType, quantity)`.
    3.  **`QuestManager`** :
        - Charge toutes les `Quest` disponibles depuis des fichiers de configuration (JSON, YAML).
        - Gère les `PlayerQuestData` pour chaque joueur (`Map<UUID, PlayerQuestData>`).
        - Méthodes `assignQuest(Player, Quest)`, `trackProgress(Player, Event)`, `completeQuest(Player, Quest)`.
    4.  **`QuestProgressListener`** :
        - Enregistrer cet écouteur.
        - Écoute les événements pertinents (ex: `BlockBreakEvent`, `EntityDeathEvent`, `PlayerInteractEvent`).
        - Pour chaque événement, appelle `QuestManager.trackProgress(player, event)` pour voir si des objectifs de quête sont mis à jour.
    5.  **`QuestCommand`** :
        - Implémente `/quests` pour afficher les quêtes actives.
        - Pourrait avoir `/quest accept <id>`, `/quest complete <id>`.
    6.  **Persistance** : Intégrer `PlayerQuestData` dans le système de persistance global (fichiers ou BD externe).

*Pour une meilleure organisation du code et la gestion des événements, consultez le `DEVELOPER_GUIDE.md`.*
*   **Références Utiles :**
    *   [Design Pattern Strategy (Refactoring Guru)](https://refactoring.guru/fr/design-patterns/strategy) (pour les objectifs/récompenses)
    *   [Design Pattern Observer (Refactoring Guru)](https://refactoring.guru/fr/design-patterns/observer) (pour la gestion des événements de progression)
    *   [Blog de Développement Hytale](https://hytale.com/news/filter/developer-blogs) (pour les concepts généraux des PNJ et de l'API Hytale).
    *   [Tutoriel Sérialisation/Désérialisation JSON en Java avec Jackson](https://www.baeldung.com/jackson) (en anglais - Les principes de sérialisation sont universels en Java).
    *   [Tutoriel Sérialisation/Désérialisation JSON en Java avec GSON](https://www.baeldung.com/gson-tutorial) (en anglais - Alternative à Jackson).