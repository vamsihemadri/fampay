package com.rivigo.zoom.datastore.dao.pg;

import com.rivigo.zoom.datastore.model.Video;
import org.jdbi.v3.sqlobject.config.RegisterFieldMapper;

@RegisterFieldMapper(Video.class)
public interface VideoDao extends AbstractDao<Video> {}
