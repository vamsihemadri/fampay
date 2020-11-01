package com.rivigo.zoom.datastore.utils;

import com.google.api.client.util.DateTime;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DateTimeUtils {

  public static Long getMillisSinceEpochForRFCTime(String rfcTime) {
    DateTime dateTime = DateTime.parseRfc3339(rfcTime);
    return dateTime.getValue();
  }
}
