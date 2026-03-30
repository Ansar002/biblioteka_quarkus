package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.acme.exception.PozajmicaException;
import org.acme.model.Pozajmica;
import java.util.List;

@ApplicationScoped
public class PozajmicaServis {

    @Inject
    EntityManager em;

    @Transactional
    public Pozajmica sacuvaj(Pozajmica pozajmica) throws PozajmicaException {
        if (pozajmica == null) {
            throw new PozajmicaException("Pozajmica nije proslijeđena");
        }
        if (pozajmica.getKnjiga() == null || pozajmica.getClan() == null) {
            throw new PozajmicaException("Pozajmica mora imati povezanu Knjiga i Clan!");
        }
        return em.merge(pozajmica);
    }

    public List<Pozajmica> getAll() throws Exception {
        List<Pozajmica> pozajmice = em.createQuery("SELECT p FROM Pozajmica p", Pozajmica.class).getResultList();
        if (pozajmice.isEmpty()) {
            throw new Exception("Nema pozajmica.");
        }
        return pozajmice;
    }

    public Pozajmica getPozajmicaById(Long id) {
        return em.find(Pozajmica.class, id);
    }

    public List<Pozajmica> getPozajmiceByClanId(Long clanId) {
        Query query = em.createNamedQuery(Pozajmica.GET_POZAJMICE_BY_CLAN_ID);
        query.setParameter("clanId", clanId);
        return query.getResultList();
    }

    public List<Pozajmica> getPozajmiceByKnjigaId(Long knjigaId) {
        Query query = em.createNamedQuery(Pozajmica.GET_POZAJMICE_BY_KNJIGA_ID);
        query.setParameter("knjigaId", knjigaId);
        return query.getResultList();
    }
}
