package org.acme.rest.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.acme.model.CurrencyResponse;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "euroratesapi-api")
@Path("/")
public interface valutaKlijent {

    @GET
    @Path("api/rates")
    @Produces(MediaType.APPLICATION_JSON)
    CurrencyResponse getRates(@QueryParam("from") String from, @QueryParam("to") String to);
}
