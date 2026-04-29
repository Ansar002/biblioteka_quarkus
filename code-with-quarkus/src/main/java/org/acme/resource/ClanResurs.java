package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.exception.ClanNotFoundException;
import org.acme.model.*;
import org.acme.service.ClanServis;
import org.acme.rest.client.IpifyKlijent;
import org.acme.rest.client.TimeApi;
import org.acme.rest.client.valutaKlijent;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import jakarta.transaction.Transactional;
import java.util.List;

@Path("/clanovi")
public class ClanResurs {

    @Inject
    private ClanServis clanServis;

    @Inject
    @RestClient
    private IpifyKlijent ipifyKlijent;

    @Inject
    @RestClient
    private TimeApi timeApi;

    @Inject
    @RestClient
    private valutaKlijent valutaKlijent;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addClan")
    public Response addClan(Clan clan) {
        try {
            clanServis.sacuvaj(clan);
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllClanovi")
    public Response getAllClanovi() {
        List<Clan> clanovi = null;
        try {
            clanovi = clanServis.getAll();
        } catch (Exception e) {
            return Response.status(Response.Status.NO_CONTENT).entity(e.getMessage()).build();
        }
        return Response.ok().entity(clanovi).build();
    }

    @GET
    @Path("/getClanByName")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClanByName(@QueryParam("ime") String ime, @QueryParam("prezime") String prezime) {
        try {
            List<Clan> clanovi = clanServis.getClanByName(ime, prezime);
            return Response.ok().entity(clanovi).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NO_CONTENT).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/getClanById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClanById(@PathParam("id") Long id) {
        try {
            Clan clan = clanServis.getClanById(id);
            return Response.ok().entity(clan).build();
        } catch (ClanNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}/pozajmice")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPozajmiceByClanId(@PathParam("id") Long id) {
        try {
            List<Pozajmica> pozajmice = clanServis.getPozajmiceByClanId(id);
            return Response.ok().entity(pozajmice).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NO_CONTENT).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/getTimezoneByIP")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response getTimezoneByIP(@QueryParam("userId") Long userId) {
        try {
            Clan clan = clanServis.getClanById(userId);

            String ip = ipifyKlijent.getPublicIp();
            TimeResponse timeResponse = timeApi.getCurrentTimeByIp(ip);

            Timezone timezone = new Timezone();
            timezone.setIp(ip);
            timezone.setTimeZone(timeResponse.getTimeZone());
            timezone.setDateTime(timeResponse.getDateTime());
            timezone.setDate(timeResponse.getDate());
            timezone.setTime(timeResponse.getTime());
            timezone.setDayOfWeek(timeResponse.getDayOfWeek());
            timezone.setDstActive(timeResponse.isDstActive());

            clan.getTimezones().add(timezone);
            clanServis.azuriraj(clan);

            return Response.ok().entity(clan).build();
        } catch (ClanNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/currencyConversion")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response currencyConversion(@QueryParam("from") String from, @QueryParam("to") String to, @QueryParam("value") double value, @QueryParam("userId") Long userId) {
        try {
            Clan clan = clanServis.getClanById(userId);

            CurrencyResponse currencyResponse = valutaKlijent.getRates(from, to);

            currencyResponse.setValue(value);
            currencyResponse.setConvertedValue(value * currencyResponse.getRate());

            CurrencyEntitet entitet = new CurrencyEntitet();
            entitet.setFromCurrency(currencyResponse.getFrom());
            entitet.setToCurrency(currencyResponse.getTo());
            entitet.setRate(currencyResponse.getRate());
            entitet.setValue(currencyResponse.getValue());
            entitet.setConvertedValue(currencyResponse.getConvertedValue());

            clan.getCurrencys().add(entitet);
            clanServis.azuriraj(clan);

            return Response.ok().entity(currencyResponse).build();
        } catch (ClanNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
