package me.knox.zmz.entity;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/**
 * Created by KNOX.
 */

public class ScheduleUpdate implements Parcelable {

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

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(this.id);
    dest.writeString(this.cnname);
    dest.writeString(this.enname);
    dest.writeString(this.poster);
    dest.writeString(this.posterB);
    dest.writeString(this.season);
    dest.writeString(this.episode);
  }

  public ScheduleUpdate() {
  }

  protected ScheduleUpdate(Parcel in) {
    this.id = in.readInt();
    this.cnname = in.readString();
    this.enname = in.readString();
    this.poster = in.readString();
    this.posterB = in.readString();
    this.season = in.readString();
    this.episode = in.readString();
  }

  public static final Parcelable.Creator<ScheduleUpdate> CREATOR =
      new Parcelable.Creator<ScheduleUpdate>() {
        @Override public ScheduleUpdate createFromParcel(Parcel source) {
          return new ScheduleUpdate(source);
        }

        @Override public ScheduleUpdate[] newArray(int size) {
          return new ScheduleUpdate[size];
        }
      };
}
