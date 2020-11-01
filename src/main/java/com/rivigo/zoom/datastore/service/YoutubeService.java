package com.rivigo.zoom.datastore.service;

import com.google.api.services.youtube.model.SearchResult;
import java.util.List;

public interface YoutubeService {

  List<SearchResult> hitYoutubeAndGetResponse(String query, String lastRunDateTime);
}
