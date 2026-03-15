package org.acme.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ime;

    @ManyToMany(mappedBy = "autori")
    private List<Knjiga> knjige;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getIme() { return ime; }
    public void setIme(String ime) { this.ime = ime; }
    public List<Knjiga> getKnjige() { return knjige; }
    public void setKnjige(List<Knjiga> knjige) { this.knjige = knjige; }
}