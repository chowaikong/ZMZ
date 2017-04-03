package me.knox.zmz.entity;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by KNOX.
 */

public class SubtitleList {

  @SerializedName("count") private int count;
  @SerializedName("list") private List<Subtitle> mSubtitleList;

  public List<Subtitle> getSubtitleList() {
    return mSubtitleList;
  }
}
