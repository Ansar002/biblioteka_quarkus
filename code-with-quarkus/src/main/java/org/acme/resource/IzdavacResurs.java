package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.Izdavac;
import org.acme.service.IzdavacServis;
import java.util.List;

@Path("/izdavaci")
public class IzdavacResurs {

    @Inject
    private IzdavacServis izdavacServis;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addIzdavac")
    public Response addIzdavac(Izdavac izdavac) {
        try {
            Izdavac saved = izdavacServis.sacuvaj(izdavac);
            return Response.ok().entity(saved).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllIzdavaci")
    public Response getAllIzdavaci() {
        List<Izdavac> izdavaci = null;
        try {
            izdavaci = izdavacServis.getAll();
        } catch (Exception e) {
            return Response.status(Response.Status.NO_CONTENT).entity(e.getMessage()).build();
        }
        return Response.ok().entity(izdavaci).build();
    }

    @GET
    @Path("/getIzdavacByNaziv")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIzdavacByNaziv(@QueryParam("naziv") String naziv) {
        try {
            List<Izdavac> izdavaci = izdavacServis.getIzdavacByNaziv(naziv);
            return Response.ok().entity(izdavaci).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NO_CONTENT).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/getIzdavacById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIzdavacById(@PathParam("id") Long id) {
        try {
            Izdavac izdavac = izdavacServis.getIzdavacById(id);
            return Response.ok().entity(izdavac).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
