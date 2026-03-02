### Story 21: Intégration d'une Interface Graphique (GUI / Inventaire Virtuel)

**En tant que** joueur,
**Je veux** pouvoir interagir avec une interface graphique personnalisée (par exemple, un inventaire virtuel) pour gérer des options de plugin ou accéder à des fonctionnalités spécifiques,
**Afin de** rendre l'utilisation du plugin plus intuitive et visuelle.

---

### Critères d'Acceptation

- **CA-21.1** : Une commande `/menu` (ou un clic droit sur un objet spécifique) ouvre une interface graphique personnalisée pour le joueur.
- **CA-21.2** : L'interface graphique a un titre distinctif (ex: "Menu du Plugin").
- **CA-21.3** : L'interface contient au moins un bouton/icône cliquable.
- **CA-21.4** : Cliquer sur le bouton/icône dans l'interface déclenche une action (ex: un message dans le chat, l'ouverture d'un sous-menu).
- **CA-21.5** : Fermer l'interface graphique ramène le joueur à son écran de jeu normal.

---

### Implémentation Technique

*   **Fichiers Suggérés** :
    *   `src/main/java/com/jjeanniard/plugin/gui/GUIManager.java` : Gère la création, l'ouverture et l'écoute des interactions pour les GUIs.
    *   `src/main/java/com/jjeanniard/plugin/gui/CustomGUI.java` : Représente une interface graphique spécifique, avec sa taille, son titre et les items qu'elle contient.
    *   `src/main/java/com/jjeanniard/plugin/gui/items/GUIItem.java` : Classe pour les éléments cliquables dans le GUI (icônes, boutons).
    *   `src/main/java/com/jjeanniard/plugin/gui/commands/MenuCommand.java` : Implémentation de la commande `/menu`.
    *   `src/main/java/com/jjeanniard/plugin/gui/listeners/PlayerInteractListener.java` : Écouteur pour détecter les clics dans les GUIs.
*   **Concepts Clés** :
    *   **API d'Inventaire (Hytale)** : Hytale exposera des APIs pour créer des inventaires virtuels (containers) qui peuvent être affichés aux joueurs. Ces inventaires sont souvent basés sur le concept d'une grille d'emplacements (`slots`).
    *   **ItemStack et Métadonnées** : Les icônes dans le GUI seront des `ItemStack` (objets en jeu) auxquels on pourra associer des noms, des descriptions (`lore`) et des données personnalisées pour identifier leur fonction.
    *   **Événements de Clic d'Inventaire (Hytale)** : Écouter des événements spécifiques qui se déclenchent lorsqu'un joueur clique dans un inventaire virtuel (ex: `InventoryClickEvent`).
    *   **Callbacks/Actions** : Associer une action (`Runnable` ou interface personnalisée) à chaque `GUIItem` pour définir ce qui se passe lors d'un clic.
*   **Logique** :
    1.  **`CustomGUI`** :
        - Contient une instance de `Inventory` (API Hytale) de taille définie (multiples de 9).
        - Une `Map<Integer, GUIItem>` pour mapper les emplacements (`slots`) aux éléments cliquables.
        - Méthodes `setItem(int slot, GUIItem item)`, `open(Player player)`.
    2.  **`GUIItem`** :
        - Contient l'`ItemStack` à afficher et l'`Action` à exécuter lors du clic.
    3.  **`GUIManager`** :
        - Gère les instances de `CustomGUI` créées.
        - `openGUI(Player player, CustomGUI gui)` pour afficher un GUI au joueur.
    4.  **`GUIListener`** :
        - Enregistrer cet écouteur.
        - Intercepter `InventoryClickEvent`. Vérifier si l'inventaire cliqué est un de nos `CustomGUI`.
        - Si oui, annuler l'événement (`event.setCancelled(true)`) pour empêcher le déplacement d'items.
        - Exécuter l'`Action` associée au `GUIItem` cliqué.
    5.  **`MenuCommand`** :
        - Crée une instance de `CustomGUI` (ex: avec un titre "Mon Super Menu", taille 9).
        - Ajoute des `GUIItem` aux emplacements (ex: un `ItemStack` de plume pour "Afficher Stats", exécutant une action `player.sendMessage("Vos stats...")`).
        - Ouvre le `CustomGUI` pour le joueur via `GUIManager`.

*Pour une meilleure organisation du code, consultez le `DEVELOPER_GUIDE.md`.*
*   **Références Utiles :**
    *   [Blog de Développement Hytale](https://hytale.com/news/filter/developer-blogs) (pour les concepts généraux et les annonces de l'API - la documentation spécifique sur l'API des GUI/Inventaires sera disponible avec le jeu).
    *   [Tutoriel Java sur les interfaces graphiques](https://docs.oracle.com/javase/tutorial/uiswing/components/index.html) (en anglais - Bien que l'API soit Swing/JavaFX, les principes de conception de GUI comme les composants, les événements de clic, la disposition, sont universels en Java).
    *   [Vidéo : Créer un menu GUI interactif en Java](https://www.youtube.com/watch?v=kYI6j2zF300) (en anglais - Les concepts généraux de la gestion d'événements et de la structure de code pour un GUI en Java sont pertinents).