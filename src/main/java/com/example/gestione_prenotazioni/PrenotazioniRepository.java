package com.example.gestione_prenotazioni;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface PrenotazioniRepository extends JpaRepository<Prenotazione, Long> {
    boolean existsByDipendenteAndDataRichiesta(Dipendente dipendente, LocalDate dataRichiesta);

    Prenotazione findByDipendenteAndDataRichiesta(Dipendente dipendente, LocalDate dataRichiesta);
}
