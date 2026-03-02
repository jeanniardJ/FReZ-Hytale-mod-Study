### Story 17: Système de WorldGuard Simplifié (Protection de Zones)

**En tant que** administrateur,
**Je veux** pouvoir définir des zones de protection dans le monde,
**Afin d'** empêcher les joueurs de modifier des blocs dans ces zones spécifiques.

---

### Critères d'Acceptation

- **CA-17.1** : Une commande `/protect <nom_zone>` permet à un administrateur de créer une zone de protection en utilisant deux points sélectionnés dans le monde (ex: un premier clic gauche, un second clic droit avec un outil spécial).
- **CA-17.2** : Dans une zone protégée, les joueurs non-administrateurs ne peuvent pas casser ou poser des blocs.
- **CA-17.3** : Si un joueur tente de casser/poser un bloc dans une zone protégée sans permission, un message d'erreur est affiché.
- **CA-17.4** : Les zones de protection sont persistantes et sont chargées au démarrage du serveur.
- **CA-17.5** : Une commande `/unprotect <nom_zone>` permet de supprimer une zone de protection existante.
- **CA-17.6** : Une commande `/listzones` affiche toutes les zones protégées et leurs coordonnées.

---

### Implémentation Technique

*   **Fichiers Suggérés** :
    *   `src/main/java/com/jjeanniard/plugin/worldguard/WorldGuardManager.java` : Gère la création, la suppression et la vérification des zones.
    *   `src/main/java/com/jjeanniard/plugin/worldguard/ProtectedRegion.java` : Classe pour stocker les informations d'une zone (nom, coordonnées min/max).
    *   `src/main/java/com/jjeanniard/plugin/worldguard/commands/ProtectCommand.java` : Implémentation de la commande `/protect`.
    *   `src/main/java/com/jjeanniard/plugin/worldguard/commands/UnprotectCommand.java` : Implémentation de la commande `/unprotect`.
    *   `src/main/java/com/jjeanniard/plugin/worldguard/commands/ListZonesCommand.java` : Implémentation de la commande `/listzones`.
    *   `src/main/java/com/jjeanniard/plugin/worldguard/listeners/BlockChangeListener.java` : Écouteur pour intercepter les événements de modification de bloc.
*   **Concepts Clés** :
    *   **Coordonnées et Vecteurs** : Hytale utilise des objets de coordonnées pour représenter des positions dans le monde. Une zone peut être définie par deux points extrêmes (min et max).
    *   **Gestion des Événements de Bloc** : Intercepter des événements comme `BlockBreakEvent` ou `BlockPlaceEvent`.
    *   **Persistance de Données Globales** : Les zones doivent être sauvegardées indépendamment des joueurs, probablement dans un fichier JSON dédié.
    *   **API des Worlds** : Accéder au monde (`World`) pour obtenir des informations sur les blocs et les positions.
    *   **Permissions** : Restreindre les commandes `/protect` et `/unprotect` aux administrateurs.
*   **Logique** :
    1.  **`ProtectedRegion`** : Stocke un nom, deux `Vector3i` (min et max pour la zone cubique).
    2.  **`WorldGuardManager`** :
        - Maintenir une liste de `ProtectedRegion`.
        - Méthodes `addRegion(ProtectedRegion)`, `removeRegion(String name)`, `isLocationProtected(Vector3i location)`.
        - Gérer la sélection des deux points pour la commande `/protect` (peut nécessiter un système de `PlayerSessionData` temporaire).
        - Sauvegarder/charger la liste des régions dans un fichier JSON.
    3.  **Commandes** :
        - Enregistrer les commandes dans `Study.java`.
        - `ProtectCommand` : Gérer la sélection des points (ex: en écoutant les clics avec un outil spécifique), puis créer et ajouter la région.
        - `UnprotectCommand` : Supprimer la région.
        - `ListZonesCommand` : Afficher les régions.
    4.  **`BlockChangeListener`** :
        - Enregistrer cet écouteur dans `Study.java`.
        - Dans les méthodes pour `BlockBreakEvent` et `BlockPlaceEvent`, vérifier si la position de l'événement est dans une zone protégée via `WorldGuardManager.isLocationProtected()`.
        - Si la zone est protégée et le joueur n'est pas un admin, annuler l'événement (`event.setCancelled(true)`) et envoyer un message d'erreur au joueur.

*Pour une meilleure organisation du code et la gestion des permissions, consultez le `DEVELOPER_GUIDE.md`.*