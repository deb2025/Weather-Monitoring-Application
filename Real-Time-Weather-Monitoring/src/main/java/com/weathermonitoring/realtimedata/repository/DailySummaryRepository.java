package com.weathermonitoring.realtimedata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weathermonitoring.realtimedata.model.DailySummary;

// Repository to store daily weather updates
public interface DailySummaryRepository extends JpaRepository<DailySummary, Long> {
}
