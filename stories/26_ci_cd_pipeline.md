### Story 26: Pipeline CI/CD pour la Livraison du Plugin

**En tant que** développeur de plugin,
**Je veux** un système d'Intégration Continue et de Déploiement Continu (CI/CD) automatisé,
**Afin de** m'assurer que chaque modification de code est automatiquement testée, construite et publiée de manière fiable et efficace.

---

### Critères d'Acceptation

- **CA-26.1** : Un pipeline CI est déclenché automatiquement à chaque `push` sur la branche principale (ex: `main` ou `master`) ou à chaque ouverture de Pull Request.
- **CA-26.2** : Le pipeline CI compile le plugin et exécute tous les tests unitaires.
- **CA-26.3** : Le pipeline CI produit un artefact de construction (le fichier `.jar` du plugin).
- **CA-26.4** : Si la compilation ou les tests échouent, le pipeline CI notifie le développeur et empêche la fusion de la Pull Request.
- **CA-26.5** : Un pipeline CD est déclenché après un tag Git (ex: `v1.0.0`) ou une fusion réussie sur la branche principale.
- **CA-26.6** : Le pipeline CD publie l'artefact de construction sur une plateforme de distribution (ex: GitHub Releases).
- **CA-26.7** : Le pipeline CD peut éventuellement générer et publier la documentation du projet.

---

### Implémentation Technique

*   **Fichiers Suggérés** :
    *   `.github/workflows/build_and_publish.yml` (ou un nom similaire) : Fichier de configuration pour GitHub Actions.
    *   Modification de `build.gradle` : Ajout de tâches Gradle pour la publication si nécessaire.
*   **Concepts Clés** :
    *   **GitHub Actions** : La plateforme CI/CD intégrée à GitHub. Utilise des fichiers `.yml` pour définir les workflows.
    *   **Workflows** : Séquence d'étapes (jobs, steps) exécutées dans un environnement virtuel.
    *   **Triggers** : Événements qui déclenchent un workflow (ex: `on: push`, `on: pull_request`, `on: release`).
    *   **Jobs & Steps** : Un job est un ensemble d'étapes qui s'exécutent sur une machine virtuelle. Les étapes exécutent des commandes ou utilisent des actions prédéfinies.
    *   **Actions Pré-construites** : Des actions réutilisables créées par la communauté (ex: `actions/checkout`, `actions/setup-java`, `gradle/gradle-build-action`).
    *   **Artefacts** : Les fichiers générés par le pipeline (ex: le `.jar` du plugin).
    *   **GitHub Releases** : Une fonctionnalité de GitHub pour publier des versions de votre logiciel avec leurs artefacts.
*   **Logique du Pipeline** :

    1.  **Pipeline CI (Build & Test)**
        *   **Trigger** : `on: push` sur `main`, `on: pull_request`.
        *   **Job `build`** :
            *   **Checkout du code** : Utilise `actions/checkout@v4`.
            *   **Configuration de Java** : Utilise `actions/setup-java@v4` pour configurer le JDK requis.
            *   **Build avec Gradle** : Exécute `gradlew build`.
            *   **Exécution des tests** : La commande `gradlew build` inclut normalement l'exécution des tests. Si des rapports Junit sont générés, on peut les uploader comme artefact.
            *   **Upload de l'artefact** : Utilise `actions/upload-artifact@v4` pour sauvegarder le `.jar` du plugin (et éventuellement les rapports de test) afin qu'il puisse être utilisé par d'autres jobs ou téléchargé.

    2.  **Pipeline CD (Release & Publish)**
        *   **Trigger** : `on: push` pour les tags (ex: `v*.*.*`).
        *   **Job `release`** (dépend du job `build`) :
            *   **Checkout du code**.
            *   **Téléchargement de l'artefact** : Utilise `actions/download-artifact@v4` pour récupérer le `.jar` du job `build`.
            *   **Création de GitHub Release** : Utilise `softprops/action-gh-release@v1` pour créer une nouvelle release GitHub et y attacher le `.jar` du plugin.
            *   **Publication de la documentation (optionnel)** : Si vous générez de la Javadoc, cela peut être une étape ici.

*Pour une meilleure organisation du code et la gestion des permissions, consultez le `DEVELOPER_GUIDE.md`.*
*   **Références Utiles :**
    *   [Documentation officielle GitHub Actions](https://docs.github.com/fr/actions)
    *   [GitHub Actions pour les projets Gradle](https://docs.github.com/fr/actions/automating-builds-and-tests/building-and-testing-java-with-gradle)
    *   [Actions pour créer une release GitHub](https://github.com/softprops/action-gh-release)
    *   [Tutoriel CI/CD pour les projets Java](https://www.baeldung.com/java-ci-cd-pipeline) (en anglais)
    *   [Video : Introduction au CI/CD](https://www.youtube.com/watch?v=sc5TXm5aZ6Y) (en français)
    *   [Video : GitHub Actions pour les développeurs Java](https://www.youtube.com/watch?v=F07oUvGjM_M) (en anglais)
