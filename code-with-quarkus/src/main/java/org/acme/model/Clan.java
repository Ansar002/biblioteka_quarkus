package org.acme.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@NamedQuery(name = Clan.GET_ALL_CLANOVI, query = "SELECT c FROM Clan c")
@NamedQuery(name = Clan.GET_CLAN_BY_NAME, query = "SELECT c FROM Clan c WHERE c.ime = :ime AND c.prezime = :prezime")
public class Clan {

    public static final String GET_ALL_CLANOVI = "SviClanovi";
    public static final String GET_CLAN_BY_NAME = "ClanPoImenu";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clan_seq")
    @SequenceGenerator(name = "clan_seq", sequenceName = "clan_seq", allocationSize = 1)
    private Long id;

    private String ime;
    private String prezime;

    @OneToOne
    @JoinColumn(name = "clan_kartica_id")
    private ClanKartica clanKartica;

    @JsonIgnore
    @OneToMany(mappedBy = "clan", fetch = FetchType.LAZY)
    private List<Pozajmica> pozajmice;

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

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public ClanKartica getClanKartica() {
        return clanKartica;
    }

    public void setClanKartica(ClanKartica clanKartica) {
        this.clanKartica = clanKartica;
    }

    public List<Pozajmica> getPozajmice() {
        return pozajmice;
    }

    public void setPozajmice(List<Pozajmica> pozajmice) {
        this.pozajmice = pozajmice;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Clan clan)) return false;
        return Objects.equals(id, clan.id) &&
                Objects.equals(ime, clan.ime) &&
                Objects.equals(prezime, clan.prezime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ime, prezime);
    }

    @Override
    public String toString() {
        return "Clan{" +
                "id=" + id +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                '}';
    }
}