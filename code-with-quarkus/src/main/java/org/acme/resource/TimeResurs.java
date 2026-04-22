package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.acme.model.TimeResponse;
import org.acme.service.TimeService;

@Path("/timezone")
public class TimeResurs {

    @Inject
    TimeService timezoneService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public TimeResponse getTimezone() {
        return timezoneService.getTimezoneInfo();
    }
}