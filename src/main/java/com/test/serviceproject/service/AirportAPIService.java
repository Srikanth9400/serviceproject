package com.test.serviceproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AirportAPIService {

    private final RestTemplate restTemplate;
    private final String airportApiUrl = "http://localhost:8080/airports";
    private final String username = "user";
    private final String password = "secret123";

    @Autowired
    public AirportAPIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getAirportData() {

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(airportApiUrl, HttpMethod.GET, entity, String.class);

        return response.getBody();
    }
}
