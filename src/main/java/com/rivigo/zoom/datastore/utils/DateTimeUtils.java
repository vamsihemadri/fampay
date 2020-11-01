package com.rivigo.zoom.datastore.utils;

import com.google.api.client.util.DateTime;
import com.rivigo.zoom.datastore.constants.YoutubeConstants;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DateTimeUtils {

  public static Long getMillisSinceEpochForRFCTime(String rfcTime) {
    DateTime dateTime = DateTime.parseRfc3339(rfcTime);
    return dateTime.getValue();
  }

  public static String getRFCTImeForEpochMillis(Long millisSinceEpoch) {
    DateTimeFormatter formatter =
        DateTimeFormatter.ofPattern(YoutubeConstants.YOUTUBE_RFC_DATE_TIME_FORMAT);
    Instant instant = Instant.ofEpochMilli(millisSinceEpoch);
    ZonedDateTime zdt = instant.atZone(ZoneId.of("UTC"));
    return zdt.format((formatter));
  }
}
