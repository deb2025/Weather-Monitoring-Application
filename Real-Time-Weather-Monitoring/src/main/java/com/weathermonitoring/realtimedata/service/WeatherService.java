package com.weathermonitoring.realtimedata.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.weathermonitoring.realtimedata.model.DailySummary;
import com.weathermonitoring.realtimedata.model.WeatherData;
import com.weathermonitoring.realtimedata.model.WeatherResponse;
import com.weathermonitoring.realtimedata.repository.DailySummaryRepository;
import com.weathermonitoring.realtimedata.repository.WeatherDataRepository;



// Service class to represent the business logic behind fetching weather data
@Service
public class WeatherService {

    @Autowired
    private WeatherDataRepository weatherDataRepo;

    @Autowired
    private DailySummaryRepository dailySummaryRepo;

    @Autowired
    private RestTemplate restTemplate;

    public WeatherData fetchWeather(String city) {
    	String apiKey = "b7fe6b10dbb9e21d4b9c0c247520fe63";
    	String url = String.format("http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s", city, apiKey);
        
        WeatherResponse response = restTemplate.getForObject(url, WeatherResponse.class);
        
        if (response != null) {
            double tempCelsius = response.getMain().getTemp() - 273.15; // Convert Kelvin to Celsius
            
            WeatherData data = new WeatherData();
            data.setCity(city);
            data.setTemperature(tempCelsius);
            data.setFeelsLike(response.getMain().getFeelsLike() - 273.15);
            data.setCondition(response.getWeather().get(0).getMain());
            data.setTimestamp(LocalDateTime.now());
            
            weatherDataRepo.save(data);
            return data;
        }
        
        return null; // Handle error appropriately in production code.
    }

    public void summarizeDaily() {
        List<WeatherData> todayWeatherData = weatherDataRepo.findAllByTimestampAfter(LocalDateTime.now().minusDays(1));
        
        if (todayWeatherData.isEmpty()) {
            return; // No data to summarize
        }

        String city = todayWeatherData.get(0).getCity();
        double totalTemp = 0.0;
        double maxTemp = Double.MIN_VALUE;
        double minTemp = Double.MAX_VALUE;
        int count = todayWeatherData.size();
        
        // To track dominant weather condition
        int clearCount = 0, rainCount = 0, snowCount = 0, otherCount = 0;

        for (WeatherData weather : todayWeatherData) {
            totalTemp += weather.getTemperature();
            maxTemp = Math.max(maxTemp, weather.getTemperature());
            minTemp = Math.min(minTemp, weather.getTemperature());

            switch (weather.getCondition()) {
                case "Clear":
                    clearCount++;
                    break;
                case "Rain":
                    rainCount++;
                    break;
                case "Snow":
                    snowCount++;
                    break;
                default:
                    otherCount++;
                    break;
            }
        }

        double avgTemp = totalTemp / count;

        // Determine dominant condition based on counts
        String dominantCondition = "Other";
        if (clearCount > Math.max(rainCount, Math.max(snowCount, otherCount))) {
            dominantCondition = "Clear";
        } else if (rainCount > Math.max(clearCount, Math.max(snowCount, otherCount))) {
            dominantCondition = "Rain";
        } else if (snowCount > Math.max(clearCount, Math.max(rainCount, otherCount))) {
            dominantCondition = "Snow";
        }

        DailySummary summary = new DailySummary();
        summary.setCity(city);
        summary.setAvgTemp(avgTemp);
        summary.setMaxTemp(maxTemp);
        summary.setMinTemp(minTemp);
        summary.setDominantCondition(dominantCondition);
        summary.setDate(LocalDate.now());

        dailySummaryRepo.save(summary);
    }
}