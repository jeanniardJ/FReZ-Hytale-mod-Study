# Règles et Bonnes Pratiques du Projet

Ce document définit les règles de base et les standards de qualité pour le développement de plugins dans ce projet.

## Principes Généraux

1. **Respecter le Guide du Développeur** : Toutes les contributions doivent suivre les instructions détaillées dans le [
   **`DEVELOPER_GUIDE.md`**](./DEVELOPER_GUIDE.md). Ce guide couvre la structure du projet, le style de code, les tests
   et le flux de travail.

2. **Architecture du Jeu** : L'architecture du plugin doit rester cohérente avec les concepts de Hytale, notamment :
    * **ECS (Entity-Component-System)** : Privilégier la manipulation de composants sur les entités.
    * **Structure de Fichiers** : Respecter la structure de dossiers définie par Hytale pour les ressources (`.json`,
      modèles, etc.).

3. **Qualité du Code** :
    * Le code doit être **lisible**, **documenté** (si la logique est complexe) et **maintenable**.
    * Toute nouvelle fonctionnalité doit être accompagnée de **tests unitaires** pour garantir son bon fonctionnement.

4. **Flux de Travail Git** :
    * Chaque nouvelle fonctionnalité doit être développée dans une branche dédiée.
    * Les Pull Requests doivent être liées à une issue GitHub qui décrit la `story` correspondante.

> Pour une explication détaillée de chacune de ces règles, veuillez consulter le [**`DEVELOPER_GUIDE.md`
**](./DEVELOPER_GUIDE.md).