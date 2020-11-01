package com.rivigo.zoom.datastore.facade;

import com.rivigo.zoom.datastore.model.Video;
import java.util.List;

public interface YoutubeFacade {

  List<Video> hitYoutubeAndSaveLocally(String query);
}
