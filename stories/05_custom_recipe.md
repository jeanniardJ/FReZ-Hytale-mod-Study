### Story 5: Ajouter une recette d'artisanat

**En tant que** joueur,
**Je veux** pouvoir fabriquer une "Épée en Diamant Améliorée" en utilisant un établi spécifique avec 2 blocs de diamant
et un bâton comme ingrédients,
**Afin d'** obtenir un objet unique qui n'existe pas dans le jeu de base.

---

### Critères d'Acceptation

- **CA-5.1** : Une recette pour "ImprovedDiamondSword" existe.
- **CA-5.2** : La recette requiert exactement 2 "DiamondBlock" et 1 "Stick".
- **CA-5.3** : La fabrication de l'objet requiert un établi de type "Fieldcraft".
- **CA-5.4** : Le résultat de la recette est 1 "ImprovedDiamondSword".
- **CA-5.5** : Un item personnalisé "ImprovedDiamondSword" est défini avec des statistiques propres (durabilité,
  dégâts).

---

### Implémentation Technique

* **Fichiers Clés** :
    * **Recette** : `src/main/resources/Server/Item/Recipes/Example_Recipe.json`
    * **Item Personnalisé** : `src/main/resources/Server/Item/ImprovedDiamondSword.json`
* **Concepts Clés** :
    * **Asset Pack** : Les recettes et les items personnalisés font partie du "pack d'assets" du plugin. Assurez-vous
      que `includes_pack=true` est défini dans `gradle.properties`.
    * **Structure JSON** : Hytale utilise des fichiers `.json` pour définir les objets du jeu. La structure doit être
      exacte.
    * **Définition d'Item** : Un item qui n'existe pas dans le jeu de base doit avoir son propre fichier de définition.
* **Logique de la Recette (`Example_Recipe.json`)** :
    1. **`Input`** : Un tableau listant les ingrédients.
        - Chaque ingrédient a un `ItemId` (ex: `"DiamondBlock"`) et une `Quantity`.
    2. **`PrimaryOutput`** : L'objet résultant de la recette.
        - Il a un `ItemId` (ici, notre item personnalisé `"ImprovedDiamondSword"`) et une `Quantity`.
    3. **`BenchRequirement`** : Spécifie l'établi nécessaire (ex: `"Fieldcraft"`).
* **Logique de l'Item (`ImprovedDiamondSword.json`)** :
    1. **`FormatVersion`** : Spécifie la version du format de l'asset.
    2. **`Item.Name`** : Le nom unique de l'item.
    3. **`Item.Components`** : C'est ici que le comportement de l'item est défini.
        - `Hytale.Item`: Définit le type de base de l'outil (ex: `"Sword"`).
        - `Hytale.Durability`: Définit la durabilité maximale.
        - `Hytale.Damage`: Définit les dégâts infligés.

> **Note** : Les fichiers pour cette story ont déjà été mis en place lors de notre session précédente. Vous pouvez les
> examiner pour voir un exemple concret d'une fonctionnalité complète.