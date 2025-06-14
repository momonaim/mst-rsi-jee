# Application de Gestion E-Commerce

Cette application est un système de gestion e-commerce développé avec Spring Boot qui permet de gérer les produits, les catégories et les marques.

## 📋 Fonctionnalités

- Gestion des produits (ajout, modification, suppression, consultation)
- Gestion des catégories de produits
- Gestion des marques
- Interface utilisateur intuitive avec JSP
- Base de données H2 embarquée

## 🔧 Prérequis

- Java JDK 17 ou supérieur
- Maven 3.8 ou supérieur
- Un IDE (Eclipse, IntelliJ IDEA, VS Code)

## 🚀 Installation et démarrage

1. Clonez le repository :

```bash
git clone [URL_DU_REPO]
cd gestion-commerce
```

2. Compilation du projet :

```bash
mvn clean install
```

3. Démarrage de l'application :

```bash
mvn spring-boot:run
```

L'application sera accessible à l'adresse : http://localhost:8080

## 📱 Captures d'écran

### Page d'accueil

[Insérer capture d'écran de la page d'accueil]

### Liste des produits

[Insérer capture d'écran de la liste des produits]

### Formulaire d'ajout/modification

[Insérer capture d'écran du formulaire]

## 🏗️ Structure du projet

```
src/
├── main/
│   ├── java/
│   │   └── com/rsi/gestion_commerce/
│   │       ├── controllers/     # Contrôleurs MVC
│   │       ├── models/          # Entités JPA
│   │       ├── repositories/    # Repositories Spring Data
│   │       └── services/        # Couche service
│   ├── resources/
│   │   └── application.properties
│   └── webapp/
│       └── WEB-INF/
│           ├── views/           # Vues JSP
│           └── static/          # Ressources statiques
```

## 🛠️ Technologies utilisées

- Spring Boot
- Spring MVC
- Spring Data JPA
- H2 Database
- JSP (JavaServer Pages)
- Maven

## 📝 Fonctionnalités détaillées

### Gestion des produits

- Liste de tous les produits
- Ajout d'un nouveau produit
- Modification d'un produit existant
- Suppression d'un produit
- Association avec une catégorie et une marque

### Gestion des catégories

- Liste des catégories
- Ajout/modification/suppression de catégories
- Visualisation des produits par catégorie

### Gestion des marques

- Liste des marques
- Ajout/modification/suppression de marques
- Visualisation des produits par marque

## 💾 Base de données

L'application utilise une base de données H2 embarquée. La configuration se trouve dans le fichier `application.properties`.

### Modèle de données

[Insérer ici un schéma du modèle de données]

## 🔒 Configuration

Le fichier `application.properties` contient les configurations principales :

- Configuration de la base de données
- Configuration du serveur
- Autres paramètres de l'application

## 🤝 Contribution

Les contributions sont les bienvenues ! N'hésitez pas à :

1. Fork le projet
2. Créer une branche pour votre fonctionnalité
3. Commit vos changements
4. Push sur votre branche
5. Ouvrir une Pull Request

## 📄 Licence

Ce projet est un travail académique réalisé dans le cadre de la formation MST-RSI (Master Sciences et Techniques - Réseaux et Systèmes Informatiques).

## ✍️ Auteur

- © 2025 MST-RSI | MOUADILI Abdelmounim

## 📞 Support

Pour toute question ou problème, veuillez :

1. Consulter la documentation
2. Ouvrir une issue sur le repository
