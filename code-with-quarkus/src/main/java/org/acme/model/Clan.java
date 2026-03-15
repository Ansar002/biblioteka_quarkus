package org.acme.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Clan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ime;
    private String prezime;

    @OneToMany(mappedBy = "clan")
    private List<Pozajmica> pozajmice;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getIme() { return ime; }
    public void setIme(String ime) { this.ime = ime; }
    public String getPrezime() { return prezime; }
    public void setPrezime(String prezime) { this.prezime = prezime; }
    public List<Pozajmica> getPozajmice() { return pozajmice; }
    public void setPozajmice(List<Pozajmica> pozajmice) { this.pozajmice = pozajmice; }
}