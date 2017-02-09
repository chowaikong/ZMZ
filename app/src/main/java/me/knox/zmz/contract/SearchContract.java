package me.knox.zmz.contract;

import io.reactivex.Flowable;
import me.knox.zmz.entity.SearchResult;
import me.knox.zmz.network.JsonResponse;
import me.knox.zmz.view.BaseView;

/**
 * Created by KNOX.
 */

public interface SearchContract {

  interface Model {
    Flowable<JsonResponse<SearchResult>> search(String keyword);
  }

  interface View extends BaseView{
    void obtainSearchResultSuccess(SearchResult result);
  }

  interface Presenter {
    void search(String keyword);
  }
}
