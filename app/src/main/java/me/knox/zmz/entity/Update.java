package me.knox.zmz.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by KNOX.
 */

public class Update {
  /**
   * id: "283038",
   resourceid: "11010",
   name: "摩登家庭.Modern.Family.S08E08.中英字幕.HDTVrip.1024X576.mp4",
   format: "HR-HDTV",
   season: "8",
   episode: "8",
   size: "202MB",
   cnname: "摩登家庭",
   channel: "tv",
   ways: {
   1: "ed2k://|file|%E6%91%A9%E7%99%BB%E5%AE%B6%E5%BA%AD.Modern.Family.S08E08.%E4%B8%AD%E8%8B%B1%E5%AD%97%E5%B9%95.HDTVrip.1024X576.mp4|211814711|c2f5c279ae083d37004d82be359f9dc4|h=ek5sht6stczygdhh7dx4drs2tcx32f3n|/",
   2: "magnet:?xt=urn:btih:34747c972346ccb37579ce0e579a920db8c4972a&tr=http://tracker.openbittorrent.com/announce&tr=udp://tracker.openbittorrent.com:80/announce&tr=udp://tr.cili001.com:6666/announce&tr=http://tracker.publicbt.com/announce&tr=udp://open.demonii.com:1337&tr=udp://tracker.opentrackr.org:1337/announce&tr=http://tr.cili001.com:6666/announce"
   }
   */

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
