package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.acme.exception.ClanNotFoundException;
import org.acme.model.Clan;
import org.acme.model.Pozajmica;
import java.util.List;

@ApplicationScoped
public class ClanServis {

    @Inject
    EntityManager em;

    @Transactional
    public Clan sacuvaj(Clan clan) throws Exception {
        if (clan == null) {
            throw new Exception("Član nije proslijeđen");
        }
        if (clan.getIme() == null || clan.getIme().isEmpty()) {
            throw new Exception("Ime je prazno");
        }
        if (clan.getPrezime() == null || clan.getPrezime().isEmpty()) {
            throw new Exception("Prezime je prazno");
        }

        // Prvo persist kartice, pa onda clana
        if (clan.getClanKartica() != null) {
            em.persist(clan.getClanKartica());
        }

        em.persist(clan);
        return clan;
    }

    public List<Clan> getAll() throws Exception {
        List<Clan> clanovi = em.createQuery("SELECT c FROM Clan c", Clan.class).getResultList();
        if (clanovi.isEmpty()) {
            throw new Exception("Nema članova.");
        }
        return clanovi;
    }

    public List<Clan> getClanByName(String ime, String prezime) {
        Query query = em.createNamedQuery(Clan.GET_CLAN_BY_NAME);
        query.setParameter("ime", ime);
        query.setParameter("prezime", prezime);
        return query.getResultList();
    }

    public Clan getClanById(Long id) throws ClanNotFoundException {
        Clan clan = em.find(Clan.class, id);
        if (clan == null) {
            throw new ClanNotFoundException("Član sa ID: " + id + " nije pronađen!");
        }
        return clan;
    }

    public List<Pozajmica> getPozajmiceByClanId(Long clanId) throws ClanNotFoundException {
        Clan clan = getClanById(clanId);
        return clan.getPozajmice();
    }
}

