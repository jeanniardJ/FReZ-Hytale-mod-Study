### Story 3: Message de bienvenue automatique

**En tant que** joueur,
**Je veux** que le serveur annonce mon arrivée à tout le monde avec un message "Bienvenue à [MonPseudo] sur le
serveur !" quand je me connecte,
**Afin de** me sentir accueilli et de notifier les autres de ma présence.

---

### Critères d'Acceptation

- **CA-3.1** : Lorsqu'un joueur se connecte au serveur, un message est envoyé dans le chat public.
- **CA-3.2** : Le message contient le nom du joueur qui vient de se connecter. Le format est "Bienvenue à [NomDuJoueur]
  sur le serveur !".
- **CA-3.3** : Le message est visible par tous les joueurs actuellement connectés.

---

### Implémentation Technique

* **Fichier Suggéré** : Créer un nouveau package `welcome` et une classe `WelcomeListener.java`.
    * `src/main/java/com/jjeanniard/plugin/welcome/WelcomeListener.java`
* **Concepts Clés** :
    * **Listener** : Une classe qui "écoute" les événements du jeu. Elle doit implémenter
      `com.hypixel.hytale.plugin.api.event.EventListener`.
    * **Enregistrement de l'événement** : Le listener doit être enregistré dans la méthode `onLoad()` de la classe
      principale (`Study.java`) via `getEventManager().registerEvents(this, new WelcomeListener())`.
    * **Annotation** : La méthode qui gère l'événement doit être annotée avec `@EventHandler`.
    * **Événement** : L'événement à écouter est `com.hypixel.hytale.api.event.player.PlayerJoinEvent`.
    * **Message public** : Utiliser `Hytale.getServer().broadcastMessage()` pour envoyer un message à tous les joueurs.
* **Logique** :
    1. Dans la classe principale `Study.java`, enregistrer une nouvelle instance de `WelcomeListener`.
    2. Dans `WelcomeListener.java` :
        - Créer une méthode `onPlayerJoin(PlayerJoinEvent event)`.
        - Annoter cette méthode avec `@EventHandler`.
        - Dans la méthode, récupérer l'objet `Player` depuis `event.getPlayer()`.
        - Construire le message de bienvenue en utilisant le nom du joueur.
        - Diffuser le message à tout le serveur.

*Pour plus d'informations sur la gestion des événements et la structure des packages, consultez
le `DEVELOPER_GUIDE.md`.*
