package com.weathermonitoring.realtimedata.model;

import java.util.List;

// Weather Response class to provide response data to users
public class WeatherResponse {

    private Main main;
    private List<Weather> weather;
    private long dt; // Unix timestamp

    // Getters and Setters
    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    // Inner classes for Main and Weather
    public static class Main {
        private double temp;
        private double feels_like;

        // Getters and Setters
        public double getTemp() {
            return temp;
        }

        public void setTemp(double temp) {
            this.temp = temp;
        }

        public double getFeelsLike() {
            return feels_like;
        }

        public void setFeelsLike(double feels_like) {
            this.feels_like = feels_like;
        }
    }

    public static class Weather {
        private String main;

        // Getters and Setters
        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }
    }
}
