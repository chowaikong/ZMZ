package me.knox.zmz.entity;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by KNOX.
 */

public class Resource {

  @SerializedName("list") private List<Data> mDataList;

  public List<Data> getDataList() {
    return mDataList;
  }

  public class Data {
    @SerializedName("cnname") private String cnname;
    @SerializedName("enname") private String enname;
    @SerializedName("remark") private String remark;
    @SerializedName("area") private String area;
    @SerializedName("format") private String format;
    @SerializedName("category") private String category;
    @SerializedName("poster") private String poster; // poster, poster_a, poster_b, poster_m,poster_s
    @SerializedName("channel") private String channel;
    @SerializedName("lang") private String lang;
    @SerializedName("play_status") private String playStatus;
    @SerializedName("rank") private String rank;
    @SerializedName("score") private String score;
    @SerializedName("views") private String views;
    @SerializedName("itemupdate") private long itemUpdate;

    public String getCnname() {
      return cnname;
    }

    public String getEnname() {
      return enname;
    }

    public String getRemark() {
      return remark;
    }

    public String getArea() {
      return area;
    }

    public String getFormat() {
      return format;
    }

    public String getCategory() {
      return category;
    }

    public String getPoster() {
      return poster;
    }

    public String getChannel() {
      return channel;
    }

    public String getLang() {
      return lang;
    }

    public String getPlayStatus() {
      return playStatus;
    }

    public String getRank() {
      return rank;
    }

    public String getScore() {
      return score;
    }

    public String getViews() {
      return views;
    }

    public long getItemUpdate() {
      return itemUpdate;
    }
  }
}
