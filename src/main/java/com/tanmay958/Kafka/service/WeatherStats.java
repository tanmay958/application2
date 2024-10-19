package com.tanmay958.Kafka.service;

import java.util.HashMap;
import java.util.Map;

public class WeatherStats {
    private int count;
    private double totalTemperature;
    private double maxTemperature = Double.MIN_VALUE;
    private double minTemperature = Double.MAX_VALUE;
    private Map<String, Integer> weatherConditionCount = new HashMap<>();

    public void incrementCount() {
        count++;
    }

    public void addTemperature(double temperature) {
        totalTemperature += temperature;
    }

    public void addWeatherCondition(String condition) {
        weatherConditionCount.put(condition, weatherConditionCount.getOrDefault(condition, 0) + 1);
    }

    public double getAverageTemperature() {
        return totalTemperature / count;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public String getDominantWeatherCondition() {
        return weatherConditionCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Unknown");
    }
}
