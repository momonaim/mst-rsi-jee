package com.rsi.gestion_commerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.util.UriUtils;
import java.nio.charset.StandardCharsets;

import com.rsi.gestion_commerce.models.Produit;
import com.rsi.gestion_commerce.repositories.CategorieRepository;
import com.rsi.gestion_commerce.repositories.MarqueRepository;
import com.rsi.gestion_commerce.services.ProduitService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/produits")
public class ProduitController {
    @Autowired
    ProduitService produitService;
    @Autowired
    MarqueRepository marqueRepo;
    @Autowired
    CategorieRepository categorieRepo;

    @GetMapping
    public String list(Model model, @PageableDefault(size = 5, sort = "id") Pageable pageable) {
        Page<Produit> produitsPage = produitService.findAll(pageable);
        model.addAttribute("produitsPage", produitsPage);
        return "produits/list";
    }

    @GetMapping("/new")
    public String form(Model model) {
        Produit nouveauProduit = new Produit();
        nouveauProduit.setId(null);
        model.addAttribute("produit", nouveauProduit);
        model.addAttribute("marques", marqueRepo.findAll());
        model.addAttribute("categories", categorieRepo.findAll());
        return "produits/form";
    }

    @PostMapping
    public String save(@Valid @ModelAttribute Produit produit, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("marques", marqueRepo.findAll());
            model.addAttribute("categories", categorieRepo.findAll());
            return "produits/form";
        }

        boolean isNew = produit.getId() == null;

        // Si ID est défini mais ne correspond à rien, on l'annule
        if (produit.getId() != null && !produitService.existsById(produit.getId())) {
            produit.setId(null);
            isNew = true;
        }

        produitService.save(produit);

        String message = UriUtils.encode(
                isNew ? "Produit créé avec succès" : "Produit mis à jour avec succès",
                StandardCharsets.UTF_8);
        return "redirect:/produits?message=" + message + "&type=success";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("produit", produitService.findById(id));
        model.addAttribute("marques", marqueRepo.findAll());
        model.addAttribute("categories", categorieRepo.findAll());
        return "produits/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        produitService.delete(id);
        String message = UriUtils.encode("Produit supprimé avec succès", StandardCharsets.UTF_8);
        return "redirect:/produits?message=" + message + "&type=success";
    }
}
