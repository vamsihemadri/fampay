package com.rivigo.zoom.datastore.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.services.youtube.model.SearchResult;
import com.rivigo.zoom.datastore.model.Video;
import com.rivigo.zoom.datastore.utils.DateTimeUtils;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class YoutubeSearchResultToVideoModelConverter {

  @Autowired private ObjectMapper objectMapper;

  /* param searchresult
  and searchresult.getSnippet()
  and searchResult.getId() are guaranteed to be non null.
   */
  public Video convertYoutubeSearchResult(SearchResult searchResult) {
    Video video = new Video();
    video.setTitle(searchResult.getSnippet().getTitle());
    video.setDescription(searchResult.getSnippet().getDescription());
    video.setPublishedAt(
        DateTimeUtils.getMillisSinceEpochForRFCTime(searchResult.getSnippet().getPublishedAt()));
    video.setYoutubeVideoId(searchResult.getId().getVideoId());
    video.setChannelName(searchResult.getSnippet().getChannelTitle());
    video.setYoutubeChannelId(searchResult.getSnippet().getChannelId());
    try {
      video.setThumbNailsMetadata(
          objectMapper.writeValueAsString(searchResult.getSnippet().getThumbnails()));
    } catch (JsonProcessingException j) {
      log.error(
          "Couldn't get json of the thumbnails from the search result for the searchresult:{}",
          searchResult.toString());
    }
    return video;
  }

  public List<Video> convertYoutubSearchResultList(List<SearchResult> searchResults) {
    return searchResults
        .stream()
        .filter(Objects::nonNull)
        .filter(v -> v.getSnippet() != null)
        .filter(v -> v.getId() != null)
        .map(this::convertYoutubeSearchResult)
        .collect(Collectors.toList());
  }
}
