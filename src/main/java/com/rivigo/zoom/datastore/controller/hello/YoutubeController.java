package com.rivigo.zoom.datastore.controller.hello;

import com.google.api.services.youtube.model.SearchResult;
import com.rivigo.zoom.datastore.service.YoutubeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/youtube")
public class YoutubeController {

  @Autowired private YoutubeService youtubeService;

  @GetMapping("/hello")
  public String helloYoutube() {
    return "Hello. I will be yout intergace to youtube.";
  }

  @GetMapping("/videos")
  public List<SearchResult> getVideos(@RequestParam String query) {
    return youtubeService.hitYoutubeAndGetResponse(query);
  }
}
