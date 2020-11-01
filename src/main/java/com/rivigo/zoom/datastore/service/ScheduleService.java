package com.rivigo.zoom.datastore.service;

import com.rivigo.zoom.datastore.model.Schedule;

public interface ScheduleService {
  Schedule getScheduleByName(String name) throws RuntimeException;

  Long save(Schedule schedule);
}
