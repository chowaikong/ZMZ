package me.knox.zmz.entity;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/**
 * Created by KNOX.
 */

public class News implements Parcelable {
  @SerializedName("id") private int id;
  @SerializedName("title") private String title;
  @SerializedName("type") private String type;
  @SerializedName("poster_b") private String poster; // poster_b,poster_m,poster_s
  @SerializedName("dateline") private long date;
  @SerializedName("intro") private String intro;
  @SerializedName("views") private String views;

  public int getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getType() {
    return type;
  }

  public String getPoster() {
    return poster;
  }

  public long getDate() {
    return date;
  }

  public String getIntro() {
    return intro;
  }

  public String getViews() {
    return views;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(this.id);
    dest.writeString(this.title);
    dest.writeString(this.type);
    dest.writeString(this.poster);
    dest.writeLong(this.date);
    dest.writeString(this.intro);
    dest.writeString(this.views);
  }

  public News() {
  }

  protected News(Parcel in) {
    this.id = in.readInt();
    this.title = in.readString();
    this.type = in.readString();
    this.poster = in.readString();
    this.date = in.readLong();
    this.intro = in.readString();
    this.views = in.readString();
  }

  public static final Parcelable.Creator<News> CREATOR = new Parcelable.Creator<News>() {
    @Override public News createFromParcel(Parcel source) {
      return new News(source);
    }

    @Override public News[] newArray(int size) {
      return new News[size];
    }
  };
}
