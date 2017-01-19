package me.knox.zmz.entity;

import android.support.annotation.NonNull;
import java.util.List;

/**
 * Created by KNOX.
 */

public class HotList {

  private List<Hot> mHotList;

  public HotList(@NonNull List<Hot> hotList) {
    mHotList = hotList;
  }

  public List<Hot> getList() {
    return mHotList;
  }
}
