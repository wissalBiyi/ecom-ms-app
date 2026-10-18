# Rapport de Projet : Système Microservices E-Commerce (ecom-ms-app)

## 1. Introduction

Ce projet s'inscrit dans le cadre du développement d'une architecture logicielle moderne basée sur des **microservices** avec Java, Spring Boot et Spring Cloud. L'objectif principal est de concevoir un système e-commerce distribué, évolutif et tolérant aux pannes, permettant de gérer séparément les clients, les produits et la facturation.

Les principales réalisations de cette première partie comprennent :
- Le développement des services métiers de base (`Customer-Service` et `Inventory-Service`).
- La mise en place de l'infrastructure des microservices avec l'annuaire de services (`Discovery-Service` / Eureka Server) et la passerelle d'API (`Spring Cloud Gateway`).
- L'implémentation de la configuration statique et dynamique du système de routage.
- Les tests et la validation de l'accès aux endpoints REST directement et à travers la Gateway.

---

## 2. Architecture du Projet

L'architecture globale du système repose sur un écosystème de microservices Spring Boot communiquant entre eux et gérés par une infrastructure centralisée :

![Architecture du système microservices](./images/archi.png)

---

## 3. Implémentation et Validation des Services

### A. Discovery Service (Eureka Server - Port 8761)
Le serveur d'enregistrement et de découverte permet à l'ensemble des microservices (`CUSTOMER-SERVICE`, `INVENTORY-SERVICE` et `GATEWAY-SERVICE`) de s'enregistrer dynamiquement sans configuration d'adresses IP statiques.

- **Tableau de bord Eureka** :
  La capture ci-dessous montre l'enregistrement réussi de tous nos services avec le statut **UP**.

  ![Tableau de bord Eureka](./images/img9.png)

---

### B. Customer-Service (Port 8081)
Ce service gère les opérations relatives aux clients de l'application e-commerce.

- **Base de données H2 (Table CUSTOMER)** :
  Vérification de l'insertion des clients en base de données H2.

  ![Base de données Customer](./images/img1.png)

- **Liste des clients au format JSON (GET /customers)** :
  Accès direct à la liste des clients enregistrés.

  ![Liste des clients JSON](./images/img2.png)

- **Recherche d'un client par ID (GET /customers/1)** :
  Récupération des détails d'un client spécifique (ID 1 - Wissal).

  ![Client 1 par ID](./images/img3.png)

---

### C. Inventory-Service (Port 8082)
Ce service gère le catalogue et l'inventaire des produits.

- **Base de données H2 (Table PRODUCT)** :
  Affichage des produits enregistrés (Computer, Printer, Smart Phone).

  ![Base de données Product](./images/img4.png)

- **Liste des produits au format JSON (GET /products)** :
  Accès direct à la liste des produits du catalogue.

  ![Liste des produits JSON](./images/img5.png)

- **Recherche d'un produit par ID (GET /products/1)** :
  Récupération des informations du produit ID 1 (Computer).

  ![Produit 1 par ID](./images/img6.png)

---

### D. Spring Cloud Gateway & Configuration du Routage (Port 8888)

La passerelle d'API sert de point d'entrée unique pour toutes les requêtes clients. Nous avons configuré et testé deux modes de routage :

#### 1. Configuration du Routage Statique
Redirection directe des requêtes vers les URIs physiques des microservices via le port de la Gateway (`8888`).

- **Accès aux clients via la Gateway (`http://localhost:8888/customers`)** :
  ![Accès Customers via Gateway](./images/img7.png)

- **Accès aux produits via la Gateway (`http://localhost:8888/products`)** :
  ![Accès Products via Gateway](./images/img8.png)

- **Accès à un produit spécifique via la Gateway (`http://localhost:8888/products/1`)** :
  ![Accès Product 1 via Gateway](./images/img9.png)

#### 2. Configuration du Routage Dynamique
Routage automatique en utilisant le nom du service enregistré dans le serveur de découverte Eureka.

- **Accès via nom du service Inventory (`http://localhost:8888/INVENTORY-SERVICE/products`)** :
  ![Routage dynamique Inventory Service](./images/img11.png)

- **Accès via nom du service Customer (`http://localhost:8888/CUSTOMER-SERVICE/customers/1`)** :
  ![Routage dynamique Customer Service](./images/img12.png)

---

## 4. Conclusion
Cette première partie a permis de valider la mise en place d'une architecture microservices robuste avec Spring Boot et Spring Cloud. La séparation des responsabilités métiers (`Customer` et `Inventory`), la découverte dynamique avec Eureka et la centralisation du routage par Spring Cloud Gateway constituent une fondation solide pour les développements futurs.