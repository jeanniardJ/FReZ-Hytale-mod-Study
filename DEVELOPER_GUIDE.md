# Guide du Développeur pour les Plugins Hytale

Bienvenue dans le projet d'étude de plugins Hytale ! Ce guide a pour but de vous aider à structurer votre code, à suivre
les bonnes pratiques et à créer des plugins robustes et maintenables.

## 1. Objectif du Projet

L'objectif principal est d'apprendre le développement de plugins Java pour Hytale en créant des fonctionnalités bien
définies, testables et suivant les conventions de la communauté.

## 2. Structure du Projet

Une bonne organisation des packages est essentielle pour la lisibilité. La structure actuelle est un bon début, mais
nous pouvons l'améliorer pour mieux organiser les fonctionnalités.

### Structure de Packages Recommandée

Je vous propose d'organiser votre code par **fonctionnalité** plutôt que par type (commandes, événements, etc.).

**Structure Actuelle :**

```
com.jjeanniard.plugin
├── commande
│   └── Command.java
├── config
│   └── MyConfig.java
└── event
```

**Structure Recommandée par Fonctionnalité :**

```
com.jjeanniard.plugin
├── core                  // Classes de base (main plugin, logging)
│   ├── Study.java
│   └── Log.java
├── welcome_message       // Fonctionnalité : Message de bienvenue
│   ├── WelcomeMessageListener.java
│   └── WelcomeMessageConfig.java
├── custom_recipe         // Fonctionnalité : Recette personnalisée
│   ├── RecipeManager.java
│   └── GiveImprovedSwordCommand.java
└── player_data           // Fonctionnalité : Données des joueurs
    ├── PlayerData.java
    └── PlayerDataStorage.java
```

**Avantages :**

- **Modularité :** Chaque fonctionnalité est autonome.
- **Lisibilité :** Il est facile de trouver tout le code lié à une fonctionnalité.
- **Maintenance :** Les modifications sont localisées et ont moins de risques de casser autre chose.

> **Action :** Pour les nouvelles fonctionnalités, essayez de créer un nouveau package qui regroupe toutes les classes
associées.

## 3. Flux de Développement : Des Stories au Code

Votre usage des fichiers `stories/*.md` est une excellente pratique inspirée du *Behavior-Driven Development* (BDD).
Voici comment l'intégrer dans votre flux de travail :

1. **Créer une Story :** Avant d'écrire du code, décrivez la fonctionnalité dans un nouveau fichier `.md` dans le
   dossier `stories`. (Ex: `15_nouveau_systeme.md`).
2. **Créer une Issue GitHub :** Créez une issue sur GitHub qui correspond à cette story.
3. **Créer une Branche :** Créez une branche Git pour cette issue (ex: `feature/15-nouveau-systeme`).
4. **Développer :** Écrivez le code pour la fonctionnalité dans son propre package (voir section 2).
5. **Écrire des Tests (Important !) :** Ajoutez des tests unitaires pour valider votre logique (voir section 6).
6. **Créer une Pull Request :** Une fois la fonctionnalité terminée et testée, ouvrez une Pull Request en la liant à
   l'issue.

## 4. Synchronisation des Stories avec GitHub Issues

Pour assurer une bonne traçabilité et une organisation claire de votre travail, nous avons lié chaque story à une issue
GitHub.

### Workflow Recommandé

1. **Création d'une Story Localement** :
    * Créez votre fichier de story `stories/XX_titre_story.md`.
    * Remplissez-le avec le titre, la description, les critères d'acceptation et les pistes d'implémentation technique,
      comme illustré dans les exemples fournis.

2. **Création de l'Issue sur GitHub** :
    * Utilisez l'interface GitHub ou un outil comme l'agent pour créer une nouvelle issue.
    * Le **titre de l'issue** doit être le titre de votre story (ex: `Story 15: Nouveau Système`).
    * Le **corps de l'issue** doit être le contenu intégral de votre fichier `stories/XX_titre_story.md`.

3. **Liaison et Suivi dans IntelliJ IDEA** :
    * **Intégration GitHub** : IntelliJ IDEA possède une excellente intégration avec GitHub. Assurez-vous d'avoir
      configuré votre compte GitHub dans l'IDE.
    * **Fenêtre d'Outils "GitHub" ou "Version Control"** : Vous pouvez accéder et interagir avec vos issues directement
      depuis l'IDE.
    * **Création de Branche depuis une Issue** : Une fonctionnalité très utile est la possibilité de créer une nouvelle
      branche Git directement à partir d'une issue GitHub. IntelliJ nommera la branche de manière conventionnelle (ex:
      `feature/ISSUE_NUMBER-issue-title`).
    * **Commits liés aux Issues** : Lors de vos commits, vous pouvez référencer l'issue correspondante dans le message
      de commit (ex: `git commit -m "feat: Implémentation du nouveau système (closes #15)"`).

### Astuce pour la Mise à Jour

* **Mettre à jour une Story** : Si vous modifiez le contenu d'un fichier `stories/*.md`, pensez à mettre à jour
  manuellement le corps de l'issue GitHub correspondante pour que la documentation reste synchronisée.

### Automatisation Future

