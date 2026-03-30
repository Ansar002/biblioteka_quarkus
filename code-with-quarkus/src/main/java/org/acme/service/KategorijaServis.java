package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.acme.model.Kategorija;
import org.acme.model.Knjiga;
import java.util.List;

@ApplicationScoped
public class KategorijaServis {

    @Inject
    EntityManager em;

    @Transactional
    public Kategorija sacuvaj(Kategorija kategorija) throws Exception {
        if (kategorija == null) {
            throw new Exception("Kategorija nije proslijeđena");
        }
        if (kategorija.getNaziv() == null || kategorija.getNaziv().isEmpty()) {
            throw new Exception("Naziv je prazan");
        }
        return em.merge(kategorija);
    }

    public List<Kategorija> getAll() throws Exception {
        List<Kategorija> kategorije = em.createQuery("SELECT k FROM Kategorija k", Kategorija.class).getResultList();
        if (kategorije.isEmpty()) {
            throw new Exception("Nema kategorija.");
        }
        return kategorije;
    }

    public List<Kategorija> getKategorijaByNaziv(String naziv) {
        Query query = em.createNamedQuery(Kategorija.GET_KATEGORIJA_BY_NAZIV);
        query.setParameter("naziv", naziv);
        return query.getResultList();
    }

    public Kategorija getKategorijaById(Long id) {
        return em.find(Kategorija.class, id);
    }

    public List<Knjiga> getKnjigeByKategorijaId(Long kategorijaId) {
        Kategorija kategorija = getKategorijaById(kategorijaId);
        if (kategorija != null) {
            return kategorija.getKnjige();
        }
        return List.of();
    }
}
