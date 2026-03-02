### Story 18: Objets Interactifs Personnalisés

**En tant que** développeur/designer de contenu,
**Je veux** pouvoir créer des objets interactifs uniques (ex: un levier qui ouvre une porte, un interrupteur qui allume des lumières),
**Afin d'** enrichir l'expérience de jeu avec des puzzles ou des mécanismes personnalisés.

---

### Critères d'Acceptation

- **CA-18.1** : Un type d'objet interactif personnalisé (ex: "CustomLever") peut être créé via une commande administrateur `/createinteractive <type_objet> <nom_objet>`.
- **CA-18.2** : L'objet est placé dans le monde à la position actuelle du joueur ou à une position ciblée.
- **CA-18.3** : L'interaction avec cet objet (ex: clic droit sur un levier) déclenche une action prédéfinie (ex: un message dans le chat, l'ouverture d'une porte).
- **CA-18.4** : Les objets interactifs et leurs actions sont persistants et sauvegardés avec le monde.
- **CA-18.5** : Une commande `/linkinteractive <nom_objet> <action_cible>` permet de définir ou de modifier l'action déclenchée par l'objet.

---

### Implémentation Technique

*   **Fichiers Suggérés** :
    *   `src/main/java/com/jjeanniard/plugin/interactiveobjects/InteractiveObjectManager.java` : Gère la création, la persistance et l'interaction avec les objets.
    *   `src/main/java/com/jjeanniard/plugin/interactiveobjects/CustomInteractiveObject.java` : Classe de base pour un objet interactif, avec son type, sa position et son action.
    *   `src/main/java/com/jjeanniard/plugin/interactiveobjects/actions/InteractiveAction.java` : Interface ou classe abstraite pour les actions, avec des implémentations spécifiques (ex: `SendMessageAction`, `OpenDoorAction`).
    *   `src/main/java/com/jjeanniard/plugin/interactiveobjects/commands/CreateInteractiveCommand.java` : Implémentation de la commande `/createinteractive`.
    *   `src/main/java/com/jjeanniard/plugin/interactiveobjects/commands/LinkInteractiveCommand.java` : Implémentation de la commande `/linkinteractive`.
    *   `src/main/java/com/jjeanniard/plugin/interactiveobjects/listeners/PlayerInteractListener.java` : Écouteur pour intercepter les interactions du joueur avec le monde.
*   **Concepts Clés** :
    *   **Entités/Blocs Personnalisés** : Hytale peut permettre de placer des entités ou blocs personnalisés dans le monde. Il faudra identifier l'API appropriée pour créer des "objets" qui peuvent être interagi. Si ce n'est pas directement possible, on peut simuler en attachant des données à des blocs existants.
    *   **Événements d'Interaction** : Écouter `PlayerInteractEvent` pour détecter les clics sur des blocs ou entités.
    *   **Polymorphisme pour les Actions** : Utiliser des interfaces ou classes abstraites pour définir différents types d'actions que l'objet peut déclencher, permettant d'ajouter facilement de nouvelles actions.
    *   **Persistance de Données au Niveau du Monde** : Sauvegarder les objets interactifs (leur type, position, et action associée) dans un fichier de configuration ou de données spécifique au monde.
*   **Logique** :
    1.  **`CustomInteractiveObject`** : Contient l'UUID, le type, la position (`Vector3i`), et une référence à `InteractiveAction`.
    2.  **`InteractiveAction`** : Interface avec une méthode `execute(Player interactor)`. `SendMessageAction` implémente `execute` pour envoyer un message.
    3.  **`InteractiveObjectManager`** :
        - Maintenir une `Map<UUID, CustomInteractiveObject>` en mémoire.
        - Méthodes `createObject(...)`, `linkAction(UUID objectId, InteractiveAction action)`, `interact(UUID objectId, Player interactor)`.
        - Sauvegarder/charger les objets interactifs dans un fichier JSON.
    4.  **`PlayerInteractListener`** :
        - Enregistrer cet écouteur.
        - Dans `onPlayerInteract(PlayerInteractEvent event)`, vérifier si le bloc ou l'entité cliquée est un de nos objets interactifs.
        - Si oui, appeler `InteractiveObjectManager.interact()` pour déclencher l'action.
    5.  **Commandes** :
        - `CreateInteractiveCommand` : Créer un nouvel objet interactif et le placer dans le monde.
        - `LinkInteractiveCommand` : Modifier l'action d'un objet existant.

*Pour une meilleure organisation du code et la gestion des permissions, consultez le `DEVELOPER_GUIDE.md`.*