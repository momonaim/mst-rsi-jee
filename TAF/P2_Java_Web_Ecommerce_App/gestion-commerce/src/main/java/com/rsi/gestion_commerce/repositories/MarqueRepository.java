package com.rsi.gestion_commerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rsi.gestion_commerce.models.Marque;

public interface MarqueRepository extends JpaRepository<Marque, Long> {
}