package com.tanmay958.Kafka.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class WeatherProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public WeatherProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendWeatherData(String topic, String weatherData) {
        kafkaTemplate.send(topic, weatherData);
    }
}