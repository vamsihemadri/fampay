package com.rivigo.zoom.datastore.facade.impl;

import com.google.api.services.youtube.model.SearchResult;
import com.rivigo.zoom.datastore.constants.FamPayYoutubeConstants;
import com.rivigo.zoom.datastore.converter.YoutubeSearchResultToVideoModelConverter;
import com.rivigo.zoom.datastore.facade.YoutubeFacade;
import com.rivigo.zoom.datastore.model.Schedule;
import com.rivigo.zoom.datastore.model.Video;
import com.rivigo.zoom.datastore.service.ScheduleService;
import com.rivigo.zoom.datastore.service.VideoService;
import com.rivigo.zoom.datastore.service.YoutubeService;
import com.rivigo.zoom.datastore.utils.DateTimeUtils;
import java.util.ArrayList;
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
    Schedule youtubeSchedule = getYoutubeSchedule();
    List<Video> toReturn = new ArrayList<>();
    if (youtubeSchedule != null) {
      // hit youtube api and get video details
      List<SearchResult> searchResults =
          youtubeService.hitYoutubeAndGetResponse(
              query, youtubeSchedule.getLastSuccessfulRunRfcTime());
      log.info(String.format("The number of search results are %s", searchResults.size()));
      log.info(String.format("The search results are %s", searchResults.toString()));
      toReturn =
          videoService.saveAll(
              youtubeSearchResultToVideoModelConverter.convertYoutubSearchResultList(
                  searchResults));
      // update the youtube schedule.
      youtubeSchedule.setLastSuccessfulRunRfcTime(
          DateTimeUtils.getRFCTImeForEpochMillis(System.currentTimeMillis()));
      scheduleService.save(youtubeSchedule);
    }
    return toReturn;
  }

  private Schedule getYoutubeSchedule() {
    Schedule youtubeSchedule = null;
    try {
      youtubeSchedule =
          scheduleService.getScheduleByName(FamPayYoutubeConstants.YOUTUBE_SCHEDULER_NAME);
    } catch (RuntimeException e) {
      log.error("No schedule found for youtube scheduler.");
    }
    return youtubeSchedule;
  }
}
