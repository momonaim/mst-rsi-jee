package com.rsi.gestion_commerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rsi.gestion_commerce.models.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
}