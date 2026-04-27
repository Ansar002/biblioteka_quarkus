package org.acme.rest.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "ipify-api")
@Path("/")
public interface IpifyKlijent {

    @GET
    @Produces(MediaType.TEXT_PLAIN)   
    String getPublicIp();
}