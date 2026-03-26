package org.acme.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@NamedQuery(name = Izdavac.GET_ALL_IZDAVACI, query = "SELECT i FROM Izdavac i")
@NamedQuery(name = Izdavac.GET_IZDAVAC_BY_NAZIV, query = "SELECT i FROM Izdavac i WHERE i.naziv = :naziv")
public class Izdavac {

    public static final String GET_ALL_IZDAVACI = "SviIzdavaci";
    public static final String GET_IZDAVAC_BY_NAZIV = "IzdavacPoNazivu";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "izdavac_seq")
    @SequenceGenerator(name = "izdavac_seq", sequenceName = "izdavac_seq", allocationSize = 1)
    private Long id;

    private String naziv;

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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Izdavac izdavac)) return false;
        return Objects.equals(id, izdavac.id) && Objects.equals(naziv, izdavac.naziv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, naziv);
    }

    @Override
    public String toString() {
        return "Izdavac{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                '}';
    }
}