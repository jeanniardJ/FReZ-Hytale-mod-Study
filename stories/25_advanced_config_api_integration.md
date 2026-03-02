### Story 25: Intégration de l'API de Configuration Avancée

**En tant que** opérateur de serveur,
**Je veux** avoir un fichier de configuration bien structuré (par exemple, au format YAML ou HOCON) avec des sections, des commentaires explicatifs et la possibilité de recharger la configuration sans redémarrer le serveur,
**Afin de** gérer facilement les paramètres complexes du plugin et de les appliquer à chaud.

---

### Critères d'Acceptation

- **CA-25.1** : Un fichier de configuration nommé `config.yml` (ou un autre format) est généré par le plugin avec des valeurs par défaut et des commentaires explicatifs.
- **CA-25.2** : Le plugin peut lire des valeurs de différents types (string, int, boolean, listes, sections imbriquées) depuis ce fichier.
- **CA-25.3** : Une commande `/myplugin reload` permet à un administrateur de recharger la configuration du plugin à chaud, sans avoir à redémarrer le serveur.
- **CA-25.4** : Les changements appliqués via le rechargement sont immédiatement pris en compte par le plugin.
- **CA-25.5** : Le plugin gère les erreurs de syntaxe dans le fichier de configuration et informe l'administrateur sans crasher.

---

### Implémentation Technique

*   **Fichiers Suggérés** :
    *   `src/main/java/com/jjeanniard/plugin/config/AdvancedConfigManager.java` : Gère le chargement, le rechargement et l'accès aux valeurs de la configuration.
    *   `src/main/java/com/jjeanniard/plugin/config/PluginConfig.java` : Classe (POJO) qui représente la structure complète de la configuration avec des annotations si une bibliothèque de mapping est utilisée.
    *   `src/main/java/com/jjeanniard/plugin/config/commands/ReloadConfigCommand.java` : Implémentation de la commande `/myplugin reload`.
*   **Concepts Clés** :
    *   **Bibliothèque de Configuration** : Hytale pourrait avoir sa propre API de configuration avancée, ou vous pourriez utiliser des bibliothèques Java populaires comme **Jackson (pour YAML/JSON)**, **SnakeYAML**, ou **Typesafe Config (HOCON)**.
    *   **Mapping Objet-Configuration** : Utiliser des annotations ou un API fluent pour mapper les clés/valeurs du fichier de configuration à un objet Java (`PluginConfig`).
    *   **Gestion des Valeurs par Défaut** : S'assurer que le fichier est généré avec des valeurs par défaut la première fois.
    *   **Rechargement à Chaud** : Implémenter une logique pour relire le fichier de configuration et réappliquer les paramètres sans affecter le fonctionnement du plugin (ex: recréer certains managers ou listeners).
    *   **Validation et Gestion d'Erreurs** : Vérifier la validité des valeurs lues et gérer les erreurs de parsing.
*   **Logique** :
    1.  **`PluginConfig`** :
        - Annotations (si Jackson ou similaire est utilisé) pour définir le mapping des champs.
        - Champs pour les différentes sections et valeurs de la configuration.
    2.  **`AdvancedConfigManager`** :
        - Au démarrage du plugin (`onLoad`) : Charger le `config.yml` (créer avec les valeurs par défaut s'il n'existe pas) et le mapper dans une instance de `PluginConfig`.
        - Méthode `reloadConfig()` : Recharger le fichier, mapper, et notifier les parties du plugin qui dépendent de la configuration (ex: passer la nouvelle instance de `PluginConfig` aux managers).
        - Accesseurs (getters) pour les valeurs de configuration.
    3.  **`ReloadConfigCommand`** :
        - Enregistrer la commande `/myplugin reload` dans `Study.java`.
        - L'exécution de la commande appelle `AdvancedConfigManager.reloadConfig()`.
        - Afficher des messages de succès ou d'erreur à l'administrateur.
*   **Références Utiles :**
    *   [Jackson Databind (pour YAML et JSON)](https://github.com/FasterXML/jackson-databind) (en anglais)
    *   [SnakeYAML](https://github.com/snakeyaml/snakeyaml) (en anglais)
    *   [Typesafe Config (HOCON)](https://github.com/lightbend/config) (en anglais)
    *   (À ajouter) Documentation Hytale sur son API de configuration si elle existe.
    *   [Tutoriel Configuration en Java avec Jackson](https://www.baeldung.com/jackson-yaml) (en anglais)
    *   **Ajout à `build.gradle` (exemple pour Jackson YAML) :**
        ```groovy
        dependencies {
            // ... autres dépendances
            implementation 'com.fasterxml.jackson.core:jackson-databind:2.16.1' // Remplacez par la dernière version
            implementation 'com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.16.1' // Remplacez par la dernière version
        }
        ```