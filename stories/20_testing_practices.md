### Story 20: Maîtriser les Tests Unitaires pour les Plugins Hytale

**En tant que** développeur de plugin,
**Je veux** comprendre comment, quand et quoi tester dans mon code Java,
**Afin de** m'assurer que mes fonctionnalités sont robustes, fonctionnent comme prévu et ne régressent pas lors de futures modifications.

---

### Critères d'Acceptation

- **CA-20.1** : Le développeur comprend la différence entre les tests unitaires, les tests d'intégration et les tests fonctionnels (bien que l'accent soit mis sur les unitaires ici).
- **CA-20.2** : Le développeur est capable d'identifier les parties de son code qui nécessitent des tests unitaires (logique métier, calculs, validation).
- **CA-20.3** : Le développeur est capable d'écrire un test unitaire simple pour une méthode sans dépendance externe (pure fonction).
- **CA-20.4** : Le développeur comprend comment "simuler" les dépendances externes (comme l'API Hytale ou les classes de manager) pour tester sa logique de manière isolée.
- **CA-20.5** : Le développeur sait à quel moment du cycle de développement il doit écrire ses tests.

---

### Implémentation Technique

*   **Fichier Suggéré** : Il ne s'agit pas d'une fonctionnalité de jeu, mais d'une **pratique**. Les fichiers générés seront des classes de test.
    *   `src/test/java/com/jjeanniard/plugin/test_examples/PureFunctionTest.java` (pour illustrer le test de fonctions pures).
    *   `src/test/java/com/jjeanniard/plugin/test_examples/ServiceWithDependenciesTest.java` (pour illustrer le test de services avec mocking).
*   **Concepts Clés** :
    *   **Principes F.I.R.S.T. (Fast, Independent, Repeatable, Self-validating, Timely)** des tests unitaires.
    *   **Cycle AAA (Arrange, Act, Assert)** pour la structure des tests.
    *   **JUnit 5** : Le framework de test déjà recommandé dans `DEVELOPER_GUIDE.md`.
    *   **Mockito** (ou un framework de mocking similaire) : Pour simuler les dépendances externes.
    *   **Code "Testable"** : Concevoir son code pour qu'il soit facilement testable (faible couplage, forte cohésion, injection de dépendances).
    *   **TDD (Test-Driven Development)** : Une approche de développement où les tests sont écrits avant le code de production.
*   **Logique** :

    1.  **Quand Tester ?**
        *   **Dès que la logique est écrite** : Idéalement, avant même d'écrire le code de la fonctionnalité (approche TDD). Au minimum, immédiatement après avoir implémenté une petite unité de code.
        *   **Avant de pousser le code** : Exécutez toujours tous les tests unitaires pour vous assurer que vos changements n'ont rien cassé.
        *   **Lors d'une modification ou d'une correction de bug** : Chaque modification ou correction devrait idéalement être couverte par de nouveaux tests (ou la modification d'existants) pour éviter les régressions.

    2.  **Quoi Tester ?**
        *   **Logique Métier Complexe** : Concentrez-vous sur les parties de votre code qui contiennent des règles métier importantes, des algorithmes, des calculs complexes, des boucles ou des conditions (if/else, switch).
        *   **Validation des Entrées** : Assurez-vous que votre code gère correctement les entrées attendues, mais aussi les entrées invalides (nulle, vide, hors limites).
        *   **Cas Limites (Edge Cases)** : Testez les scénarios extrêmes : valeurs minimales/maximales, collections vides ou très grandes, etc.
        *   **Comportement des Managers/Services** : Vérifiez que vos classes de service ou de manager (qui coordonnent d'autres objets) se comportent comme prévu en réponse à différentes entrées et états de leurs dépendances.
        *   **Gestion des Erreurs** : Assurez-vous que les exceptions sont levées (ou gérées) correctement lorsque des conditions d'erreur surviennent.

    3.  **Comment Tester (Principes Généraux avec JUnit 5 et Mockito) ?**

        *   **Pré-requis conceptuels** :
            *   **JUnit 5** : Sera le cadre principal pour définir vos tests (`@Test`).
            *   **Mockito** : Sera utilisé pour créer des objets "mock" (simulacres) des dépendances de votre classe à tester.

        *   **Tester une fonction pure (sans dépendance externe)** :
            *   Identifiez les méthodes qui prennent des entrées et produisent une sortie sans interagir avec d'autres objets ou l'état du système.
            *   Dans votre test, créez une instance de la classe à tester, appelez la méthode avec des entrées spécifiques et utilisez les assertions de JUnit (ex: `assertEquals`, `assertTrue`) pour vérifier que la sortie est conforme aux attentes.

        *   **Tester un service avec des dépendances (avec Mockito)** :
            *   Pour tester une classe qui dépend d'autres objets (ex: un service qui a besoin d'un gestionnaire de base de données), utilisez Mockito pour créer des "mocks" de ces dépendances.
            *   Un "mock" est un objet factice qui imite le comportement d'un objet réel. Vous "programmez" le mock pour qu'il retourne des valeurs spécifiques lorsque ses méthodes sont appelées (ex: `when(mockObject.someMethod()).thenReturn(value)`).
            *   Puis, dans votre test, vous appelez la méthode de votre service.
            *   Enfin, vous utilisez `verify(mockObject, times(X)).someMethod(args)` pour vérifier que le service a interagi avec ses dépendances de la manière attendue. Cela permet de tester la logique du service sans dépendre de l'implémentation réelle (et potentiellement complexe) de ses dépendances.

*Ce document complète la section "Tests Unitaires" du `DEVELOPER_GUIDE.md` et fournit des explications conceptuelles pour vous guider.*
*   **Références Utiles :**
    *   [Documentation officielle JUnit 5](https://junit.org/junit5/docs/current/user-guide/) (en anglais)
    *   [Documentation officielle Mockito](https://site.mockito.org/javadoc/current/org/mockito/Mockito.html) (en anglais)
    *   [Tutoriel Baeldung sur JUnit 5](https://www.baeldung.com/junit-5) (en anglais)
    *   [Tutoriel Baeldung sur Mockito](https://www.baeldung.com/mockito-series) (en anglais)
    *   [Principes F.I.R.S.T. des tests](https://medium.com/@khammy/f-i-r-s-t-principles-of-unit-testing-a5fc0d9a65d6) (en anglais)