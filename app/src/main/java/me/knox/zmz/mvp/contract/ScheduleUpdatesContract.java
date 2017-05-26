package me.knox.zmz.mvp.contract;

import io.reactivex.Flowable;
import java.util.List;
import java.util.Map;
import me.knox.zmz.entity.ScheduleUpdate;
import me.knox.zmz.network.JsonResponse;
import me.knox.zmz.view.BaseView;

/**
 * Created by KNOX.
 */

public interface ScheduleUpdatesContract {

  @FunctionalInterface
  interface Model {
    Flowable<JsonResponse<Map<String, List<ScheduleUpdate>>>> getScheduleUpdates(String start,
        String end);
  }

  interface View extends BaseView {
    void obtainScheduleUpdatesSucceed(Map<String, List<ScheduleUpdate>> stringListMap);
  }

  @FunctionalInterface
  interface Presenter {
    void getScheduleUpdates(String start, String end);
  }
}
