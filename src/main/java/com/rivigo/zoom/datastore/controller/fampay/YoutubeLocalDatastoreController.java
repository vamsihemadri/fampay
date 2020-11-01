package com.rivigo.zoom.datastore.controller.fampay;

import com.rivigo.zoom.datastore.model.Video;
import com.rivigo.zoom.datastore.service.VideoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fampay")
public class YoutubeLocalDatastoreController {

  @Autowired private VideoService videoService;

  public List<Video> getVideoData(
      @RequestParam(required = false) Integer pageNumber,
      @RequestParam(required = false) Integer pageSize) {
    return videoService.getVideoDetails(pageNumber, pageSize);
  }
}
