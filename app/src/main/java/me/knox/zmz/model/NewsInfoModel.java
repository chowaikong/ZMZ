package me.knox.zmz.model;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import javax.inject.Inject;
import me.knox.zmz.App;
import me.knox.zmz.contract.NewsInfoContract;
import me.knox.zmz.entity.NewsInfo;
import me.knox.zmz.network.JsonResponse;

/**
 * Created by KNOX.
 */

public class NewsInfoModel implements NewsInfoContract.Model {

  @Inject
  public NewsInfoModel() {
  }

  @Override public Flowable<JsonResponse<NewsInfo>> getNewsInfo(int id) {
    return App.getInstance().getApi().getNewsInfo(id).observeOn(AndroidSchedulers.mainThread(), true);
  }
}
