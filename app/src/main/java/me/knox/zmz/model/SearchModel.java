package me.knox.zmz.model;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import javax.inject.Inject;
import me.knox.zmz.App;
import me.knox.zmz.contract.SearchContract;
import me.knox.zmz.entity.SearchResult;
import me.knox.zmz.network.JsonResponse;

/**
 * Created by KNOX.
 */

public class SearchModel implements SearchContract.Model {

  @Inject
  public SearchModel() {
  }

  @Override public Flowable<JsonResponse<SearchResult>> search(String keyword) {
    return App.getInstance().getApi().search(keyword).observeOn(AndroidSchedulers.mainThread());
  }
}
