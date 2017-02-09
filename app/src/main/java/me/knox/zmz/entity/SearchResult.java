package me.knox.zmz.entity;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by KNOX.
 */

public class SearchResult {

  @SerializedName("count") private int count;
  @SerializedName("list") private List<Search> mSearches;

  public int getCount() {
    return count;
  }

  public List<Search> getSearches() {
    return mSearches;
  }
}
