package com.weathermonitoring.realtimedata.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weathermonitoring.realtimedata.model.WeatherData;

// Weather Data Repository to store weather related updates in real time
public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {
	
	List<WeatherData> findAllByTimestampAfter(LocalDateTime timestamp);
}
