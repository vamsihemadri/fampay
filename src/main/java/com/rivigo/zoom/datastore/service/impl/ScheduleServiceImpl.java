package com.rivigo.zoom.datastore.service.impl;

import com.rivigo.zoom.datastore.dao.pg.SchedulerDao;
import com.rivigo.zoom.datastore.model.Schedule;
import com.rivigo.zoom.datastore.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl implements ScheduleService {

  @Autowired private SchedulerDao schedulerDao;

  @Override
  public Schedule getScheduleByName(String name) throws RuntimeException {
    return schedulerDao
        .findByName(name)
        .orElseThrow(
            () ->
                new RuntimeException((String.format("No schedule found with the name %s", name))));
  }
}
