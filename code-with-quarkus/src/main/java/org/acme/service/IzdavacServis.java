package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.acme.model.Izdavac;
import java.util.List;

@ApplicationScoped
public class IzdavacServis {

    @Inject
    EntityManager em;

    @Transactional
    public Izdavac sacuvaj(Izdavac izdavac) throws Exception {
        if (izdavac == null) {
            throw new Exception("Izdavač nije proslijeđen");
        }
        if (izdavac.getNaziv() == null || izdavac.getNaziv().isEmpty()) {
            throw new Exception("Naziv je prazan");
        }
        em.persist(izdavac);
        return izdavac;
    }

    public List<Izdavac> getAll() throws Exception {
        List<Izdavac> izdavaci = em.createQuery("SELECT i FROM Izdavac i", Izdavac.class).getResultList();
        if (izdavaci.isEmpty()) {
            throw new Exception("Nema izdavača.");
        }
        return izdavaci;
    }

    public List<Izdavac> getIzdavacByNaziv(String naziv) {
        Query query = em.createNamedQuery(Izdavac.GET_IZDAVAC_BY_NAZIV);
        query.setParameter("naziv", naziv);
        return query.getResultList();
    }

    public Izdavac getIzdavacById(Long id) {
        return em.find(Izdavac.class, id);
    }
}