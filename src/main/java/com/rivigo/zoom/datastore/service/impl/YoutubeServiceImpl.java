package com.rivigo.zoom.datastore.service.impl;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.rivigo.zoom.datastore.constants.YoutubeConstants;
import com.rivigo.zoom.datastore.service.YoutubeService;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class YoutubeServiceImpl implements YoutubeService {

  @Override
  public List<SearchResult> hitYoutubeAndGetResponse(String query) {
    // Define and execute the API request
    SearchListResponse response;
    try {
      YouTube youtubeService = getService();
      YouTube.Search.List request =
          youtubeService.search().list(YoutubeConstants.SNINPPET_PART_LIST);
      response =
          request
              .setKey(YoutubeConstants.getDeveloperKey())
              .setOrder(YoutubeConstants.DATE_KEY)
              .setPublishedAfter("2020-10-01T07:18:21+00:00")
              .setQ(query)
              .setType(YoutubeConstants.TYPE_VIDEO_LSIT)
              .execute();
    } catch (GeneralSecurityException | IOException i) {
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
    return new YouTube.Builder(httpTransport, YoutubeConstants.JSON_FACTORY, null)
        .setApplicationName(YoutubeConstants.getApplicationName())
        .build();
  }
}
