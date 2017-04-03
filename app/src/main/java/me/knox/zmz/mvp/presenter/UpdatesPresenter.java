package me.knox.zmz.mvp.presenter;

import javax.inject.Inject;
import me.knox.zmz.mvp.contract.UpdatesContract;
import me.knox.zmz.mvp.model.ResourceInfoModel;
import me.knox.zmz.network.ApiErrorException;

/**
 * Created by KNOX.
 */

public class UpdatesPresenter extends BasePresenter implements UpdatesContract.Presenter {

  private UpdatesContract.Model mModel;
  private ResourceInfoModel mInfoModel;
  private UpdatesContract.View mView;

  @Inject public UpdatesPresenter(UpdatesContract.Model model, ResourceInfoModel infoModel,
      UpdatesContract.View view) {
    mModel = model;
    mInfoModel = infoModel;
    mView = view;
  }

  @Override public void getUpdates() {
    addDisposable(mModel.getUpdates().map(listJsonResponse -> {
      for (int i = 0; i < listJsonResponse.getData().size(); i++) {
        int finalI = i;
        mInfoModel.getResourceInfo(listJsonResponse.getData().get(i).getResourceId(), 0)
            .subscribe(result -> {
              if (result.isSuccess()) {
                listJsonResponse.getData().get(finalI).setPoster(result.getData().getPoster());
              }
            });
      }
      return listJsonResponse;
    }).subscribe(result -> {
      if (result.isSuccess()) {
        mView.obtainUpdatesSucceed(result.getData());
      } else {
        mView.error(result.getInfo());
      }
    }, new ApiErrorException(mView)));
  }
}
