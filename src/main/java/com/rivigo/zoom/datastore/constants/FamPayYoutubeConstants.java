package com.rivigo.zoom.datastore.constants;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import java.util.Arrays;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FamPayYoutubeConstants {

  private static final String DEVELOPER_KEY = "AIzaSyBBnbjp5PbBeJsRfH02LhnZ482qcquvztk";

  private static final String APPLICATION_NAME = "youtube-api-second-294317";

  public static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

  public static final List<String> SNINPPET_PART_LIST = Arrays.asList("snippet");

  public static final List<String> TYPE_VIDEO_LSIT = Arrays.asList("video");

  public static final String DATE_KEY = "date";

  public static final String DEFAULT_QUERY = "epl highlights today";

  public static final Long DEFAULT_MAX_RESULTS = 25L;

  public static final String DEFAULT_PUBLISHED_AFTER = "2020-11-01T07:18:21+00:00";

  public static final String YOUTUBE_RFC_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

  public static final String YOUTUBE_SCHEDULER_NAME = "youtube_video_schedule";

  public static final Integer DEFAULT_PAGE_NUMBER = 0;

  public static final Integer DEFAULT_PAGE_SIZE = 10;

  public String getDeveloperKey() {
    return DEVELOPER_KEY;
  }

  public String getApplicationName() {
    return APPLICATION_NAME;
  }
}
