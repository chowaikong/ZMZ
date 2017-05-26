package me.knox.zmz.mvp.presenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import javax.inject.Inject;
import me.knox.zmz.mvp.contract.ResourceInfoContract;
import me.knox.zmz.network.ApiErrorException;

/**
 * Created by KNOX.
 */

public class ResourceInfoPresenter
    extends BasePresenter
    implements ResourceInfoContract.Presenter {

  private final ResourceInfoContract.Model mModel;
  private final ResourceInfoContract.View mView;

  @Inject ResourceInfoPresenter(ResourceInfoContract.Model model, ResourceInfoContract.View view) {
    mModel = model;
    mView = view;
  }

  @Override public void getResourceInfo(int id, int isSharable) {
    addDisposable(mModel.getResourceInfo(id, isSharable)
        .observeOn(AndroidSchedulers.mainThread(), true)
        .subscribe(result -> {
          if (result.isSuccess()) {
            mView.obtainResourceInfoSucceed(result.getData());
          } else {
            mView.error(result.getInfo());
          }
        }, new ApiErrorException(mView)));
  }
}
