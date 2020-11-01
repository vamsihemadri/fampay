package com.rivigo.zoom.datastore.config;

import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class WriteDataSourceConfig {

  @Primary
  @Bean
  @ConfigurationProperties(prefix = "spring.datasource")
  public DataSource writeDataSource() {
    return DataSourceBuilder.create().build();
  }
}
