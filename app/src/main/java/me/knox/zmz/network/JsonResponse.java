package me.knox.zmz.network;

import com.google.gson.annotations.SerializedName;

/**
 * Created by KNOX on 2016-11-27.
 */

public class JsonResponse<T> {

  @SerializedName("status") private int mStatus;

  @SerializedName("data") private T mData;

  @SerializedName("info") private String mInfo;

  public boolean isSuccess() {
    return mStatus == 1;
  }

  public int getStatus() {
    return mStatus;
  }

  public T getData() {
    return mData;
  }

  public String getInfo() {
    return mInfo;
  }
}
