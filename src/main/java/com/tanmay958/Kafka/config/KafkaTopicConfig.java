package com.tanmay958.Kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic mumbaiTopic() {
        return new NewTopic("mumbai", 1, (short) 1);
    }

    @Bean
    public NewTopic delhiTopic() {
        return new NewTopic("delhi", 1, (short) 1);
    }

    @Bean
    public NewTopic kolkataTopic() {
        return new NewTopic("kolkata", 1, (short) 1);
    }
    @Bean
    public NewTopic chennaiTopic() {
        return new NewTopic("chennai", 1, (short) 1);
    }
    @Bean
    public NewTopic bengaluruTopic() {
        return new NewTopic("bengaluru", 1, (short) 1);
    }
}
