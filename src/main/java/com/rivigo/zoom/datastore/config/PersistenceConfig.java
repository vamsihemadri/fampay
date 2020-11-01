package com.rivigo.zoom.datastore.config;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import javax.sql.DataSource;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.argument.AbstractArgumentFactory;
import org.jdbi.v3.core.argument.Argument;
import org.jdbi.v3.core.argument.ArgumentFactory;
import org.jdbi.v3.core.config.ConfigRegistry;
import org.jdbi.v3.core.mapper.ColumnMapper;
import org.jdbi.v3.core.statement.StatementContext;
import org.jdbi.v3.postgres.PostgresPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan(basePackages = {"com.rivigo.zoom.datastore.config"})
public class PersistenceConfig {

  private DataSource readDataSource;

  private DataSource writeDataSource;

  @Autowired
  public PersistenceConfig(DataSource readDataSource, DataSource writeDataSource) {
    this.readDataSource = readDataSource;
    this.writeDataSource = writeDataSource;
  }

  @Bean
  public Jdbi writeDbiBean() {
    Jdbi dbi = Jdbi.create(writeDataSource);
    return getDbi(dbi);
  }

  @Bean
  public Jdbi readDbiBean() {
    Jdbi dbi = Jdbi.create(readDataSource);
    return getDbi(dbi);
  }

  private Jdbi getDbi(Jdbi dbi) {
    dbi.installPlugin(new PostgresPlugin());
    dbi.installPlugin(new SqlObjectPlugin());

    // Request argument factories
    dbi.registerArgument(new LocalDateTimeArgumentFactory(Types.BIGINT));

    // Result mappers
    dbi.registerColumnMapper(new LocalDateTimeMapper());
    return dbi;
  }

  /** DBI argument factory for converting LocalDateTime to sql timestamp */
  public static class LocalDateTimeArgumentFactory extends AbstractArgumentFactory<LocalDateTime> {

    /**
     * Constructs an {@link ArgumentFactory} for type {@code T}.
     *
     * @param sqlType the {@link Types} constant to use when the argument value is {@code null}.
     */
    protected LocalDateTimeArgumentFactory(int sqlType) {
      super(sqlType);
    }

    @Override
    protected Argument build(LocalDateTime value, ConfigRegistry config) {
      return (position, statement, ctx1) ->
          statement.setLong(position, value.toInstant(ZoneOffset.UTC).toEpochMilli());
    }
  }

  /** A {@link ColumnMapper} to map {@link LocalDateTime} objects. */
  public static class LocalDateTimeMapper implements ColumnMapper<LocalDateTime> {

    @Override
    public LocalDateTime map(ResultSet r, int columnNumber, StatementContext ctx)
        throws SQLException {
      final Long timestamp = r.getLong(columnNumber);
      return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneOffset.UTC);
    }
  }
}
