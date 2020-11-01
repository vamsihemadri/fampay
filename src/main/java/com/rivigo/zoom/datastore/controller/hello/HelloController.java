package com.rivigo.zoom.datastore.controller.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hello")
public class HelloController {

  @GetMapping("/")
  public String home() {
    return "Hello Spring Boot with Docker";
  }
}
