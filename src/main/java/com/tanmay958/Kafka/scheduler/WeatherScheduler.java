package com.tanmay958.Kafka.scheduler;
import com.tanmay958.Kafka.producer.WeatherProducer ;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherScheduler {
    private final WeatherProducer weatherProducer;
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String API_KEY = "c1f59eba71e6268b68f7c806a7d6514b";

    public WeatherScheduler(WeatherProducer weatherProducer) {
        this.weatherProducer = weatherProducer;
    }

    @Scheduled(fixedRate = 15000)
    public void pollWeatherData() {
        fetchAndSendWeather("Mumbai");
        fetchAndSendWeather("Delhi");
        fetchAndSendWeather("Kolkata");
        fetchAndSendWeather("Chennai");
        fetchAndSendWeather("Bengaluru");

    }

    private void fetchAndSendWeather(String city) {
        String url = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s", city, API_KEY);
        String weatherData = restTemplate.getForObject(url, String.class);
        if (weatherData != null) {
            weatherProducer.sendWeatherData(city.toLowerCase(), weatherData);
        }
    }
}
