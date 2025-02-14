package com.example.gestione_prenotazioni;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioniRepository prenotazioneRepository;

    @Autowired
    private ViaggioRepository viaggioRepository;

    @Autowired
    private DipendenteRepository dipendenteRepository;

    // Endpoint per assegnare un viaggio a un dipendente
    @PostMapping
    public Prenotazione createPrenotazione(@RequestBody Prenotazione prenotazione) {
        // Verifica che il dipendente non abbia già una prenotazione per lo stesso
        // giorno
        if (prenotazioneRepository.existsByDipendenteAndDataRichiesta(
                prenotazione.getDipendente(), prenotazione.getDataRichiesta())) {
            throw new RuntimeException("Il dipendente ha già una prenotazione per questa data.");
        }

        // Verifica che il viaggio e il dipendente esistano
        Viaggio viaggio = viaggioRepository.findById(prenotazione.getViaggio().getId())
                .orElseThrow(() -> new RuntimeException("Viaggio non trovato"));
        Dipendente dipendente = dipendenteRepository.findById(prenotazione.getDipendente().getId())
                .orElseThrow(() -> new RuntimeException("Dipendente non trovato"));

        prenotazione.setViaggio(viaggio);
        prenotazione.setDipendente(dipendente);

        return prenotazioneRepository.save(prenotazione);
    }

    // Altri endpoint per la gestione delle prenotazioni (CRUD)
    @GetMapping
    public List<Prenotazione> getAllPrenotazioni() {
        return prenotazioneRepository.findAll();
    }

    @GetMapping("/{id}")
    public Prenotazione getPrenotazioneById(@PathVariable Long id) {
        return prenotazioneRepository.findById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    public Prenotazione updatePrenotazione(@PathVariable Long id, @RequestBody Prenotazione prenotazioneDetails) {
        Prenotazione prenotazione = prenotazioneRepository.findById(id).orElseThrow();
        prenotazione.setViaggio(prenotazioneDetails.getViaggio());
        prenotazione.setDipendente(prenotazioneDetails.getDipendente());
        prenotazione.setDataRichiesta(prenotazioneDetails.getDataRichiesta());
        prenotazione.setNote(prenotazioneDetails.getNote());
        return prenotazioneRepository.save(prenotazione);
    }

    @DeleteMapping("/{id}")
    public void deletePrenotazione(@PathVariable Long id) {
        prenotazioneRepository.deleteById(id);
    }
}
