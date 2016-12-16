package me.knox.zmz.contract;

import io.reactivex.Flowable;
import java.util.List;
import me.knox.zmz.entity.News;
import me.knox.zmz.network.JsonResponse;

/**
 * Created by KNOX.
 */

public interface NewsListContract {

  interface Model {
    Flowable<JsonResponse<List<News>>> getNewsList(int page);
  }

  interface View {
    void obtainNewsListSucceed(List<News> newsList);

    void obtainNewsListFailed(String error);
  }

  interface Presenter {
    void getNewsList(int page);
  }
}
