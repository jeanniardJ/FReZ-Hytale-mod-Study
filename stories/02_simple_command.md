### Story 2: Commande simple de salutation

**En tant que** joueur,
**Je veux** pouvoir taper la commande `/salut` dans le chat,
**Afin de** recevoir un message privé qui me dit "Bonjour, joueur !".

---

### Critères d'Acceptation

- **CA-2.1** : L'exécution de la commande `/salut` par un joueur déclenche une réponse.
- **CA-2.2** : Le joueur qui a exécuté la commande reçoit le message "Bonjour, [NomDuJoueur] !".
- **CA-2.3** : Si la commande est exécutée depuis la console, un message d'erreur "Cette commande doit être exécutée par
  un joueur." est affiché.

---

### Implémentation Technique

* **Fichier Suggéré** : Créer un nouveau package `greeting` et une classe `SalutationCommand.java` à l'intérieur, comme
  suggéré dans le `DEVELOPER_GUIDE.md`.
    * `src/main/java/com/jjeanniard/plugin/greeting/SalutationCommand.java`
* **Concepts Clés** :
    * **Enregistrement** : Les commandes doivent être enregistrées dans la méthode `onLoad()` de la classe principale (
      `Study.java`) via le `getCommandManager()`.
    * **Interface** : La classe de commande doit implémenter `com.hypixel.hytale.plugin.api.command.CommandExecutor`.
    * **Exécution** : La logique se place dans la méthode `execute(CommandContext context)`.
    * **Validation** : Vérifier si l'émetteur de la commande (`context.getSender()`) est un joueur (`Player`).
    * **Message Privé** : Utiliser `sender.sendMessage()` pour envoyer la réponse.
* **Logique** :
    1. Dans la classe principale `Study.java`, enregistrer la commande `/salut` en l'associant à une nouvelle instance
       de `SalutationCommand`.
    2. Dans `SalutationCommand.java` :
        - Implémenter la méthode `execute`.
        - Vérifier si `context.getSender()` est une instance de `Player`.
        - Si non, afficher un message d'erreur dans la console via `Log.setLog()`.
        - Si oui, caster le `sender` en `Player`, récupérer son nom, et lui envoyer le message de salutation.

*Pour plus d'informations sur la structure des packages, consultez le `DEVELOPER_GUIDE.md`.*
