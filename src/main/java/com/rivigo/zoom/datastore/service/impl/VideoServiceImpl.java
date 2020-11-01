package com.rivigo.zoom.datastore.service.impl;

import com.rivigo.zoom.datastore.dao.pg.VideoDao;
import com.rivigo.zoom.datastore.model.Video;
import com.rivigo.zoom.datastore.service.VideoService;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoServiceImpl implements VideoService {

  @Autowired private VideoDao videoDao;

  @Override
  public List<Video> saveAll(List<Video> videos) {
    return videoDao.saveAll(videos.stream().filter(Objects::nonNull).collect(Collectors.toList()));
  }
}
