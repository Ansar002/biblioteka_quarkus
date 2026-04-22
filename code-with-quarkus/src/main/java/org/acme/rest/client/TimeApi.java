package org.acme.rest.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.acme.model.TimeResponse;

@RegisterRestClient(configKey = "timeapi-api")
@Path("/api/time/current/ip")
public interface TimeApi{

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    TimeResponse getCurrentTimeByIp(@QueryParam("ipAddress") String ipAddress);
}