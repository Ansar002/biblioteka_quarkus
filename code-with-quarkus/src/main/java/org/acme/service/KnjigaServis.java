package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.acme.model.Knjiga;
import java.util.List;

@ApplicationScoped
public class KnjigaServis {

    @Inject
    EntityManager em;

    @Transactional
    public void sacuvaj(Knjiga knjiga) {
        em.persist(knjiga);
    }

    public List<Knjiga> getAll() {
        return em.createQuery("SELECT k FROM Knjiga k", Knjiga.class).getResultList();
    }
}