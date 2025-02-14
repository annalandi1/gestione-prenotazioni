package com.example.gestione_prenotazioni;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {

    @Autowired
    private DipendenteRepository dipendenteRepository;

    @PostMapping
    public Dipendente createDipendente(@RequestBody Dipendente dipendente) {
        return dipendenteRepository.save(dipendente);
    }

    @PutMapping("/{id}")
    public Dipendente updateDipendente(@PathVariable Long id, @RequestBody Dipendente dipendenteDetails) {
        Dipendente dipendente = dipendenteRepository.findById(id).orElseThrow();
        dipendente.setUsername(dipendenteDetails.getUsername());
        dipendente.setNome(dipendenteDetails.getNome());
        dipendente.setCognome(dipendenteDetails.getCognome());
        dipendente.setEmail(dipendenteDetails.getEmail());
        return dipendenteRepository.save(dipendente);
    }

    @PostMapping("/{id}/upload-immagine")
    public Dipendente uploadImmagineProfilo(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        Dipendente dipendente = dipendenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dipendente non trovato"));

        try {
            // Salva il file in una directory specifica
            String nomeFile = file.getOriginalFilename();
            String percorsoFile = "uploads/" + nomeFile; // Cartella "uploads" nel progetto
            file.transferTo(new File(percorsoFile));

            // Aggiorna il percorso dell'immagine nel profilo del dipendente
            dipendente.setImmagineProfilo(percorsoFile);
        } catch (IOException e) {
            throw new RuntimeException("Errore durante l'upload del file");
        }

        return dipendenteRepository.save(dipendente);
    }

    // Altri endpoint per la gestione dei dipendenti (CRUD)
    @GetMapping
    public List<Dipendente> getAllDipendenti() {
        return dipendenteRepository.findAll();
    }

    @GetMapping("/{id}")
    public Dipendente getDipendenteById(@PathVariable Long id) {
        return dipendenteRepository.findById(id).orElseThrow();
    }

    @DeleteMapping("/{id}")
    public void deleteDipendente(@PathVariable Long id) {
        dipendenteRepository.deleteById(id);
    }
}