package com.rsi.gestion_commerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;
import java.nio.charset.StandardCharsets;

import com.rsi.gestion_commerce.models.Marque;
import com.rsi.gestion_commerce.repositories.MarqueRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/marques")
public class MarqueController {
    @Autowired
    private MarqueRepository marqueRepo;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("marques", marqueRepo.findAll());
        return "marques/list";
    }

    @GetMapping("/new")
    public String form(Model model) {
        Marque nouvelleMarque = new Marque();
        nouvelleMarque.setId(null);
        model.addAttribute("marque", nouvelleMarque);
        return "marques/form";
    }

    @PostMapping
    public String save(@Valid @ModelAttribute Marque marque, BindingResult result) {
        if (result.hasErrors()) {
            return "marques/form";
        }

        boolean isNew = marque.getId() == null;

        // Si ID est défini mais ne correspond à rien, on l'annule
        if (marque.getId() != null && !marqueRepo.existsById(marque.getId())) {
            marque.setId(null);
            isNew = true;
        }

        marqueRepo.save(marque);

        String message = UriUtils.encode(
                isNew ? "Marque créée avec succès" : "Marque mise à jour avec succès",
                StandardCharsets.UTF_8);
        return "redirect:/marques?message=" + message + "&type=success";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("marque", marqueRepo.findById(id).orElse(null));
        return "marques/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        marqueRepo.deleteById(id);
        String message = UriUtils.encode("Marque supprimée avec succès", StandardCharsets.UTF_8);
        return "redirect:/marques?message=" + message + "&type=success";
    }
}
