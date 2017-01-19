package me.knox.zmz.entity;

import java.util.List;

/**
 * Created by KNOX.
 */

public class NewsList {

  private List<News> mNewsList;

  public NewsList(List<News> newsList) {
    mNewsList = newsList;
  }

  public List<News> getList() {
    return mNewsList;
  }
}
