package me.knox.zmz.presenter;

import javax.inject.Inject;
import me.knox.zmz.contract.HotListContract;
import me.knox.zmz.model.HotListModel;
import me.knox.zmz.network.ApiErrorException;

/**
 * Created by KNOX.
 */

public class HotListPresenter extends BasePresenter implements HotListContract.Presenter {

  private HotListModel mHotListModel;
  private HotListContract.View mView;

  @Inject
  public HotListPresenter(HotListModel hotListModel, HotListContract.View view) {
    mHotListModel = hotListModel;
    mView = view;
  }

  @Override public void getHot() {
    addDisposable(mHotListModel.getHot().subscribe(result -> {
      if (result.isSuccess()) {
        mView.obtainHotListSuccess(result.getData());
      } else {
        mView.error(result.getInfo());
      }
    }, new ApiErrorException(mView)));
  }
}
