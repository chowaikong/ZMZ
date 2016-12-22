package me.knox.zmz.presenter;

import javax.inject.Inject;
import me.knox.zmz.contract.UpdatesContract;
import me.knox.zmz.network.ApiErrorException;

/**
 * Created by KNOX.
 */

public class UpdatesPresenter extends BasePresenter implements UpdatesContract.Presenter {

  private UpdatesContract.Model mModel;
  private UpdatesContract.View mView;

  @Inject
  public UpdatesPresenter(UpdatesContract.Model model, UpdatesContract.View view) {
    mModel = model;
    mView = view;
  }

  @Override public void getUpdates() {
    addDisposable(mModel.getUpdates().subscribe(result -> {
      if (result.isSuccess()) {
        mView.obtainUpdatesSucceed(result.getData());
      } else {
        mView.error(result.getInfo());
      }
    }, new ApiErrorException(mView)));
  }
}
