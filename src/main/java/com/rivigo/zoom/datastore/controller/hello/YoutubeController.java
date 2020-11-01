package com.rivigo.zoom.datastore.controller.hello;

import com.rivigo.zoom.datastore.facade.YoutubeFacade;
import com.rivigo.zoom.datastore.model.Video;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/youtube")
public class YoutubeController {

  @Autowired private YoutubeFacade youtubeFacade;

  @GetMapping("/hello")
  public String helloYoutube() {
    return "Hello. I will be your interface to Youtube API.";
  }

  @GetMapping("/videos")
  public List<Video> getVideos(@RequestParam(required = false) String query) {
    return youtubeFacade.hitYoutubeAndSaveLocally(query);
  }
}
