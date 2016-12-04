package me.knox.zmz.contract;

import io.reactivex.Flowable;
import java.util.List;
import java.util.Map;
import me.knox.zmz.entity.ScheduleUpdate;
import me.knox.zmz.network.JsonResponse;

/**
 * Created by KNOX.
 */

public interface ScheduleUpdatesContract {

  interface Model {
    Flowable<JsonResponse<Map<String, List<ScheduleUpdate>>>> getScheduleUpdates(String start,
        String end);
  }

  interface View {
    void obtainScheduleUpdatesSucceed(Map<String, List<ScheduleUpdate>> stringListMap);

    void obtainScheduleUpdatesFailed(String error);
  }

  interface Presenter {
    void getScheduleUpdates(String start, String end);
  }
}
