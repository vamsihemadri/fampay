package com.rivigo.zoom.datastore.controller.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hello")
public class HelloController {

  @GetMapping("/world")
  public String home() {
    return "Hello there. Spring Boot with Docker is what I am.";
  }
}
