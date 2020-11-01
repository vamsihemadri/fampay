package com.rivigo.zoom.datastore.converter;

import com.google.api.services.youtube.model.SearchResult;
import com.rivigo.zoom.datastore.model.Video;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class YoutubeSearchResultToVideoModelConverter {

  public Video convertYoutubeSearchResult(SearchResult searchResult) {
    Video video = new Video();
    return video;
  }

  public List<Video> convertYoutubSearchResultList(List<SearchResult> searchResults) {
    return searchResults
        .stream()
        .filter(Objects::nonNull)
        .map(this::convertYoutubeSearchResult)
        .collect(Collectors.toList());
  }
}
