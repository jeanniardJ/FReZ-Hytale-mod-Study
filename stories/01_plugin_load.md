### Story 1: Le Plugin se charge

**En tant que** opérateur de serveur,
**Je veux** voir un message "MonPlugin est chargé !" dans la console au démarrage du serveur,
**Afin de** confirmer que mon plugin est bien installé et fonctionne.

---

### Critères d'Acceptation

- **CA-1.1** : Au démarrage complet du serveur, un message contenant le texte "Study est chargé !" est affiché dans les
  logs de la console.
- **CA-1.2** : Le message est enregistré avec le niveau `INFO`.

---

### Implémentation Technique

* **Fichier Principal** : `src/main/java/com/jjeanniard/plugin/Study.java`
* **Concepts Clés** :
    * Hériter de `com.hypixel.hytale.plugin.api.Plugin` pour la classe principale.
    * Surcharger la méthode `onLoad()`. C'est le point d'entrée de votre plugin, appelé lorsque le serveur le charge.
    * Utiliser la classe `Log.java` pour afficher le message, comme recommandé dans le `DEVELOPER_GUIDE.md`.
* **Logique** :
    1. Dans la méthode `onLoad()`, faire un appel à `Log.setLog()`.
    2. Passer `Level.INFO` comme premier paramètre.
    3. Passer le message "Le plugin Study est chargé !" comme second paramètre.

*Pour plus d'informations sur les bonnes pratiques, consultez le `DEVELOPER_GUIDE.md`.*
