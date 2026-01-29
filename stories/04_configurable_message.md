### Story 4: Personnaliser le message de bienvenue

**En tant que** opérateur de serveur,
**Je veux** pouvoir changer le message de bienvenue dans un fichier de configuration,
**Afin de** l'adapter au thème de mon serveur sans avoir à modifier le code Java.

---

### Critères d'Acceptation

- **CA-4.1** : Un fichier de configuration (ex: `config.json`) est généré par le plugin s'il n'existe pas.
- **CA-4.2** : Le fichier de configuration contient une option pour définir le format du message de bienvenue (ex:
  `"welcomeMessage": "Bienvenue, {player} !"`).
- **CA-4.3** : Lorsque le plugin se charge, il lit la valeur de cette option.
- **CA-4.4** : Lorsqu'un joueur rejoint, le message affiché utilise le format défini dans le fichier de configuration,
  en remplaçant `{player}` par le nom du joueur.

---

### Implémentation Technique

* **Fichiers Suggérés** :
    * `src/main/java/com/jjeanniard/plugin/config/ConfigManager.java` (pour la logique de configuration).
    * Modifier `welcome/WelcomeListener.java` pour utiliser le `ConfigManager`.
* **Concepts Clés** :
    * **Configuration API** : Hytale fournit une API pour gérer les configurations. Vous pouvez créer une classe (POJO -
      Plain Old Java Object) qui représente la structure de votre JSON.
    * **Chargement** : Au démarrage (`onLoad`), chargez le fichier de configuration. Hytale peut automatiquement mapper
      le JSON à votre objet de configuration.
    * **Injection de Dépendance (Avancé)** : Pour un code plus propre, vous pouvez passer votre `ConfigManager` ou votre
      objet de configuration au constructeur de votre `WelcomeListener`.
    * **Placeholder** : Utiliser `String.replace("{player}", player.getName())` pour remplacer le nom du joueur dans le
      message.
* **Logique** :
    1. Créer une classe `PluginConfig.java` qui contient un champ `String welcomeMessage`.
    2. Créer une classe `ConfigManager.java` :
        - Dans son constructeur ou une méthode `init()`, utiliser l'API de configuration de Hytale pour charger un
          fichier `config.json` dans une instance de `PluginConfig`. Si le fichier n'existe pas, le créer avec des
          valeurs par défaut.
    3. Dans la classe principale `Study.java` :
        - Initialiser le `ConfigManager`.
        - Passer l'instance du `ConfigManager` au constructeur de `WelcomeListener`.
    4. Dans `WelcomeListener.java` :
        - Stocker l'instance du `ConfigManager`.
        - Dans la méthode `onPlayerJoin`, récupérer le format du message depuis le `ConfigManager`.
        - Formater le message avec le nom du joueur et le diffuser.

*Le `DEVELOPER_GUIDE.md` contient des informations sur l'organisation du code qui peuvent vous aider à structurer votre
gestionnaire de configuration.*
