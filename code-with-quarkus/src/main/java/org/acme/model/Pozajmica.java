package org.acme.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Pozajmica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Knjiga knjiga;

    @ManyToOne
    private Clan clan;

    private LocalDate datumPozajmice;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Knjiga getKnjiga() { return knjiga; }
    public void setKnjiga(Knjiga knjiga) { this.knjiga = knjiga; }
    public Clan getClan() { return clan; }
    public void setClan(Clan clan) { this.clan = clan; }
    public LocalDate getDatumPozajmice() { return datumPozajmice; }
    public void setDatumPozajmice(LocalDate datum) { this.datumPozajmice = datum; }
}