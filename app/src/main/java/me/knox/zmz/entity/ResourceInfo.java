package me.knox.zmz.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by KNOX.
 */

public class ResourceInfo {
  @SerializedName("cnname") private String cnname;
  @SerializedName("enname") private String enname;
  @SerializedName("remark") private String remark;
  @SerializedName("poster_a") private String poster; // poster, poster_a, poster_b, poster_m,poster_s
  @SerializedName("play_status") private String playStatus;
  @SerializedName("area") private String area;
  @SerializedName("category") private String category;
  @SerializedName("views") private int views;
  @SerializedName("score") private String score;
  @SerializedName("content") private String content;
  @SerializedName("premiere") private String premiere;
  @SerializedName("shareTitle") private String shareTitle;
  @SerializedName("shareContent") private String shareContent;
  @SerializedName("shareImage") private String shareImage;
  @SerializedName("shareUrl") private String shareUrl;

  public String getCnname() {
    return cnname;
  }

  public String getEnname() {
    return enname;
  }

  public String getRemark() {
    return remark;
  }

  public String getPoster() {
    return poster;
  }

  public String getPlayStatus() {
    return playStatus;
  }

  public String getArea() {
    return area;
  }

  public String getCategory() {
    return category;
  }

  public int getViews() {
    return views;
  }

  public String getScore() {
    return score;
  }

  public String getContent() {
    return content;
  }

  public String getPremiere() {
    return premiere;
  }

  public String getShareTitle() {
    return shareTitle;
  }

  public String getShareContent() {
    return shareContent;
  }

  public String getShareImage() {
    return shareImage;
  }

  public String getShareUrl() {
    return shareUrl;
  }
}
