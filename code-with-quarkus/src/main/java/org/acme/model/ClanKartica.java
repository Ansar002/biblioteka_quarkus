package org.acme.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class ClanKartica {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clan_kartica_seq")
    @SequenceGenerator(name = "clan_kartica_seq", sequenceName = "clan_kartica_seq", allocationSize = 1)
    private Long id;

    private String brojKarte;
    private LocalDate datumIzdavanja;
    private LocalDate datumIsteka;
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrojKarte() {
        return brojKarte;
    }

    public void setBrojKarte(String brojKarte) {
        this.brojKarte = brojKarte;
    }

    public LocalDate getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(LocalDate datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public LocalDate getDatumIsteka() {
        return datumIsteka;
    }

    public void setDatumIsteka(LocalDate datumIsteka) {
        this.datumIsteka = datumIsteka;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ClanKartica clanKartica)) return false;
        return Objects.equals(id, clanKartica.id) && Objects.equals(brojKarte, clanKartica.brojKarte);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brojKarte);
    }

    @Override
    public String toString() {
        return "ClanKartica{" +
                "id=" + id +
                ", brojKarte='" + brojKarte + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
