package com.test.serviceproject.service;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.test.serviceproject.model.Location;

@Service
public class FaresAPIService {
	
	
	@Value("${fares.api.url}")
    private String faresApiUrl;
	
	@Value("${airports.api.url}")
    private String airportApiUrl;

    @Value("${fares.api.username}")
    private String username;

    @Value("${fares.api.password}")
    private String password;
    
    private final RestTemplate restTemplate;

    public FaresAPIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getFares(String origin, String destination) {
    	
    	Callable<ResponseEntity<Location>> originDetails = callShow("en", origin);
    	Callable<ResponseEntity<Location>> destinationDetails = callShow("en", destination);
    	
        String fareResponse = faresApiUrl + "/" + origin + "/" + destination;

        // Fetch origin and destination details asynchronously
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        ResponseEntity<Location> originResponse = null;
        ResponseEntity<Location> destinationResponse = null;
        
        try {
            originResponse = executorService.submit(originDetails).get();
            destinationResponse = executorService.submit(destinationDetails).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }

        // Get the API response
        String apiUrlResponse = restTemplate.getForObject(fareResponse, String.class);

        // Combine all responses into a single string
        StringBuilder combinedResponse = new StringBuilder();
        combinedResponse.append("Origin Details: ").append(originResponse.getBody()).append("\n");
        combinedResponse.append("Destination Details: ").append(destinationResponse.getBody()).append("\n");
        combinedResponse.append("Fare details: ").append(apiUrlResponse);

        return combinedResponse.toString();
    }
    
    public Callable<ResponseEntity<Location>> callShow(String lang, String key) {
        return () -> {
            String apiUrl = airportApiUrl + key + "?lang=" + lang; // Replace with actual base URL
            Thread.sleep(ThreadLocalRandom.current().nextLong(200, 800));
            return restTemplate.getForObject(apiUrl, ResponseEntity.class);
        };
    }
}
