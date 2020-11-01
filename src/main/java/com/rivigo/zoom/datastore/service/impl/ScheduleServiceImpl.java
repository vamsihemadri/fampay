package com.rivigo.zoom.datastore.service.impl;

import com.rivigo.zoom.datastore.dao.pg.SchedulerDao;
import com.rivigo.zoom.datastore.model.Schedule;
import com.rivigo.zoom.datastore.service.ScheduleService;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl implements ScheduleService {

  @Autowired private Jdbi readDbiBean;

  @Override
  public Schedule getScheduleByName(String name) throws RuntimeException {
    try (Handle handle = readDbiBean.open()) {
      SchedulerDao schedulerDao = handle.attach(SchedulerDao.class);
      return schedulerDao
          .findByName(name)
          .orElseThrow(
              () ->
                  new RuntimeException(
                      (String.format("No schedule found with the name %s", name))));
    }
  }
}
