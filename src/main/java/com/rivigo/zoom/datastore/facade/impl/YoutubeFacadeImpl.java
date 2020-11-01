package com.rivigo.zoom.datastore.facade.impl;

import com.google.api.services.youtube.model.SearchResult;
import com.rivigo.zoom.datastore.converter.YoutubeSearchResultToVideoModelConverter;
import com.rivigo.zoom.datastore.facade.YoutubeFacade;
import com.rivigo.zoom.datastore.model.Video;
import com.rivigo.zoom.datastore.service.VideoService;
import com.rivigo.zoom.datastore.service.YoutubeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class YoutubeFacadeImpl implements YoutubeFacade {

  @Autowired private YoutubeService youtubeService;

  @Autowired
  private YoutubeSearchResultToVideoModelConverter youtubeSearchResultToVideoModelConverter;

  @Autowired private VideoService videoService;

  @Override
  public List<Video> hitYoutubeAndSaveLocally(String query) {
    String lastRun = null;
    List<SearchResult> searchResults = youtubeService.hitYoutubeAndGetResponse(query, lastRun);
    return videoService.saveAll(
        youtubeSearchResultToVideoModelConverter.convertYoutubSearchResultList(searchResults));
  }
}