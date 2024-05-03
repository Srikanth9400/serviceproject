package com.test.serviceproject.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.serviceproject.service.FaresAPIService;

@RestController
@RequestMapping("/fares")
public class FaresAPIController {

    private final FaresAPIService faresAPIService;

    @Autowired
    public FaresAPIController(FaresAPIService faresAPIService) {
        this.faresAPIService = faresAPIService;
    }

    @GetMapping("/{origin}/{destination}")
    public String getFares(@PathVariable String origin, @PathVariable String destination) {
        return faresAPIService.getFares(origin, destination);
    }
}
