package org.acme.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Knjiga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String naslov;

    @OneToOne
    private Izdavac izdavac;

    @ManyToMany
    private List<Autor> autori;

    @ManyToMany
    private List<Kategorija> kategorije;

    @OneToMany(mappedBy = "knjiga")
    private List<Pozajmica> pozajmice;

    // getteri i setteri
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNaslov() { return naslov; }
    public void setNaslov(String naslov) { this.naslov = naslov; }
    public Izdavac getIzdavac() { return izdavac; }
    public void setIzdavac(Izdavac izdavac) { this.izdavac = izdavac; }
    public List<Autor> getAutori() { return autori; }
    public void setAutori(List<Autor> autori) { this.autori = autori; }
    public List<Kategorija> getKategorije() { return kategorije; }
    public void setKategorije(List<Kategorija> kategorije) { this.kategorije = kategorije; }
    public List<Pozajmica> getPozajmice() { return pozajmice; }
    public void setPozajmice(List<Pozajmica> pozajmice) { this.pozajmice = pozajmice; }
}