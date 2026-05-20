// src/main/java/org/acme/resource/KnjigaFajloviResurs.java

package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.exception.KnjigaNotFoundException;
import org.acme.model.FileUploadInput;
import org.acme.model.Knjiga;
import org.acme.model.UploadedFile;
import org.acme.service.KnjigaFajloviServis;
import org.jboss.resteasy.reactive.MultipartForm;

import java.io.IOException;

@Path("/knjige")
public class KnjigaFajloviResurs {

    @Inject
    KnjigaFajloviServis servis;

    // POST /knjige/upload?knjigaId=1
    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadFajl(
            @QueryParam("knjigaId") Long knjigaId,
            @MultipartForm FileUploadInput input) {

        if (knjigaId == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Parametar knjigaId je obavezan.").build();
        }
        if (input.filename == null || input.filename.isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Naziv fajla je obavezan.").build();
        }
        if (input.file == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Fajl nije proslijeđen.").build();
        }

        try {
            UploadedFile rezultat = servis.uploadFajl(
                    knjigaId,
                    input.filename,
                    input.file.uploadedFile()
            );

            if (rezultat == null) {
                // Fajl već postoji na fajlsistemu
                return Response.status(Response.Status.CONFLICT)
                        .entity("Fajl sa imenom '" + input.filename +
                                "' već postoji. Koristi se postojeća putanja.")
                        .build();
            }

            return Response.status(Response.Status.CREATED)
                    .entity(rezultat).build();

        } catch (KnjigaNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage()).build();
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Greška pri čuvanju fajla: " + e.getMessage()).build();
        }
    }

    // GET /knjige/{id}/sa-fajlovima
    @GET
    @Path("/{id}/sa-fajlovima")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getKnjigaSaFajlovima(@PathParam("id") Long id) {
        try {
            Knjiga knjiga = servis.getKnjigaSaFajlovima(id);
            return Response.ok(knjiga).build();
        } catch (KnjigaNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage()).build();
        }
    }
}