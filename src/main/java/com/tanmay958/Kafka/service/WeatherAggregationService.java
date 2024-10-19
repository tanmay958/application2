package com.tanmay958.Kafka.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tanmay958.Kafka.model.WeatherData;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class WeatherAggregationService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final Map<String, WeatherStats> cityStats = new HashMap<>();

    public WeatherAggregationService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = new ObjectMapper();
    }

    @KafkaListener(topics = {"mumbai", "kolkata", "delhi","bengaluru","chennai"}, groupId = "weather_group")
    public void listenWeatherData(String weatherDataJson) {
        WeatherData weatherData = parseWeatherData(weatherDataJson);

        if (weatherData != null) {
            updateStatistics(weatherData);
            sendAggregatedData(weatherData);
            sendRawData(weatherData); // Send raw data as well
        }
    }

    private WeatherData parseWeatherData(String weatherDataJson) {
        try {
            return objectMapper.readValue(weatherDataJson, WeatherData.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void updateStatistics(WeatherData weatherData) {
        String city = weatherData.getCity();
        double temperature = weatherData.getTemperature();
        String weatherCondition = weatherData.getWeatherCondition();

        cityStats.putIfAbsent(city, new WeatherStats());
        WeatherStats stats = cityStats.get(city);

        stats.incrementCount();
        stats.addTemperature(temperature);

        if (temperature > stats.getMaxTemperature()) {
            stats.setMaxTemperature(temperature);
        }
        if (temperature < stats.getMinTemperature()) {
            stats.setMinTemperature(temperature);
        }
        stats.addWeatherCondition(weatherCondition);
    }

    private void sendAggregatedData(WeatherData weatherData) {
        String city = weatherData.getCity();
        WeatherStats stats = cityStats.get(city);
        if (stats != null) {
            AggregatedWeatherData aggregatedData = new AggregatedWeatherData(
                    city,
                    stats.getAverageTemperature(),
                    stats.getMaxTemperature(),
                    stats.getMinTemperature(),
                    stats.getDominantWeatherCondition()
            );
            kafkaTemplate.send("aggregate-" + city.toLowerCase(), aggregatedData.toString());
        }
    }

    private void sendRawData(WeatherData weatherData) {
        // Send the original weather data
        String city = weatherData.getCity();
        kafkaTemplate.send("aggregate-" + city.toLowerCase(), weatherData.toString());
    }
}
