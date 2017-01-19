package me.knox.zmz.entity;

import java.util.List;

/**
 * Created by KNOX.
 */

public class UpdateList {
  private List<Update> mUpdates;

  public UpdateList(List<Update> updates) {
    mUpdates = updates;
  }

  public List<Update> getList() {
    return mUpdates;
  }
}
