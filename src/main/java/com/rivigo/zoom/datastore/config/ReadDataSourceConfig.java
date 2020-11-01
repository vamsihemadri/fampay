package com.rivigo.zoom.datastore.config;

import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReadDataSourceConfig {

  @Bean
  @ConfigurationProperties(prefix = "read.spring.datasource")
  public DataSource readDataSource() {
    return DataSourceBuilder.create().build();
  }
}
