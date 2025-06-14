package com.rsi.gestion_commerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;
import java.nio.charset.StandardCharsets;

import com.rsi.gestion_commerce.models.Categorie;
import com.rsi.gestion_commerce.repositories.CategorieRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/categories")
public class CategorieController {
    @Autowired
    private CategorieRepository categorieRepo;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("categories", categorieRepo.findAll());
        return "categories/list";
    }

    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("categorie", new Categorie());
        return "categories/form";
    }

    @PostMapping
    public String save(@Valid @ModelAttribute Categorie categorie, BindingResult result) {
        if (result.hasErrors()) {
            return "categories/form";
        }

        boolean isNew = categorie.getId() == null;

        categorieRepo.save(categorie);

        String message = UriUtils.encode(
                isNew ? "Catégorie créée avec succès" : "Catégorie mise à jour avec succès",
                StandardCharsets.UTF_8);
        return "redirect:/categories?message=" + message + "&type=success";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("categorie", categorieRepo.findById(id).orElse(null));
        return "categories/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        categorieRepo.deleteById(id);
        String message = UriUtils.encode("Catégorie supprimée avec succès", StandardCharsets.UTF_8);
        return "redirect:/categories?message=" + message + "&type=success";
    }
}
