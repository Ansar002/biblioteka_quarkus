package org.acme.schedulers;

import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.acme.model.Pozajmica;
import java.util.List;

@ApplicationScoped
public class PozajmicaScheduler {

    @Inject
    EntityManager em;

    @Scheduled(every = "10s")
    public void proveraPozajmica() {
        List<Pozajmica> pozajmice = em.createQuery("SELECT p FROM Pozajmica p", Pozajmica.class)
                .getResultList();
        
        System.out.println("*** Provjera pozajmica ***");
        System.out.println("Ukupno pozajmica: " + pozajmice.size());
        
        for (Pozajmica p : pozajmice) {
            System.out.println("Pozajmica ID: " + p.getId() + 
                    " | Knjiga: " + p.getKnjiga().getNaslov() + 
                    " | Clan: " + p.getClan().getIme());
        }
    }
}

