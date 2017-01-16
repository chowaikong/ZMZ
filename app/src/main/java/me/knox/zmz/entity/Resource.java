package me.knox.zmz.entity;

import android.os.Parcel;
import android.os.Parcelable;
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

  public static class Data implements Parcelable {
    @SerializedName("id") private int id;
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

    public int getId() {
      return id;
    }

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

    @Override public int describeContents() {
      return 0;
    }

    @Override public void writeToParcel(Parcel dest, int flags) {
      dest.writeInt(this.id);
      dest.writeString(this.cnname);
      dest.writeString(this.enname);
      dest.writeString(this.remark);
      dest.writeString(this.area);
      dest.writeString(this.format);
      dest.writeString(this.category);
      dest.writeString(this.poster);
      dest.writeString(this.channel);
      dest.writeString(this.lang);
      dest.writeString(this.playStatus);
      dest.writeString(this.rank);
      dest.writeString(this.score);
      dest.writeString(this.views);
      dest.writeLong(this.itemUpdate);
    }

    public Data() {
    }

    protected Data(Parcel in) {
      this.id = in.readInt();
      this.cnname = in.readString();
      this.enname = in.readString();
      this.remark = in.readString();
      this.area = in.readString();
      this.format = in.readString();
      this.category = in.readString();
      this.poster = in.readString();
      this.channel = in.readString();
      this.lang = in.readString();
      this.playStatus = in.readString();
      this.rank = in.readString();
      this.score = in.readString();
      this.views = in.readString();
      this.itemUpdate = in.readLong();
    }

    public static final Parcelable.Creator<Data> CREATOR = new Parcelable.Creator<Data>() {
      @Override public Data createFromParcel(Parcel source) {
        return new Data(source);
      }

      @Override public Data[] newArray(int size) {
        return new Data[size];
      }
    };
  }
}
