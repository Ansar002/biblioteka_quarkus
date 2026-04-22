package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.acme.rest.client.IpifyKlijent;
import org.acme.rest.client.TimeApi;
import org.acme.model.TimeResponse;

@ApplicationScoped
public class TimeService {

    @RestClient
    IpifyKlijent ipifyClient;


    @RestClient
    TimeApi timeApiClient;

    public TimeResponse getTimezoneInfo() {
        // 1. Dobij IP adresu
        String ip = ipifyClient.getPublicIp();

        // 2. Iskoristi IP da dobiješ podatke o vremenskoj zoni
        return timeApiClient.getCurrentTimeByIp(ip);
    }
}