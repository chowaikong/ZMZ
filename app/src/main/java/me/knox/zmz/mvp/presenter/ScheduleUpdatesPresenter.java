package me.knox.zmz.mvp.presenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import javax.inject.Inject;
import me.knox.zmz.mvp.contract.ScheduleUpdatesContract;
import me.knox.zmz.network.ApiErrorException;

/**
 * Created by KNOX.
 */

public class ScheduleUpdatesPresenter
    extends BasePresenter
    implements ScheduleUpdatesContract.Presenter {

  private final ScheduleUpdatesContract.Model mModel;
  private final ScheduleUpdatesContract.View mView;

  @Inject ScheduleUpdatesPresenter(ScheduleUpdatesContract.Model model,
      ScheduleUpdatesContract.View view) {
    mModel = model;
    mView = view;
  }

  @Override public void getScheduleUpdates(String start, String end) {
    addDisposable(mModel.getScheduleUpdates(start, end)
        .observeOn(AndroidSchedulers.mainThread(), true)
        .subscribe(result -> {
          if (result.isSuccess()) {
            mView.obtainScheduleUpdatesSucceed(result.getData());
          } else {
            mView.error(result.getInfo());
          }
        }, new ApiErrorException(mView)));
  }
}
