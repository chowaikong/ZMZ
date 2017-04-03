package me.knox.zmz.mvp.contract;

import io.reactivex.Flowable;
import me.knox.zmz.entity.SearchResult;
import me.knox.zmz.network.JsonResponse;
import me.knox.zmz.view.BaseView;

/**
 * Created by KNOX.
 */

public interface SearchContract {

  interface Model {
    Flowable<JsonResponse<SearchResult>> search(String keyword, int limit, int page);
  }

  interface View extends BaseView{
    void obtainSearchResultSuccess(SearchResult result);
  }

  interface Presenter {
    void search(String keyword, int limit, int page);
  }
}
