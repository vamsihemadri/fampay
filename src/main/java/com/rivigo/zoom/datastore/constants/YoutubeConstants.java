package com.rivigo.zoom.datastore.constants;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import java.util.Arrays;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class YoutubeConstants {

  private static final String DEVELOPER_KEY = "AIzaSyBPGAp_bPaTqCL0Xwq06HrjMZb9j9Ka8QA";

  private static final String APPLICATION_NAME = "youtube-videos-294306";
  public static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

  public static final List<String> SNINPPET_PART_LIST = Arrays.asList("snippet");
  public static final List<String> TYPE_VIDEO_LSIT = Arrays.asList("video");

  public static final String DATE_KEY = "date";

  public static final String DEFAULT_QUERY = "epl highlights today";

  public String getDeveloperKey() {
    return DEVELOPER_KEY;
  }

  public String getApplicationName() {
    return APPLICATION_NAME;
  }
}
