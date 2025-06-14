package com.rsi.gestion_commerce.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Produit {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "Le nom est obligatoire")
    @Size(min = 2, max = 100, message = "Le nom doit contenir entre 2 et 100 caractères")
    private String nom;

    @Min(value = 0, message = "Le prix doit être supérieur ou égal à 0")
    private double prix;

    @NotNull(message = "La marque est obligatoire")
    @ManyToOne
    private Marque marque;

    @NotNull(message = "La catégorie est obligatoire")
    @ManyToOne
    private Categorie categorie;

    // Getters et Setters
}
