package me.knox.zmz.mvp.presenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import javax.inject.Inject;
import me.knox.zmz.mvp.contract.HotListContract;
import me.knox.zmz.mvp.model.HotListModel;
import me.knox.zmz.network.ApiErrorException;

/**
 * Created by KNOX.
 */

public class HotListPresenter
    extends BasePresenter
    implements HotListContract.Presenter {

  private HotListModel mHotListModel;
  private HotListContract.View mView;

  @Inject HotListPresenter(HotListModel hotListModel, HotListContract.View view) {
    mHotListModel = hotListModel;
    mView = view;
  }

  @Override public void getHot() {
    addDisposable(
        mHotListModel.getHot().observeOn(AndroidSchedulers.mainThread(), true).subscribe(result -> {
          if (result.isSuccess()) {
            mView.obtainHotListSuccess(result.getData());
          } else {
            mView.error(result.getInfo());
          }
        }, new ApiErrorException(mView)));
  }
}
