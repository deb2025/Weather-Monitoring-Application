package com.weathermonitoring.realtimedata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weathermonitoring.realtimedata.model.WeatherData;
import com.weathermonitoring.realtimedata.service.WeatherService;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    // API endpoint to check the weather of a particular city
    @GetMapping("/{city}") // Expecting a path variable named 'city'
    public WeatherData getCurrentWeather(@PathVariable String city) {
        return weatherService.fetchWeather(city);
    }
}
