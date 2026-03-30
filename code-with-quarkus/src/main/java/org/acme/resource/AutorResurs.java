package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.Autor;
import org.acme.model.Knjiga;
import org.acme.service.AutorServis;
import java.util.List;

@Path("/autori")
public class AutorResurs {

    @Inject
    private AutorServis autorServis;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addAutor")
    public Response addAutor(Autor autor) {
        try {
            autorServis.sacuvaj(autor);
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllAutori")
    public Response getAllAutori() {
        List<Autor> autori = null;
        try {
            autori = autorServis.getAll();
        } catch (Exception e) {
            return Response.status(Response.Status.NO_CONTENT).entity(e.getMessage()).build();
        }
        return Response.ok().entity(autori).build();
    }

    @GET
    @Path("/getAutorByIme")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAutorByIme(@QueryParam("ime") String ime) {
        try {
            List<Autor> autori = autorServis.getAutorByIme(ime);
            return Response.ok().entity(autori).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NO_CONTENT).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/getAutorById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAutorById(@PathParam("id") Long id) {
        try {
            Autor autor = autorServis.getAutorById(id);
            return Response.ok().entity(autor).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}/knjige")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getKnjigeByAutorId(@PathParam("id") Long id) {
        try {
            List<Knjiga> knjige = autorServis.getKnjigeByAutorId(id);
            return Response.ok().entity(knjige).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NO_CONTENT).entity(e.getMessage()).build();
        }
    }
}
