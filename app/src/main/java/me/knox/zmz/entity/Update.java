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
  @SerializedName("season") private int season;
  @SerializedName("episode") private int episode;
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

  public int getSeason() {
    return season;
  }

  public int getEpisode() {
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
