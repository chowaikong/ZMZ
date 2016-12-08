package me.knox.zmz.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by KNOX.
 */

public class ScheduleUpdate {

  @SerializedName("id") private String id;
  @SerializedName("cnname") private String cnname;
  @SerializedName("enname") private String enname;
  @SerializedName("poster_b") private String poster;  // poster, poster_a, poster_b, poster_m,poster_s
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
