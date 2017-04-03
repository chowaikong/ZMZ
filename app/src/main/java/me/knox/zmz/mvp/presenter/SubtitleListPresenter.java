package me.knox.zmz.mvp.presenter;

import javax.inject.Inject;
import me.knox.zmz.mvp.contract.SubtitleListContract;
import me.knox.zmz.network.ApiErrorException;

/**
 * Created by KNOX.
 */

public class SubtitleListPresenter extends BasePresenter implements SubtitleListContract.Presenter {

  private SubtitleListContract.Model mModel;
  private SubtitleListContract.View mView;

  @Inject
  public SubtitleListPresenter(SubtitleListContract.Model model, SubtitleListContract.View view) {
    mModel = model;
    mView = view;
  }

  @Override public void getSubtitleList() {
    addDisposable(mModel.getSubtitleList().subscribe(result -> {
      if (result.isSuccess()) {
        mView.obtainSubtitleListSuccess(result.getData());
      } else {
        mView.error(result.getInfo());
      }
    }, new ApiErrorException(mView)));
  }
}
