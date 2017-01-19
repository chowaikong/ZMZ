package me.knox.zmz.entity;

import java.util.List;

/**
 * Created by KNOX.
 */

public class ResourceList {
  private List<Resource.Data> mDataList;

  public ResourceList(List<Resource.Data> dataList) {
    mDataList = dataList;
  }

  public List<Resource.Data> getList() {
    return mDataList;
  }
}
