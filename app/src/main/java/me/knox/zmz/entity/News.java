package me.knox.zmz.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by KNOX.
 */

public class News {
  @SerializedName("id") private int id;
  @SerializedName("title") private String title;
  @SerializedName("type") private String type;
  @SerializedName("poster_b") private String poster; // poster_b,poster_m,poster_s
  @SerializedName("dateline") private long date;
  @SerializedName("intro") private String intro;
  @SerializedName("views") private String views;

  public int getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getType() {
    return type;
  }

  public String getPoster() {
    return poster;
  }

  public long getDate() {
    return date;
  }

  public String getIntro() {
    return intro;
  }

  public String getViews() {
    return views;
  }
}
