package org.acme.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Kategorija {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String naziv;

    @ManyToMany(mappedBy = "kategorije")
    private List<Knjiga> knjige;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNaziv() { return naziv; }
    public void setNaziv(String naziv) { this.naziv = naziv; }
    public List<Knjiga> getKnjige() { return knjige; }
    public void setKnjige(List<Knjiga> knjige) { this.knjige = knjige; }
}