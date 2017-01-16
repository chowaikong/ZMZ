package me.knox.zmz.presenter;

import javax.inject.Inject;
import me.knox.zmz.contract.NewsInfoContract;
import me.knox.zmz.network.ApiErrorException;

/**
 * Created by KNOX.
 */

public class NewsInfoPresenter extends BasePresenter implements NewsInfoContract.Presenter {

  private NewsInfoContract.Model mModel;
  private NewsInfoContract.View mView;

  @Inject
  public NewsInfoPresenter(NewsInfoContract.Model model, NewsInfoContract.View view) {
    mModel = model;
    mView = view;
  }

  @Override public void getNewsInfo(int id) {
    addDisposable(mModel.getNewsInfo(id).subscribe(result -> {
      if (result.isSuccess()) {
        mView.obtainNewsInfoSucceed(result.getData());
      } else {
        mView.error(result.getInfo());
      }
    }, new ApiErrorException(mView)));
  }
}
