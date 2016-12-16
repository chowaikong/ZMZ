package me.knox.zmz.model;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import java.util.List;
import javax.inject.Inject;
import me.knox.zmz.App;
import me.knox.zmz.contract.NewsListContract;
import me.knox.zmz.entity.News;
import me.knox.zmz.network.JsonResponse;

/**
 * Created by KNOX.
 */

public class NewsListModel implements NewsListContract.Model {

  @Inject public NewsListModel() {
  }

  @Override public Flowable<JsonResponse<List<News>>> getNewsList(int page) {
    return App.getInstance().getApi().getNews(page).observeOn(AndroidSchedulers.mainThread(), true);
  }
}
