package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.acme.exception.KnjigaNotFoundException;
import org.acme.model.Autor;
import org.acme.model.Kategorija;
import org.acme.model.Knjiga;
import org.acme.model.Pozajmica;
import java.util.List;

@ApplicationScoped
public class KnjigaServis {

    @Inject
    EntityManager em;

    @Transactional
    public Knjiga sacuvaj(Knjiga knjiga) throws Exception {
        if (knjiga == null) {
            throw new Exception("Knjiga nije proslijeđena");
        }
        if (knjiga.getNaslov() == null || knjiga.getNaslov().isEmpty()) {
            throw new Exception("Naslov je prazan");
        }
        return em.merge(knjiga);
    }

    public List<Knjiga> getAll() throws Exception {
        List<Knjiga> knjige = em.createQuery("SELECT k FROM Knjiga k", Knjiga.class).getResultList();
        if (knjige.isEmpty()) {
            throw new Exception("Nema knjiga.");
        }
        return knjige;
    }

    public List<Knjiga> getKnjigaByNaslov(String naslov) {
        Query query = em.createNamedQuery(Knjiga.GET_KNJIGA_BY_NASLOV);
        query.setParameter("naslov", naslov);
        return query.getResultList();
    }

    public Knjiga getKnjigaById(Long id) throws KnjigaNotFoundException {
        Knjiga knjiga = em.find(Knjiga.class, id);
        if (knjiga == null) {
            throw new KnjigaNotFoundException("Knjiga sa ID: " + id + " nije pronađena!");
        }
        return knjiga;
    }

    public List<Knjiga> getKnjigeByAutorId(Long autorId) {
        Query query = em.createNamedQuery(Knjiga.GET_KNJIGE_BY_AUTOR_ID);
        query.setParameter("autorId", autorId);
        return query.getResultList();
    }

    public List<Knjiga> getKnjigeByKategorijaId(Long kategorijaId) {
        Query query = em.createNamedQuery(Knjiga.GET_KNJIGE_BY_KATEGORIJA_ID);
        query.setParameter("kategorijaId", kategorijaId);
        return query.getResultList();
    }

    public List<Autor> getAutoriByKnjigaId(Long knjigaId) throws KnjigaNotFoundException {
        Knjiga knjiga = getKnjigaById(knjigaId);
        return knjiga.getAutori();
    }

    public List<Kategorija> getKategorijeByKnjigaId(Long knjigaId) throws KnjigaNotFoundException {
        Knjiga knjiga = getKnjigaById(knjigaId);
        return knjiga.getKategorije();
    }

    public List<Pozajmica> getPozajmiceByKnjigaId(Long knjigaId) throws KnjigaNotFoundException {
        Knjiga knjiga = getKnjigaById(knjigaId);
        return knjiga.getPozajmice();
    }
}