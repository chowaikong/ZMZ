package me.knox.zmz.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by KNOX.
 */

public class Search {

  @SerializedName("itemid") private int id;
  @SerializedName("title") private String title;
  @SerializedName("type") private String type;
  @SerializedName("channel") private String channel;
  @SerializedName("pubtime") private long pubilshTime;
  @SerializedName("uptime") private long updateTime;

  public int getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getType() {
    return type;
  }

  public String getChannel() {
    return channel;
  }

  public long getPubilshTime() {
    return pubilshTime;
  }

  public long getUpdateTime() {
    return updateTime;
  }
}
