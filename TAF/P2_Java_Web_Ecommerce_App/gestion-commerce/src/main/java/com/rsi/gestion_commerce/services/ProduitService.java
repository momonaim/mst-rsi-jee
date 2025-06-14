package com.rsi.gestion_commerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rsi.gestion_commerce.models.Produit;
import com.rsi.gestion_commerce.repositories.ProduitRepository;

@Service
public class ProduitService {
    @Autowired
    private ProduitRepository produitRepo;

    public Page<Produit> findAll(Pageable pageable) {
        return produitRepo.findAll(pageable);
    }

    public boolean existsById(Long id) {
        return produitRepo.existsById(id);
    }

    public Produit findById(Long id) {
        return produitRepo.findById(id).orElse(null);
    }

    public Produit save(Produit p) {
        return produitRepo.save(p);
    }

    public void delete(Long id) {
        produitRepo.deleteById(id);
    }
}