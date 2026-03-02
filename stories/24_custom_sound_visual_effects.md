### Story 24: Effets Sonores et Visuels Personnalisés

**En tant que** développeur,
**Je veux** pouvoir déclencher des effets sonores (musique, sons d'ambiance, sons spécifiques) et des effets visuels (particules, lumières, explosions) personnalisés en jeu,
**Afin d'** enrichir l'expérience immersive pour les joueurs, signaler des événements ou créer une ambiance.

---

### Critères d'Acceptation

- **CA-24.1** : Une commande `/playsound <son_id> [joueur_cible] [volume] [pitch]` permet de jouer un son spécifique pour un joueur ou pour tous les joueurs à une position donnée.
- **CA-24.2** : Une commande `/spawnparticle <particule_id> <position> [nombre] [vitesse]` permet de faire apparaître des particules à une position spécifique.
- **CA-24.3** : Une commande `/createlight <position> [rayon] [couleur]` permet de créer une source de lumière temporaire ou permanente.
- **CA-24.4** : Le développeur comprend les identifiants de sons, de particules et les paramètres nécessaires via l'API Hytale.
- **CA-24.5** : Les effets peuvent être déclenchés par des événements du plugin (ex: obtention d'une quête, utilisation d'un item).

---

### Implémentation Technique

*   **Fichiers Suggérés** :
    *   `src/main/java/com/jjeanniard/plugin/effects/EffectManager.java` : Centralise les méthodes pour jouer des sons, créer des particules et des lumières.
    *   `src/main/java/com/jjeanniard/plugin/effects/commands/SoundCommand.java` : Implémentation de la commande `/playsound`.
    *   `src/main/java/com/jjeanniard/plugin/effects/commands/ParticleCommand.java` : Implémentation de la commande `/spawnparticle`.
    *   `src/main/java/com/jjeanniard/plugin/effects/commands/LightCommand.java` : Implémentation de la commande `/createlight`.
*   **Concepts Clés** :
    *   **API Sonore de Hytale** : Accéder aux sons prédéfinis du jeu ou potentiellement à des sons personnalisés si l'API le permet.
    *   **API Particules de Hytale** : Utiliser les types de particules et leurs paramètres (vitesse, dispersion, nombre).
    *   **API Lumière de Hytale** : Créer des sources de lumière dynamiques dans le monde.
    *   **Coordonnées et Orientation** : Tous les effets sont liés à des positions (`Vector3f` ou `Vector3i`) et parfois des directions.
    *   **Scopes (Portée des effets)** : Comprendre comment déclencher un effet pour un seul joueur, un groupe de joueurs, ou globalement.
*   **Logique** :
    1.  **`EffectManager`** :
        - Méthodes `playSound(Player player, String soundId, float volume, float pitch)`, `playSound(Vector3f location, String soundId, float volume, float pitch, float radius)`.
        - Méthodes `spawnParticle(Vector3f location, String particleId, int count, float speed)`, `spawnParticle(Player player, String particleId, int count, float speed)`.
        - Méthodes `createLight(Vector3i position, int radius, Color color)`, `removeLight(Vector3i position)`.
        - Ces méthodes Wrappent l'API Hytale correspondante.
    2.  **Commandes** :
        - Enregistrer les commandes dans `Study.java`.
        - Chaque commande (ex: `SoundCommand`) parse ses arguments et appelle la méthode correspondante dans `EffectManager`.
        - Les commandes peuvent inclure des options pour cibler un joueur, un rayon, etc.
*   **Références Utiles :**
    *   [Blog de Développement Hytale](https://hytale.com/news/filter/developer-blogs) (pour les concepts généraux et les annonces de l'API - la documentation spécifique sur l'API des Effets sera disponible avec le jeu).
    *   [Tutoriel Java sur les Vecteurs et la Géométrie 3D](https://www.baeldung.com/java-vectors-matrices-3d-geometry) (en anglais - Pour comprendre les concepts de `Vector3f`, `Vector3i` et les calculs spatiaux).
    *   [Exemple de l'API LibGDX pour les Effets Particulaires](https://libgdx.com/wiki/graphics/2d/particle-effects) (en anglais - Bien que spécifique à LibGDX, les principes de gestion des systèmes de particules sont très instructifs).
    *   [Exemple de l'API LibGDX pour les Sons](https://libgdx.com/wiki/audio/sound-and-music) (en anglais - Pour comprendre les principes de gestion des sons et de la musique dans les jeux).