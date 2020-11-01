package com.rivigo.zoom.datastore.service.impl;

import com.rivigo.zoom.datastore.dao.pg.VideoDao;
import com.rivigo.zoom.datastore.model.Video;
import com.rivigo.zoom.datastore.service.VideoService;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoServiceImpl implements VideoService {

  @Autowired private Jdbi readDbiBean;

  @Autowired private Jdbi writeDbiBean;

  @Override
  public List<Video> saveAll(List<Video> videos) {
    try (Handle handle = writeDbiBean.open()) {
      VideoDao videoDao = handle.attach(VideoDao.class);
      return videoDao.saveAll(
          videos.stream().filter(Objects::nonNull).collect(Collectors.toList()));
    }
  }
}
