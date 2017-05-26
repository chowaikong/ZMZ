package me.knox.zmz.mvp.contract;

import io.reactivex.Flowable;
import me.knox.zmz.entity.NewsInfo;
import me.knox.zmz.network.JsonResponse;
import me.knox.zmz.view.BaseView;

/**
 * Created by KNOX.
 */

public interface NewsInfoContract {

  @FunctionalInterface
  interface Model {
    Flowable<JsonResponse<NewsInfo>> getNewsInfo(int id);
  }

  interface View extends BaseView {
    void obtainNewsInfoSucceed(NewsInfo newsInfo);
  }

  @FunctionalInterface
  interface Presenter {
    void getNewsInfo(int id);
  }
}
