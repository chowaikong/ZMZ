package me.knox.zmz.presenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import javax.inject.Inject;
import me.knox.zmz.contract.NewsListContract;
import me.knox.zmz.network.ApiErrorException;

/**
 * Created by KNOX.
 */

public class NewsListPresenter extends BasePresenter implements NewsListContract.Presenter {

  private NewsListContract.Model mModel;
  private NewsListContract.View mView;

  @Inject public NewsListPresenter(NewsListContract.Model model, NewsListContract.View view) {
    mModel = model;
    mView = view;
  }

  @Override public void getNewsList(int page) {
    addDisposable(mModel.getNewsList(page)
        .observeOn(AndroidSchedulers.mainThread(), true)
        .subscribe(result -> {
          if (result.isSuccess()) {
            mView.obtainNewsListSucceed(result.getData());
          } else {
            mView.error(result.getInfo());
          }
        }, new ApiErrorException(mView)));
  }
}
