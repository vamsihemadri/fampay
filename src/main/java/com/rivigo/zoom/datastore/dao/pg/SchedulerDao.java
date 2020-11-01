package com.rivigo.zoom.datastore.dao.pg;

import com.rivigo.zoom.datastore.model.Schedule;
import java.util.Optional;
import org.jdbi.v3.sqlobject.config.RegisterFieldMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

@RegisterFieldMapper(Schedule.class)
public interface SchedulerDao extends AbstractDao<Schedule> {

  @SqlQuery(
      "select "
          + "id, "
          + "name, "
          + "last_successful_run_rfc_time "
          + "from schedules where name = :name")
  Optional<Schedule> findByName(@Bind("name") String name);

  @SqlUpdate(
      ""
          + "INSERT INTO schedules (name, "
          + "last_successful_run_rfc_time) "
          + "VALUES ( "
          + ":name, "
          + ":lastSuccessfulRunRfcTime )"
          + "")
  @GetGeneratedKeys("id")
  Long add(@BindBean Schedule schedule);

  @SqlUpdate(
      "UPDATE "
          + "schedules SET "
          + "name = :name, "
          + "last_successful_run_rfc_time = :lastSuccessfulRunRfcTime "
          + "WHERE id = :id ")
  @GetGeneratedKeys("id")
  Long update(@BindBean Schedule schedule);

  @SqlQuery(
      "select id, "
          + "name, "
          + "last_successful_run_rfc_time "
          + "from schedules "
          + "where "
          + "id = :id ")
  Optional<Schedule> findById(@Bind("id") Long id);
}
