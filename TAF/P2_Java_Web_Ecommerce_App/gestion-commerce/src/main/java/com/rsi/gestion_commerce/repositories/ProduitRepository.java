package com.rsi.gestion_commerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rsi.gestion_commerce.models.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
}