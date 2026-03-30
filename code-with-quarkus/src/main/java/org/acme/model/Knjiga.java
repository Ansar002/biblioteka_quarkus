package org.acme.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@NamedQuery(name = Knjiga.GET_ALL_KNJIGE, query = "SELECT k FROM Knjiga k")
@NamedQuery(name = Knjiga.GET_KNJIGA_BY_NASLOV, query = "SELECT k FROM Knjiga k WHERE k.naslov = :naslov")
@NamedQuery(name = Knjiga.GET_KNJIGE_BY_AUTOR_ID, query = "SELECT k FROM Knjiga k JOIN k.autori a WHERE a.id = :autorId")
@NamedQuery(name = Knjiga.GET_KNJIGE_BY_KATEGORIJA_ID, query = "SELECT k FROM Knjiga k JOIN k.kategorije kat WHERE kat.id = :kategorijaId")
@NamedQuery(name = Knjiga.GET_KNJIGE_BY_IZDAVAC_ID, query = "SELECT k FROM Knjiga k WHERE k.izdavac.id = :izdavacId")
public class Knjiga {

    public static final String GET_ALL_KNJIGE = "SveKnjige";
    public static final String GET_KNJIGA_BY_NASLOV = "KnjigaPoNaslovu";
    public static final String GET_KNJIGE_BY_AUTOR_ID = "KnjigePoAutoruId";
    public static final String GET_KNJIGE_BY_KATEGORIJA_ID = "KnjigePoKategorijiId";
    public static final String GET_KNJIGE_BY_IZDAVAC_ID = "KnjigePoIzdavacuId";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "knjiga_seq")
    @SequenceGenerator(name = "knjiga_seq", sequenceName = "knjiga_seq", allocationSize = 1)
    private Long id;

    private String naslov;

    @OneToOne
    @JoinColumn(name = "izdavac_id")
    private Izdavac izdavac;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "knjiga_autor",
        joinColumns = @JoinColumn(name = "knjiga_id"),
        inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> autori;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "knjiga_kategorija",
        joinColumns = @JoinColumn(name = "knjiga_id"),
        inverseJoinColumns = @JoinColumn(name = "kategorija_id")
    )
    private List<Kategorija> kategorije;

    @JsonIgnore
    @OneToMany(mappedBy = "knjiga", fetch = FetchType.LAZY)
    private List<Pozajmica> pozajmice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public Izdavac getIzdavac() {
        return izdavac;
    }

    public void setIzdavac(Izdavac izdavac) {
        this.izdavac = izdavac;
    }

    public List<Autor> getAutori() {
        return autori;
    }

    public void setAutori(List<Autor> autori) {
        this.autori = autori;
    }

    public List<Kategorija> getKategorije() {
        return kategorije;
    }

    public void setKategorije(List<Kategorija> kategorije) {
        this.kategorije = kategorije;
    }

    public List<Pozajmica> getPozajmice() {
        return pozajmice;
    }

    public void setPozajmice(List<Pozajmica> pozajmice) {
        this.pozajmice = pozajmice;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Knjiga knjiga)) return false;
        return Objects.equals(id, knjiga.id) && Objects.equals(naslov, knjiga.naslov);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, naslov);
    }

    @Override
    public String toString() {
        return "Knjiga{" +
                "id=" + id +
                ", naslov='" + naslov + '\'' +
                '}';
    }
}