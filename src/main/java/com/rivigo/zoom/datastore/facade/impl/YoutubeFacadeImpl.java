package com.rivigo.zoom.datastore.facade.impl;

import com.google.api.services.youtube.model.SearchResult;
import com.rivigo.zoom.datastore.constants.YoutubeConstants;
import com.rivigo.zoom.datastore.converter.YoutubeSearchResultToVideoModelConverter;
import com.rivigo.zoom.datastore.facade.YoutubeFacade;
import com.rivigo.zoom.datastore.model.Schedule;
import com.rivigo.zoom.datastore.model.Video;
import com.rivigo.zoom.datastore.service.ScheduleService;
import com.rivigo.zoom.datastore.service.VideoService;
import com.rivigo.zoom.datastore.service.YoutubeService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class YoutubeFacadeImpl implements YoutubeFacade {

  @Autowired private YoutubeService youtubeService;

  @Autowired
  private YoutubeSearchResultToVideoModelConverter youtubeSearchResultToVideoModelConverter;

  @Autowired private VideoService videoService;

  @Autowired private ScheduleService scheduleService;

  @Override
  public List<Video> hitYoutubeAndSaveLocally(String query) {
    String lastRun = getLastSuccessFullYoutubeSchedulerRun();
    List<SearchResult> searchResults = youtubeService.hitYoutubeAndGetResponse(query, lastRun);
    return videoService.saveAll(
        youtubeSearchResultToVideoModelConverter.convertYoutubSearchResultList(searchResults));
    // insert into schedule with this time;
  }

  private String getLastSuccessFullYoutubeSchedulerRun() {
    Schedule youtubeSchedule = null;
    try {
      youtubeSchedule = scheduleService.getScheduleByName(YoutubeConstants.YOUTUBE_SCHEDULER_NAME);
    } catch (RuntimeException e) {
      log.error("No schedule found for youtube scheduler.");
    }

    return youtubeSchedule == null
        ? YoutubeConstants.DEFAULT_PUBLISHED_AFTER
        : youtubeSchedule.getLastSuccessfulRunRFCTime();
  }
}
