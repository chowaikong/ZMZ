package me.knox.zmz.mvp.presenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import javax.inject.Inject;
import me.knox.zmz.mvp.contract.NewsListContract;
import me.knox.zmz.network.ApiErrorException;

/**
 * Created by KNOX.
 */

public class NewsListPresenter
    extends BasePresenter
    implements NewsListContract.Presenter {

  private final NewsListContract.Model mModel;
  private final NewsListContract.View mView;

  @Inject NewsListPresenter(NewsListContract.Model model, NewsListContract.View view) {
    mModel = model;
    mView = view;
  }

  @Override public void getNewsList() {
    addDisposable(mModel.getNewsList()
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
