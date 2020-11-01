package com.rivigo.zoom.datastore.service.impl;

import com.rivigo.zoom.datastore.constants.YoutubeConstants;
import com.rivigo.zoom.datastore.facade.YoutubeFacade;
import com.rivigo.zoom.datastore.service.InitService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class InitServiceImpl implements InitService {

  @Autowired private ApplicationContext applicationContext;

  @Override
  public void initialiseApplication() {
    final YoutubeFacade youtubeFacade = applicationContext.getBean(YoutubeFacade.class);
    Runnable runnable =
        new Runnable() {
          public void run() {
            //youtubeFacade.hitYoutubeAndSaveLocally(YoutubeConstants.DEFAULT_QUERY);
            System.out.println(
                String.format("Hello i have hit youtube api at {}!!", System.currentTimeMillis()));
          }
        };
    ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
    service.scheduleAtFixedRate(runnable, 10, 15, TimeUnit.SECONDS);
  }
}
