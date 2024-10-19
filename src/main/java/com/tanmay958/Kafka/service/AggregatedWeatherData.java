package com.tanmay958.Kafka.service;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AggregatedWeatherData {
    private String city;
    private double averageTemperature;
    private double maxTemperature;
    private double minTemperature;
    private String dominantWeatherCondition;

    @Override
    public String toString() {
        return String.format("City: %s, Avg Temp: %.2f, Max Temp: %.2f, Min Temp: %.2f, Dominant Condition: %s",
                city, averageTemperature, maxTemperature, minTemperature, dominantWeatherCondition);
    }
}
