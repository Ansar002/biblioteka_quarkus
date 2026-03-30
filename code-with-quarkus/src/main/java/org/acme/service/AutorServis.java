package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.acme.model.Autor;
import org.acme.model.Knjiga;
import java.util.List;

@ApplicationScoped
public class AutorServis {

    @Inject
    EntityManager em;

    @Transactional
    public Autor sacuvaj(Autor autor) throws Exception {
        if (autor == null) {
            throw new Exception("Autor nije proslijeđen");
        }
        if (autor.getIme() == null || autor.getIme().isEmpty()) {
            throw new Exception("Ime je prazno");
        }
        return em.merge(autor);
    }

    public List<Autor> getAll() throws Exception {
        List<Autor> autori = em.createQuery("SELECT a FROM Autor a", Autor.class).getResultList();
        if (autori.isEmpty()) {
            throw new Exception("Nema autora.");
        }
        return autori;
    }

    public List<Autor> getAutorByIme(String ime) {
        Query query = em.createNamedQuery(Autor.GET_AUTOR_BY_IME);
        query.setParameter("ime", ime);
        return query.getResultList();
    }

    public Autor getAutorById(Long id) {
        return em.find(Autor.class, id);
    }

    public List<Knjiga> getKnjigeByAutorId(Long autorId) {
        Autor autor = getAutorById(autorId);
        if (autor != null) {
            return autor.getKnjige();
        }
        return List.of();
    }
}
