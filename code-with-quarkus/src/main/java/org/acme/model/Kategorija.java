package org.acme.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@NamedQuery(name = Kategorija.GET_ALL_KATEGORIJE, query = "SELECT k FROM Kategorija k")
@NamedQuery(name = Kategorija.GET_KATEGORIJA_BY_NAZIV, query = "SELECT k FROM Kategorija k WHERE k.naziv = :naziv")
public class Kategorija {

    public static final String GET_ALL_KATEGORIJE = "SveKategorije";
    public static final String GET_KATEGORIJA_BY_NAZIV = "KategorijaPoNazivu";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "kategorija_seq")
    @SequenceGenerator(name = "kategorija_seq", sequenceName = "kategorija_seq", allocationSize = 1)
    private Long id;

    private String naziv;

    @JsonIgnore
    @ManyToMany(mappedBy = "kategorije", fetch = FetchType.LAZY)
    private List<Knjiga> knjige;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public List<Knjiga> getKnjige() {
        return knjige;
    }

    public void setKnjige(List<Knjiga> knjige) {
        this.knjige = knjige;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Kategorija kategorija)) return false;
        return Objects.equals(id, kategorija.id) && Objects.equals(naziv, kategorija.naziv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, naziv);
    }

    @Override
    public String toString() {
        return "Kategorija{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                '}';
    }
}