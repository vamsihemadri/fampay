package com.rivigo.zoom.datastore.constants;

import com.google.common.collect.ImmutableList;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class HeaderConstant {

  public static final String IMEI_NUMBER = "imeiNumber";
  public static final String MAC_ADDRESS = "macAddress";
  public static final String APP_NAME = "appName";
  public static final String APP_VERSION = "appVersion";
  public static final String X_USER_AGENT = "X-User-Agent";

  public static final List<String> CUSTOM_HEADERS =
      ImmutableList.of(X_USER_AGENT, IMEI_NUMBER, MAC_ADDRESS, APP_NAME, APP_VERSION);
}
