package me.knox.zmz.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by KNOX.
 */

public class NewsInfo {
  @SerializedName("favorites") private int favorites;
  @SerializedName("translator") private String translator;
  @SerializedName("intro") private String intro;
  @SerializedName("id") private int id;
  @SerializedName("title") private String title;
  @SerializedName("resourceid") private int resourceid;
  @SerializedName("views") private int views;
  @SerializedName("poster") private String poster;
  @SerializedName("source") private String source;
  @SerializedName("content") private String content;
  @SerializedName("dateline") private long dateline;
  @SerializedName("type") private String type;

  public int getFavorites() {
    return favorites;
  }

  public String getTranslator() {
    return translator;
  }

  public String getIntro() {
    return intro;
  }

  public int getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public int getResourceid() {
    return resourceid;
  }

  public int getViews() {
    return views;
  }

  public String getPoster() {
    return poster;
  }

  public String getSource() {
    return source;
  }

  public String getContent() {
    return content;
  }

  public long getDateline() {
    return dateline;
  }

  public String getType() {
    return type;
  }
}
