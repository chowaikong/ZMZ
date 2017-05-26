package me.knox.zmz.mvp.model;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import java.util.List;
import javax.inject.Inject;
import me.knox.zmz.App;
import me.knox.zmz.entity.News;
import me.knox.zmz.mvp.contract.NewsListContract;
import me.knox.zmz.network.JsonResponse;

/**
 * Created by KNOX.
 */

public class NewsListModel implements NewsListContract.Model {

  @Inject public NewsListModel() {
    // empty constructor for injection
  }

  @Override public Flowable<JsonResponse<List<News>>> getNewsList() {
    return App.getInstance().getApi().getNews().observeOn(AndroidSchedulers.mainThread(), true);
  }
}
