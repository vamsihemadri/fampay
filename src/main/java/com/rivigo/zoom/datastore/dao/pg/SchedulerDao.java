package com.rivigo.zoom.datastore.dao.pg;

import com.rivigo.zoom.datastore.model.Schedule;
import java.util.Optional;
import org.jdbi.v3.sqlobject.config.RegisterFieldMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

@RegisterFieldMapper(Schedule.class)
public interface SchedulerDao extends AbstractDao<Schedule> {

  @SqlQuery(
      "select " + "id, " + "name, " + "last_successful_run, " + "from schedules where name = :name")
  Optional<Schedule> findByName(@Bind("name") String name);

  @SqlQuery("" + "")
  @GetGeneratedKeys("id")
  Long add(Schedule schedule);

  @SqlQuery("")
  @GetGeneratedKeys("id")
  Long update(Schedule schedule);

  @SqlQuery("" + "")
  Optional<Schedule> findById(Long id);
}
