package me.knox.zmz.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by KNOX.
 */

public class ScheduleUpdate {

  @SerializedName("id") private int id;
  @SerializedName("cnname") private String cnname;
  @SerializedName("enname") private String enname;
  @SerializedName("poster") private String poster;
  @SerializedName("poster_b") private String posterB;  // posterB, poster_a, poster_b, poster_m,poster_s
  @SerializedName("season") private String season;
  @SerializedName("episode") private String episode;

  public int getId() {
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

  public String getPosterB() {
    return posterB;
  }

  public String getSeason() {
    if (Math.log10(Double.valueOf(season)) + 1 < 2) {
      return "0" + season;
    }
    return season;
  }

  public String getEpisode() {
    if (Math.log10(Double.valueOf(episode)) + 1 < 2) {
      return "0" + episode;
    }
    return episode;
  }
}
