package me.knox.zmz.mvp.model;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import me.knox.zmz.App;
import me.knox.zmz.mvp.contract.ScheduleUpdatesContract;
import me.knox.zmz.entity.ScheduleUpdate;
import me.knox.zmz.network.JsonResponse;

/**
 * Created by KNOX.
 */

public class ScheduleUpdatesModel implements ScheduleUpdatesContract.Model {

  @Inject public ScheduleUpdatesModel() {
    // empty constructor for injection
  }

  @Override
  public Flowable<JsonResponse<Map<String, List<ScheduleUpdate>>>> getScheduleUpdates(String start,
      String end) {
    return App.getInstance()
        .getApi()
        .getScheduleUpdates(start, end)
        .take(10)
        .observeOn(AndroidSchedulers.mainThread(), true);
  }
}
