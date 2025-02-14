package com.example.gestione_prenotazioni;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/viaggi")
public class ViaggioController {

    @Autowired
    private ViaggioRepository viaggioRepository;

    @GetMapping
    public List<Viaggio> getAllViaggi() {
        return viaggioRepository.findAll();
    }

    @PostMapping
    public Viaggio createViaggio(@RequestBody Viaggio viaggio) {
        return viaggioRepository.save(viaggio);
    }

    @GetMapping("/{id}")
    public Viaggio getViaggioById(@PathVariable Long id) {
        return viaggioRepository.findById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    public Viaggio updateViaggio(@PathVariable Long id, @RequestBody Viaggio viaggio) {
        Viaggio existingViaggio = viaggioRepository.findById(id).orElseThrow();
        if (viaggio.getDestinazione() != null) {
            existingViaggio.setDestinazione(viaggio.getDestinazione());
        }
        if (viaggio.getDataPartenza() != null) {
            existingViaggio.setDataPartenza(viaggio.getDataPartenza());
        }
        if (viaggio.getDataRitorno() != null) {
            existingViaggio.setDataRitorno(viaggio.getDataRitorno());
        }
        return viaggioRepository.save(existingViaggio);
    }

    @DeleteMapping("/{id}")
    public void deleteViaggio(@PathVariable Long id) {
        viaggioRepository.deleteById(id);
    }

    @PutMapping("/{id}/stato")
    public Viaggio updateStatoViaggio(@PathVariable Long id, @RequestParam String nuovoStato) {
        Viaggio viaggio = viaggioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Viaggio non trovato"));
        viaggio.setStato(nuovoStato);
        return viaggioRepository.save(viaggio);
    }

}
