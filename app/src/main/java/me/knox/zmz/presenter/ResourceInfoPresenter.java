package me.knox.zmz.presenter;

import javax.inject.Inject;
import me.knox.zmz.contract.ResourceInfoContract;
import me.knox.zmz.network.ApiErrorException;

/**
 * Created by KNOX.
 */

public class ResourceInfoPresenter extends BasePresenter implements ResourceInfoContract.Presenter {

  private ResourceInfoContract.Model mModel;
  private ResourceInfoContract.View mView;

  @Inject
  public ResourceInfoPresenter(ResourceInfoContract.Model model, ResourceInfoContract.View view) {
    mModel = model;
    mView = view;
  }

  @Override public void getResourceInfo(int id) {
    addDisposable(mModel.getResourceInfo(id).subscribe(result -> {
      if (result.isSuccess()) {
        mView.obtainResourceInfoSucceed(result.getData());
      } else {
        mView.error(result.getInfo());
      }
    }, new ApiErrorException(mView)));
  }
}
