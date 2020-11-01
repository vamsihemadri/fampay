package com.rivigo.zoom.datastore;

import com.rivigo.zoom.datastore.config.WebConfig;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@Slf4j
@SpringBootApplication(scanBasePackages = "com.rivigo.zoom.datastore")
public class Application {

  public static void main(String[] args) {
    Application application = new Application();
    application.run(args);
  }

  private void run(String[] args) {
    ApplicationContext ctx =
        SpringApplication.run(new Class[] {Application.class, WebConfig.class}, args);

    String[] beanNames = ctx.getBeanDefinitionNames();
    Arrays.sort(beanNames);
    for (String beanName : beanNames) {
      log.debug("Beans initialized by spring boot {}", beanName);
    }
    Runnable runnable =
        new Runnable() {
          public void run() {
            System.out.println("Hello !!");
          }
        };
    ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
    service.scheduleAtFixedRate(runnable, 10, 10, TimeUnit.SECONDS);
  }
}
