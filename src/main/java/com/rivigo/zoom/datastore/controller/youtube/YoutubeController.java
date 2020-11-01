package com.rivigo.zoom.datastore.controller.youtube;

import com.rivigo.zoom.datastore.facade.YoutubeFacade;
import com.rivigo.zoom.datastore.model.Video;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** this is a controller too test the flows manually. */
@RestController
@RequestMapping("/youtube")
public class YoutubeController {

  @Autowired private YoutubeFacade youtubeFacade;

  @GetMapping("/hello")
  public String helloYoutube() {
    return "Hello. I will be your interface to Youtube API.";
  }

  /**
   * this hits the youtube endpoint and stores the response locally. this is added for the purpose
   * of testing.
   *
   * @param query the query with which the youtube api is hit.
   * @return list of local video entities created on the response of the youtube api.
   */
  @PostMapping("/videos")
  public List<Video> getVideos(@RequestParam(required = false) String query) {
    return youtubeFacade.hitYoutubeAndSaveLocally(query);
  }
}
