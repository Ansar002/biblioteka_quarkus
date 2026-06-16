package org.acme.resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.exception.KnjigaNotFoundException;
import org.acme.model.Autor;
import org.acme.model.Kategorija;
import org.acme.model.Knjiga;
import org.acme.model.Pozajmica;
import org.acme.service.KnjigaServis;
import java.util.List;


@Path("/knjige")
public class KnjigaResurs {

    @Inject
    private KnjigaServis knjigaServis;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addKnjiga")
    public Response addKnjiga(Knjiga knjiga) {
        try {
            knjigaServis.sacuvaj(knjiga);
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllKnjige")
    public Response getAllKnjige() {
        List<Knjiga> knjige = null;
        try {
            knjige = knjigaServis.getAll();
        } catch (Exception e) {
            return Response.status(Response.Status.NO_CONTENT).entity(e.getMessage()).build();
        }
        return Response.ok().entity(knjige).build();
    }

    @GET
    @Path("/getKnjigaByNaslov")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getKnjigaByNaslov(@QueryParam("naslov") String naslov) {
        try {
            List<Knjiga> knjige = knjigaServis.getKnjigaByNaslov(naslov);
            return Response.ok().entity(knjige).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NO_CONTENT).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/getKnjigaById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getKnjigaById(@PathParam("id") Long id) {
        try {
            Knjiga knjiga = knjigaServis.getKnjigaById(id);
            return Response.ok().entity(knjiga).build();
        } catch (KnjigaNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }





    @GET
    @Path("/getKnjigeByAutorId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getKnjigeByAutorId(@PathParam("id") Long id) {
        try {
            List<Knjiga> knjige = knjigaServis.getKnjigeByAutorId(id);
            return Response.ok().entity(knjige).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NO_CONTENT).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/getKnjigeByKategorijaId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getKnjigeByKategorijaId(@PathParam("id") Long id) {
        try {
            List<Knjiga> knjige = knjigaServis.getKnjigeByKategorijaId(id);
            return Response.ok().entity(knjige).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NO_CONTENT).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}/autori")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAutoriByKnjigaId(@PathParam("id") Long id) {
        try {
            List<Autor> autori = knjigaServis.getAutoriByKnjigaId(id);
            return Response.ok().entity(autori).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NO_CONTENT).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}/kategorije")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getKategorijeByKnjigaId(@PathParam("id") Long id) {
        try {
            List<Kategorija> kategorije = knjigaServis.getKategorijeByKnjigaId(id);
            return Response.ok().entity(kategorije).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NO_CONTENT).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}/pozajmice")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPozajmiceByKnjigaId(@PathParam("id") Long id) {
        try {
            List<Pozajmica> pozajmice = knjigaServis.getPozajmiceByKnjigaId(id);
            return Response.ok().entity(pozajmice).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NO_CONTENT).entity(e.getMessage()).build();
        }
    }
}