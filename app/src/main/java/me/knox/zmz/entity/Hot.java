package me.knox.zmz.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by KNOX.
 */

public class Hot {
  @SerializedName("id") private String id;
  @SerializedName("cnname") private String cnname;
  @SerializedName("channel") private String channel;
  @SerializedName("area") private String area;
  @SerializedName("category") private String category;
  @SerializedName("publish_year") private String publish_year;
  @SerializedName("play_status") private String play_status;

  public String getId() {
    return id;
  }

  public String getCnname() {
    return cnname;
  }

  public String getChannel() {
    return channel;
  }

  public String getArea() {
    return area;
  }

  public String getCategory() {
    return category;
  }

  public String getPublish_year() {
    return publish_year;
  }

  public String getPlay_status() {
    return play_status;
  }
}
