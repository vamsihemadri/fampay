package com.rivigo.zoom.datastore.dao.pg;

import com.rivigo.zoom.datastore.model.Video;
import java.util.Optional;
import org.jdbi.v3.sqlobject.config.RegisterFieldMapper;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

@RegisterFieldMapper(Video.class)
public interface VideoDao extends AbstractDao<Video> {

  @SqlQuery("" + "")
  @GetGeneratedKeys("id")
  Long add(Video video);

  @SqlQuery("")
  @GetGeneratedKeys("id")
  Long update(Video video);

  @SqlQuery("" + "")
  Optional<Video> findById(Long id);
}
