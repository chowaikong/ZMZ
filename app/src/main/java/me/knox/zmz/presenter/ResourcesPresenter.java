package me.knox.zmz.presenter;

import javax.inject.Inject;
import me.knox.zmz.contract.ResourcesContract;
import me.knox.zmz.network.ApiErrorException;

/**
 * Created by KNOX.
 */

public class ResourcesPresenter extends BasePresenter implements ResourcesContract.Presenter {

  private ResourcesContract.Model mModel;
  private ResourcesContract.View mView;

  @Inject
  public ResourcesPresenter(ResourcesContract.Model model, ResourcesContract.View view) {
    mModel = model;
    mView = view;
  }

  @Override public void getResources(int page) {
    addDisposable(mModel.getResources(page).subscribe(result -> {
      if (result.isSuccess()) {
        mView.obtainResourcesSucceed(result.getData().getDataList());
      } else {
        mView.obtainResourcesFailed(result.getInfo());
      }
    }, new ApiErrorException()));
  }
}
