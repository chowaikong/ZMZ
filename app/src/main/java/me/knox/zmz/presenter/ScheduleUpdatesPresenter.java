package me.knox.zmz.presenter;

import javax.inject.Inject;
import me.knox.zmz.contract.ScheduleUpdatesContract;
import me.knox.zmz.network.ApiErrorException;

/**
 * Created by KNOX.
 */

public class ScheduleUpdatesPresenter extends BasePresenter implements ScheduleUpdatesContract.Presenter {

  private ScheduleUpdatesContract.Model mModel;
  private ScheduleUpdatesContract.View mView;

  @Inject
  public ScheduleUpdatesPresenter(ScheduleUpdatesContract.Model model,
      ScheduleUpdatesContract.View view) {
    mModel = model;
    mView = view;
  }

  @Override public void getScheduleUpdates(String start, String end) {
    addDisposable(mModel.getScheduleUpdates(start, end).subscribe(result -> {
      if (result.isSuccess()) {
        mView.obtainScheduleUpdatesSucceed(result.getData());
      } else {
        mView.obtainScheduleUpdatesFailed(result.getInfo());
      }
    }, new ApiErrorException()));
  }
}
