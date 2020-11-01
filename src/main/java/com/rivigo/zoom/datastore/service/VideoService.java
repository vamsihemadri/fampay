package com.rivigo.zoom.datastore.service;

import com.rivigo.zoom.datastore.model.Video;
import java.util.List;

public interface VideoService {

  List<Video> saveAll(List<Video> videos);

  List<Video> getVideoDetails(Integer pageNumber, Integer pageSize);

  List<Video> searchVideoByTitleOrDescription(String query);
}
