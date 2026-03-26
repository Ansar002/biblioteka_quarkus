package org.acme.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@NamedQuery(name = Pozajmica.GET_ALL_POZAJMICE, query = "SELECT p FROM Pozajmica p")
@NamedQuery(name = Pozajmica.GET_POZAJMICE_BY_CLAN_ID, query = "SELECT p FROM Pozajmica p WHERE p.clan.id = :clanId")
@NamedQuery(name = Pozajmica.GET_POZAJMICE_BY_KNJIGA_ID, query = "SELECT p FROM Pozajmica p WHERE p.knjiga.id = :knjigaId")
public class Pozajmica {

    public static final String GET_ALL_POZAJMICE = "SvePozajmice";
    public static final String GET_POZAJMICE_BY_CLAN_ID = "PozajmicePoClanuId";
    public static final String GET_POZAJMICE_BY_KNJIGA_ID = "PozajmicePoKnjiziId";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pozajmica_seq")
    @SequenceGenerator(name = "pozajmica_seq", sequenceName = "pozajmica_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "knjiga_id")
    private Knjiga knjiga;

    @ManyToOne
    @JoinColumn(name = "clan_id")
    private Clan clan;

    private LocalDate datumPozajmice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Knjiga getKnjiga() {
        return knjiga;
    }

    public void setKnjiga(Knjiga knjiga) {
        this.knjiga = knjiga;
    }

    public Clan getClan() {
        return clan;
    }

    public void setClan(Clan clan) {
        this.clan = clan;
    }

    public LocalDate getDatumPozajmice() {
        return datumPozajmice;
    }

    public void setDatumPozajmice(LocalDate datum) {
        this.datumPozajmice = datum;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pozajmica pozajmica)) return false;
        return Objects.equals(id, pozajmica.id) &&
                Objects.equals(datumPozajmice, pozajmica.datumPozajmice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, datumPozajmice);
    }

    @Override
    public String toString() {
        return "Pozajmica{" +
                "id=" + id +
                ", datumPozajmice=" + datumPozajmice +
                '}';
    }
}