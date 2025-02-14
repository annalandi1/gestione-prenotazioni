package com.example.gestione_prenotazioni;

import lombok.Data;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Data
@Entity

public class Viaggio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String destinazione;
    private LocalDate dataPartenza;
    private LocalDate dataRitorno;
    private String stato;

    public LocalDate getDataPartenza() {
        return dataPartenza != null ? dataPartenza : LocalDate.now();
    }

    public LocalDate getDataRitorno() {
        return dataRitorno != null ? dataRitorno : LocalDate.now();
    }
}
