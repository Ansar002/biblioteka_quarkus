package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.Kategorija;
import org.acme.model.Knjiga;
import org.acme.service.KategorijaServis;
import java.util.List;

@Path("/kategorije")
public class KategorijaResurs {

    @Inject
    private KategorijaServis kategorijaServis;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addKategorija")
    public Response addKategorija(Kategorija kategorija) {
        try {
            kategorijaServis.sacuvaj(kategorija);
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllKategorije")
    public Response getAllKategorije() {
        List<Kategorija> kategorije = null;
        try {
            kategorije = kategorijaServis.getAll();
        } catch (Exception e) {
            return Response.status(Response.Status.NO_CONTENT).entity(e.getMessage()).build();
        }
        return Response.ok().entity(kategorije).build();
    }

    @GET
    @Path("/getKategorijaByNaziv")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getKategorijaByNaziv(@QueryParam("naziv") String naziv) {
        try {
            List<Kategorija> kategorije = kategorijaServis.getKategorijaByNaziv(naziv);
            return Response.ok().entity(kategorije).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NO_CONTENT).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/getKategorijaById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getKategorijaById(@PathParam("id") Long id) {
        try {
            Kategorija kategorija = kategorijaServis.getKategorijaById(id);
            return Response.ok().entity(kategorija).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}/knjige")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getKnjigeByKategorijaId(@PathParam("id") Long id) {
        try {
            List<Knjiga> knjige = kategorijaServis.getKnjigeByKategorijaId(id);
            return Response.ok().entity(knjige).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NO_CONTENT).entity(e.getMessage()).build();
        }
    }
}
