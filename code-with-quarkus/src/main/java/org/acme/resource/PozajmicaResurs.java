package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.exception.PozajmicaException;
import org.acme.model.Pozajmica;
import org.acme.service.PozajmicaServis;
import java.util.List;

@Path("/pozajmice")
public class PozajmicaResurs {

    @Inject
    private PozajmicaServis pozajmicaServis;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addPozajmica")
    public Response addPozajmica(Pozajmica pozajmica) {
        try {
            pozajmicaServis.sacuvaj(pozajmica);
        } catch (PozajmicaException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllPozajmice")
    public Response getAllPozajmice() {
        List<Pozajmica> pozajmice = null;
        try {
            pozajmice = pozajmicaServis.getAll();
        } catch (Exception e) {
            return Response.status(Response.Status.NO_CONTENT).entity(e.getMessage()).build();
        }
        return Response.ok().entity(pozajmice).build();
    }

    @GET
    @Path("/getPozajmicaById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPozajmicaById(@PathParam("id") Long id) {
        try {
            Pozajmica pozajmica = pozajmicaServis.getPozajmicaById(id);
            return Response.ok().entity(pozajmica).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/getPozajmiceByClanId")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPozajmiceByClanId(@QueryParam("clanId") Long clanId) {
        try {
            List<Pozajmica> pozajmice = pozajmicaServis.getPozajmiceByClanId(clanId);
            return Response.ok().entity(pozajmice).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NO_CONTENT).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/getPozajmiceByKnjigaId")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPozajmiceByKnjigaId(@QueryParam("knjigaId") Long knjigaId) {
        try {
            List<Pozajmica> pozajmice = pozajmicaServis.getPozajmiceByKnjigaId(knjigaId);
            return Response.ok().entity(pozajmice).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NO_CONTENT).entity(e.getMessage()).build();
        }
    }
}
