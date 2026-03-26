package org.acme.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQuery(name = Autor.GET_ALL_AUTORI, query = "SELECT a FROM Autor a")
@NamedQuery(name = Autor.GET_AUTOR_BY_IME, query = "SELECT a FROM AUTOR a WHERE a.ime = :ime")
public class Autor {

    public static final String GET_ALL_AUTORI = "SviAutori";
    public static final String GET_AUTOR_BY_IME = "SviAutorByIme";


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "autor_seq")
    @SequenceGenerator(name = "autor_seq", sequenceName = "autor_seq", allocationSize = 1)
    private Long id;
    private String ime;

    @ManyToMany(mappedBy = "autori")
    private List<Knjiga> knjige;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public List<Knjiga> getKnjige() {
        return knjige;
    }

    public void setKnjige(List<Knjiga> knjige) {
        this.knjige = knjige;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Autor autor)) return false;
        return Objects.equals(id, autor.id) && Objects.equals(ime, autor.ime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ime);
    }

    @Override
    public String toString() {
        return "Autor{" +
            "id=" + id +
            ", ime='" + ime + '\'' +
            '}';
    }
}