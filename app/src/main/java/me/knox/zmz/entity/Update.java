package me.knox.zmz.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by KNOX.
 */

public class Update {

  @SerializedName("id") private int id;
  @SerializedName("resourceid") private int resourceId;
  @SerializedName("name") private String name;
  @SerializedName("format") private String format;
  @SerializedName("season") private String season;
  @SerializedName("episode") private String episode;
  @SerializedName("size") private String size;
  @SerializedName("cnname") private String cnname;
  @SerializedName("channel") private String channel;
  //@SerializedName("ways") private String[] ways;

  public int getId() {
    return id;
  }

  public int getResourceId() {
    return resourceId;
  }

  public String getName() {
    return name;
  }

  public String getFormat() {
    return format;
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

  public String getSize() {
    return size;
  }

  public String getCnname() {
    return cnname;
  }

  public String getChannel() {
    return channel;
  }
}
