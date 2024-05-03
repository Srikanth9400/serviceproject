package com.test.serviceproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.serviceproject.service.AirportAPIService;

@RestController
@RequestMapping("/airport")
public class AirportAPIController {

    private final AirportAPIService airportAPIService;

    @Autowired
    public AirportAPIController(AirportAPIService airportAPIService) {
        this.airportAPIService = airportAPIService;
    }

    @GetMapping
    public String getAirportData() {
        return airportAPIService.getAirportData();
    }
    
}
