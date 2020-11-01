package com.rivigo.zoom.datastore.config;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ZoomDatastoreConfig {

  private static final int CORE_POOL_SIZE = 100;
  private static final int MAX_POOL_SIZE = 100;
  private static final int QUEUE_SIZE = 50;
  private static final int KEEP_ALIVE_TIME = 5000;

  @Value("${service.name}")
  private String serviceName;

  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper()
        .registerModule(
            new JavaTimeModule()
                .addSerializer(LocalDateTime.class, new CustomLocalDateTimeSerializer())
                .addDeserializer(LocalDateTime.class, new CustomLocalDateTimeDeSerializer()))
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .setSerializationInclusion(Include.NON_NULL);
  }

  @Bean
  public ExecutorService getExecutorService() {
    return new ThreadPoolExecutor(
        CORE_POOL_SIZE,
        MAX_POOL_SIZE,
        KEEP_ALIVE_TIME,
        TimeUnit.MILLISECONDS,
        new ArrayBlockingQueue<>(QUEUE_SIZE, true),
        new ThreadPoolExecutor.CallerRunsPolicy());
  }

  @Bean(name = "AddrCleanUpExecutor")
  public Executor asyncExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(3);
    executor.setMaxPoolSize(3);
    executor.setQueueCapacity(100);
    executor.setThreadNamePrefix("AddrCleanUpThread-");
    executor.initialize();
    return executor;
  }

  private static class CustomLocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers)
        throws IOException {
      gen.writeNumber(value.toInstant(ZoneOffset.UTC).toEpochMilli());
    }
  }

  private static class CustomLocalDateTimeDeSerializer extends JsonDeserializer<LocalDateTime> {

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      return LocalDateTime.ofInstant(Instant.ofEpochMilli(p.getValueAsLong()), ZoneOffset.UTC);
    }
  }
}
