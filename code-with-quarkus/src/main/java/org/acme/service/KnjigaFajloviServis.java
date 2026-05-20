// src/main/java/org/acme/service/KnjigaFajloviServis.java

package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.acme.exception.KnjigaNotFoundException;
import org.acme.model.Knjiga;
import org.acme.model.UploadedFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@ApplicationScoped
public class KnjigaFajloviServis {

    private static final String UPLOAD_DIR = "/tmp/biblioteka-uploads/";

    @Inject
    EntityManager em;

    @Transactional
    public UploadedFile uploadFajl(Long knjigaId, String filename, Path tempFile)
            throws KnjigaNotFoundException, IOException {

        // 1. Učitaj knjigu
        Knjiga knjiga = em.find(Knjiga.class, knjigaId);
        if (knjiga == null) {
            throw new KnjigaNotFoundException("Knjiga sa ID: " + knjigaId + " nije pronađena!");
        }

        // 2. Pripremi upload direktorij
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Path targetPath = uploadPath.resolve(filename);

        // 3. Provjeri da li fajl već postoji
        if (Files.exists(targetPath)) {
            UploadedFile postojeci = em.createQuery(
                            "SELECT u FROM UploadedFile u WHERE u.filename = :filename", UploadedFile.class)
                    .setParameter("filename", targetPath.toString())
                    .getResultStream()
                    .findFirst()
                    .orElse(null);

            if (postojeci == null) {
                postojeci = new UploadedFile();
                postojeci.setFilename(targetPath.toString());
                em.persist(postojeci);
            }


            if (!knjiga.getUploadedFiles().contains(postojeci)) {
                knjiga.getUploadedFiles().add(postojeci);
                em.merge(knjiga);
            }


            return null;
        }

        // 4. Kopiraj fajl na ciljnu lokaciju
        Files.copy(tempFile, targetPath, StandardCopyOption.REPLACE_EXISTING);

        // 5. Kreiraj UploadedFile entitet
        UploadedFile novi = new UploadedFile();
        novi.setFilename(targetPath.toString());
        em.persist(novi);

        // 6. Poveži s knjigom
        knjiga.getUploadedFiles().add(novi);
        em.merge(knjiga);

        return novi;
    }

    public Knjiga getKnjigaSaFajlovima(Long knjigaId) throws KnjigaNotFoundException {
        Knjiga knjiga = em.find(Knjiga.class, knjigaId);
        if (knjiga == null) {
            throw new KnjigaNotFoundException("Knjiga sa ID: " + knjigaId + " nije pronađena!");
        }

        // Učitaj File objekte iz filename putanja u @Transient polje
        for (UploadedFile uf : knjiga.getUploadedFiles()) {
            if (uf.getFilename() != null) {
                File f = new File(uf.getFilename());
                if (f.exists()) {
                    uf.setFile(f);
                }
            }
        }

        return knjiga;
    }
}