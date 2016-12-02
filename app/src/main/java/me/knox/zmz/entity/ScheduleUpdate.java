package me.knox.zmz.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by KNOX.
 */

public class ScheduleUpdate {

  @SerializedName("id") private String id;
  @SerializedName("cnname") private String cnname;
  @SerializedName("enname") private String enname;
  @SerializedName("poster") private String poster;
  @SerializedName("season") private String season;
  @SerializedName("episode") private String episode;

  public String getId() {
    return id;
  }

  public String getCnname() {
    return cnname;
  }

  public String getEnname() {
    return enname;
  }

  public String getPoster() {
    return poster;
  }

  public String getSeason() {
    return season;
  }

  public String getEpisode() {
    return episode;
  }
}
