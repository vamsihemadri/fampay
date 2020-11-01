package com.rivigo.zoom.datastore.service.impl;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.rivigo.zoom.datastore.constants.FamPayYoutubeConstants;
import com.rivigo.zoom.datastore.service.YoutubeService;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class YoutubeServiceImpl implements YoutubeService {

  @Override
  public List<SearchResult> hitYoutubeAndGetResponse(String query, String lastRunDateTime) {
    // Define and execute the API request
    SearchListResponse response;
    try {
      YouTube youtubeService = getService();
      YouTube.Search.List request =
          youtubeService.search().list(FamPayYoutubeConstants.SNINPPET_PART_LIST);
      response =
          request
              .setKey(FamPayYoutubeConstants.getDeveloperKey())
              .setOrder(FamPayYoutubeConstants.DATE_KEY)
              .setPublishedAfter(
                  lastRunDateTime == null
                      ? FamPayYoutubeConstants.DEFAULT_PUBLISHED_AFTER
                      : lastRunDateTime)
              .setQ(query == null ? FamPayYoutubeConstants.DEFAULT_QUERY : query)
              .setType(FamPayYoutubeConstants.TYPE_VIDEO_LSIT)
              .setMaxResults(FamPayYoutubeConstants.DEFAULT_MAX_RESULTS)
              .execute();
      log.info("The response from youtube api is {}", response.toString());
    } catch (GeneralSecurityException | IOException i) {
      log.error("There occurred an exception : {}", (Object) i.getStackTrace());
      return Collections.emptyList();
    }

    return response != null ? response.getItems() : Collections.emptyList();
  }

  /**
   * Build and return an authorized API client service.
   *
   * @return an authorized API client service
   * @throws GeneralSecurityException, IOException
   */
  public static YouTube getService() throws GeneralSecurityException, IOException {
    final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
    return new YouTube.Builder(httpTransport, FamPayYoutubeConstants.JSON_FACTORY, null)
        .setApplicationName(FamPayYoutubeConstants.getApplicationName())
        .build();
  }
}
