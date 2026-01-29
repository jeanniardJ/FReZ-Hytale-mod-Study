### Story 15: Système d'économie simple

**En tant que** joueur,
**Je veux** avoir un solde d'argent virtuel que je peux gagner et dépenser,
**Afin de** participer à un système économique basique dans le jeu.

---

### Critères d'Acceptation

- **CA-15.1** : Chaque nouveau joueur commence avec un solde d'argent par défaut (ex: 100 unités).
- **CA-15.2** : Une commande `/balance` permet à un joueur de voir son solde actuel.
- **CA-15.3** : Une commande `/pay <joueur> <montant>` permet à un joueur de transférer de l'argent à un autre joueur.
- **CA-15.4** : Le solde d'argent des joueurs est persistant (sauvegardé et chargé à la déconnexion/connexion).
- **CA-15.5** : Si un joueur tente de payer plus d'argent qu'il ne possède, la transaction est refusée et un message
  d'erreur est affiché.
- **CA-15.6** : Les commandes de gestion de l'économie (ex: `/balance`, `/pay`) sont utilisables uniquement par des
  joueurs.

---

### Implémentation Technique

* **Fichiers Suggérés** :
    * `src/main/java/com/jjeanniard/plugin/economy/EconomyManager.java` : Gère la logique de l'économie (ajouter/retirer
      de l'argent, vérifier le solde).
    * `src/main/java/com/jjeanniard/plugin/economy/PlayerEconomyData.java` : Classe pour stocker le solde d'un joueur.
    * `src/main/java/com/jjeanniard/plugin/economy/commands/BalanceCommand.java` : Implémentation de la commande
      `/balance`.
    * `src/main/java/com/jjeanniard/plugin/economy/commands/PayCommand.java` : Implémentation de la commande `/pay`.
    * Modifier `player_data/PlayerDataStorage.java` ou créer un nouveau listener pour sauvegarder/charger les données
      économiques à la connexion/déconnexion.
* **Concepts Clés** :
    * **Gestion des Données Persistantes** : Utiliser l'API de stockage de Hytale pour sauvegarder et charger les soldes
      des joueurs. Cela peut s'appuyer sur des concepts déjà explorés dans la Story 7 (`player_data`).
    * **Commandes avec Arguments** : Les commandes `/pay` nécessitent de parser plusieurs arguments (`<joueur>`,
      `<montant>`).
    * **Validation des Entrées** : Vérifier que le `<montant>` est un nombre valide et positif. Vérifier que le joueur
      cible existe.
    * **Transactions** : S'assurer que les transferts d'argent sont atomiques (tout ou rien) pour éviter la duplication
      ou la perte d'argent.
    * **API des Joueurs** : Récupérer les objets `Player` par leur nom ou leur UUID.
* **Logique** :
    1. **`EconomyManager`** :
        - Maintenir une `Map<UUID, PlayerEconomyData>` en mémoire pour les soldes des joueurs connectés.
        - Méthodes `addMoney(Player, amount)`, `removeMoney(Player, amount)`, `getBalance(Player)`,
          `canAfford(Player, amount)`.
    2. **`PlayerEconomyData`** :
        - Contient le solde d'un joueur et potentiellement d'autres données économiques.
    3. **Commandes** :
        - Enregistrer `BalanceCommand` et `PayCommand` dans `Study.java`.
        - Implémenter la logique de parsing et de validation des arguments dans les classes de commande.
        - Utiliser `EconomyManager` pour effectuer les opérations économiques.
    4. **Persistance** :
        - Dans un `EventListener` (ex: `PlayerJoinEvent`, `PlayerQuitEvent`), charger le solde du joueur depuis le
          stockage persistant dans `EconomyManager` à la connexion, et sauvegarder le solde à la déconnexion. Utiliser
          `Hytale.getServer().getPluginDataFolder()` pour trouver un emplacement de stockage pour les fichiers JSON ou
          autre format.

*Pour une meilleure organisation du code, suivez la structure de packages recommandée dans le `DEVELOPER_GUIDE.md`.*