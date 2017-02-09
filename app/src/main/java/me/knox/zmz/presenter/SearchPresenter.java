package me.knox.zmz.presenter;

import javax.inject.Inject;
import me.knox.zmz.contract.SearchContract;
import me.knox.zmz.network.ApiErrorException;

/**
 * Created by KNOX.
 */

public class SearchPresenter extends BasePresenter implements SearchContract.Presenter {

  private SearchContract.Model mModel;
  private SearchContract.View mView;

  @Inject
  public SearchPresenter(SearchContract.Model model, SearchContract.View view) {
    mModel = model;
    mView = view;
  }

  @Override public void search(String keyword) {
    addDisposable(mModel.search(keyword).subscribe(result -> {
      if (result.isSuccess()) {
        mView.obtainSearchResultSuccess(result.getData());
      } else {
        mView.error(result.getInfo());
      }
    }, new ApiErrorException(mView)));
  }
}
