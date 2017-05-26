package me.knox.zmz.mvp.presenter;

import javax.inject.Inject;
import me.knox.zmz.mvp.contract.SearchContract;
import me.knox.zmz.network.ApiErrorException;

/**
 * Created by KNOX.
 */

public class SearchPresenter
    extends BasePresenter
    implements SearchContract.Presenter {

  private final SearchContract.Model mModel;
  private final SearchContract.View mView;

  @Inject SearchPresenter(SearchContract.Model model, SearchContract.View view) {
    mModel = model;
    mView = view;
  }

  @Override public void search(String keyword, int limit, int page) {
    addDisposable(mModel.search(keyword, limit, page).subscribe(result -> {
      if (result.isSuccess()) {
        mView.obtainSearchResultSuccess(result.getData());
      } else {
        mView.error(result.getInfo());
      }
    }, new ApiErrorException(mView)));
  }
}
