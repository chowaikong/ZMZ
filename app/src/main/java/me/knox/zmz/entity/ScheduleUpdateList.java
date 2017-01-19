package me.knox.zmz.entity;

import java.util.List;

/**
 * Created by KNOX.
 */

public class ScheduleUpdateList {

  private List<ScheduleUpdate> mScheduleUpdates;

  public ScheduleUpdateList(List<ScheduleUpdate> scheduleUpdates) {
    mScheduleUpdates = scheduleUpdates;
  }

  public List<ScheduleUpdate> getList() {
    return mScheduleUpdates;
  }
}
