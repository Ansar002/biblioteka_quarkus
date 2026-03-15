package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.Knjiga;
import org.acme.service.KnjigaServis;
import java.util.List;

@Path("/knjige")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class KnjigaResurs {

    @Inject
    KnjigaServis knjigaServis;

    @POST
    public Response sacuvaj(Knjiga knjiga) {
        knjigaServis.sacuvaj(knjiga);
        return Response.ok().build();
    }

    @GET
    public List<Knjiga> getAll() {
        return knjigaServis.getAll();
    }
}