Bien qu'une automatisation complète et en temps réel de la synchronisation (via des webhooks ou GitHub Actions) soit
possible, elle est au-delà de la portée de ce guide d'apprentissage initial. Le workflow manuel décrit ci-dessus vous
assure une compréhension des étapes fondamentales.

### Prévention des Doublons et Fusion

Pour éviter la duplication des issues, suivez cette procédure :

1. **Vérifier les Issues Existantes** : Avant de créer une nouvelle issue sur GitHub pour une story, utilisez la
   fonction de recherche de GitHub (ou l'intégration IntelliJ IDEA) pour vérifier si une issue avec un titre similaire
   existe déjà. Vous pouvez rechercher `Story XX: [Titre de la Story]`.
2. **Gestion des Duplicatas** :
    * **Si un duplicata est trouvé (Issue Primaire)** :
        * Copiez le contenu enrichi de votre fichier `stories/*.md` et mettez à jour le corps de l'Issue Primaire si
          nécessaire.
        * Si vous avez accidentellement créé une nouvelle issue, commentez-la pour indiquer qu'elle est un doublon de l'
          Issue Primaire (par ex. "Cette issue est un doublon de #YY. Veuillez vous référer à l'issue #YY pour le
          suivi.")
        * Fermez la nouvelle issue en sélectionnant "Mark as duplicate" et en la liant à l'Issue Primaire.
    * **Si aucun duplicata n'est trouvé** :
        * Procédez à la création de votre nouvelle issue.

3. **Convention de Nommage** : Adoptez une convention de nommage stricte pour les titres d'issues, comme
   `Story XX: [Titre de la Story]`, pour faciliter la détection des doublons.

## 5. Style de Code

Pour assurer la cohérence, je vous recommande d'adopter un guide de style standard. Le *
*[Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)** est une excellente référence :

- Conventions de nommage.
- Règles de formatage.
- Bonnes pratiques d'écriture.

> **Action :** Lisez les sections principales de ce guide pour vous familiariser avec les conventions. De nombreux IDEs
peuvent être configurés pour formater automatiquement votre code selon ce style.

## 6. Logging

Vous avez déjà une classe `Log.java` qui utilise le logger natif de Hytale (`HytaleLogger`). C'est parfait !

**Bonnes pratiques :**

- **Continuez à l'utiliser :** N'utilisez **jamais** `System.out.println()` pour les logs. Votre classe `Log` est la
  seule bonne manière de faire.
- **Utilisez les bons niveaux :**
    - `INFO` : Pour les informations générales sur le fonctionnement du plugin.
    - `WARNING` : Pour les situations inattendues mais qui ne bloquent pas le fonctionnement.
    - `SEVERE` : Pour les erreurs critiques qui empêchent une fonctionnalité de marcher.

## 7. Tests Unitaires

Les tests unitaires sont cruciaux pour vérifier que votre logique fonctionne comme prévu, sans avoir à lancer le jeu. Le
projet n'inclut pas encore de framework de test.

### Ajouter JUnit 5

Je ne peux pas modifier votre `build.gradle`, mais voici comment vous pouvez ajouter le support pour les tests :

1. Ouvrez votre fichier `build.gradle`.
2. Localisez la section `dependencies { ... }`.
3. Ajoutez les lignes suivantes à l'intérieur :

   ```groovy
   // Dépendances pour les tests
   testImplementation(platform('org.junit:junit-bom:5.10.0'))
   testImplementation('org.junit.jupiter:junit-jupiter')
   ```

4. Ajoutez cette section à la fin de votre fichier pour activer la tâche de test :

   ```groovy
   tasks.withType(Test) {
       useJUnitPlatform()
   }
   ```

### Écrire votre premier test

- Créez le dossier `src/test/java`.
- À l'intérieur, recréz la même structure de package que dans `src/main/java`.
- Pour une classe `MyClass.java`, créez une classe de test `MyClassTest.java`.

**Exemple de test simple :**

```java
// Dans src/test/java/com/jjeanniard/plugin/MyFirstTest.java
package com.jjeanniard.plugin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyFirstTest {

    @Test
    void addition_worksCorrectly() {
        // Arrange (Préparer)
        int a = 2;
        int b = 3;

        // Act (Agir)
        int result = a + b;

        // Assert (Vérifier)
        assertEquals(5, result, "2 + 3 devrait être égal à 5");
    }
}
```

> **Action :** Suivez ces étapes pour ajouter JUnit à votre projet et essayez d'écrire un premier test simple.

## 8. Dépendances Clés

- **`HytaleServer.jar` :** C'est la dépendance la plus importante. Elle contient tout le code du serveur Hytale, ce qui
  vous permet d'accéder aux classes du jeu (joueurs, items, événements, etc.). Elle est déjà correctement configurée
  dans votre `build.gradle`.
- **`org.junit.jupiter:junit-jupiter` :** (À ajouter) La bibliothèque principale pour écrire des tests en Java.

## 9. Nettoyage

Le package `org.example.plugin` contient des fichiers d'exemple qui ne sont pas liés à votre projet.

> **Action :** Une fois que vous êtes à l'aise avec la structure, vous pouvez supprimer le dossier
`src/main/java/org/example`.