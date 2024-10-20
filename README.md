﻿# Application2

Realtime Aggreagte and Rollups of weather data of specific location

## Installation

```bash
git clone  https://github.com/tanmay958/application01
cd  application02
```

## Tech Stack

Spring boot, Kafka

## Explanation

In a weather data processing system, real-time aggregation is essential for providing timely insights into metrics like temperature and rainfall. Scheduled cron jobs periodically fetch weather data from APIs or IoT devices and send the information to city-specific Kafka topics, such as weather-delhi. Kafka ensures reliable transmission of this data, while a stream processing application utilizes the Kafka Streams API to perform real-time aggregations, calculating averages and other statistics. The aggregated results are then published to an aggregate-[city] topic, enabling the frontend application to visualize trends and current conditions in real time. This architecture effectively delivers scalable, dynamic weather insights, enhancing user experience through timely visualizations.

## Usage

```docker
$ docker pull apache/kafka:3.8.0
$ docker run -p 9092:9092 apache/kafka:3.8.0
download .jar from https://drive.google.com/file/d/1lyAnoiF2K2vWsnwbyUWXtBNkUKew60wM/view?usp=sharing
go to the download folder (where its located)
java -jar  [file_name]
##
```

## Architecture

![Arch ](./Assets/architecture.png)

## Advantages

1. This makes the app highly scalable and fault tolerence

### 1. Cities realtime weather

![Cities ](./Assets/cities.png)

### 2. Aggregates

![Aggreagtes](./Assets/aggregate.png)

Made with 💖 Tanmay
